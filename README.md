
- 基于vlife-starter完成springboot项目框架搭建后，vlife可以帮助你在半小时完成一个模块多张表的接口开发上线工作；

> 用户、机构、地区、角色进行系统基础管理模块的开发示例,重点以用户业务来说明VLIFE的简单高效。

* 示例关键代码，全部请查看 [vlife-example](http://github.wwwlike/vlife-example)

## 1. 引入依赖
> 以maven项目为例；pom.xml里引入vlife的starter进行依赖

``` xml
 <dependency>
    <groupId>io.github.wwwlike</groupId>
    <artifactId>vlife-spring-boot-starter</artifactId>
    <version>1.0.2</version>
 </dependency>
```
- pom里其他jar包和插件的依赖
* [X] queryDsl插件，生成querydsl实体类对应类的插件 （apt-maven-plugin）
* [X] vlife插件，智能生成Api,service,dao插件 （vlife-plugin）
* [X] 数据库驱动和连接池(mysql & p6spy)

## 2. 编写模型
> 根据业务需求设计编写模型（DO\VO\DTO\REQ)
### 模型继承说明
- 数据库实体对象模型设计（DO）,继承DbEntity
- 数据保存对象封装对象设计（DTO）,继承接口SaveBean
- 视图展示层封装对象设计（VO），继承接口VoBean
- 查询条件入参对象模型设计（REQ）,一般查询继承VlifeQuery,分页查询条件继承PageQuery

> 2.1 DO实体类设计

- 实体类编写完成后，还是利用JPA来自动生成表结构比较方便；( yml->ddl-auto: update )
![](../../static/img/example_db.png)
### 表说明
-  机构表是用户表的外键表，
-  地区表和用户表是间接外键表关系,
-  角色表和用户表通过角色用户关联表形成多对多关联关系；

``` java
public class User extends DbEntity { //DO对象都继承 DbEntity
    public String name;
    public String tel;
    public String idno;
    public String unitId; //外键字段命名=外键DO实体类名称 unit + Id进行组合
    public Date joinDate;
}
```

> 2.2 用户保存dto对象

``` java
@Data
public class UserDto implements SaveBean<User> { //保存的dto对象需要实现SaveBean接口，把当前实体类User写入到泛型
    public String id;
    @VField(pathName ="name") //保存的别名 使用VField注解的 pathName属性设置真实的数据库名称
    public String username;
    public String tel;
    public String idno;
    @VField(pathName="roleUser_roleId") //保存USER时会在多对多保存表里查询roleId里提供的关联记录
    public List<String> roleId;
    @VField(pathName="roleUser_role") //保存User,关联保存role,以及roleUser
    public List<Role> roles;
    public Unit unit; //保存User之前，保存Unit,把unit里的外键赋值给User进行保存
}
```
- 保存User的同时我们可以级联保存这么多的关联对象，和Hibernate一样？NoNoNo,这是在DTO里不是DO里。这里做了很多封装，并且支持递归

> 2.3 用户列表VO对象

``` java
public class UserVo implements VoBean<User> { //实现vobean接口，将user作为泛型设置进去
    public String id;
    @VField(pathName = "joinDate",tran = YearExpressTran.class) //使用Vfield注解，将joinDate的入职日期提取年份赋值给 joinYear
    public Integer joinYear;
    public String name;
    public String tel;
    @VField(pathName = "idno")  //查询别名 idno转换成idNumber显示
    public String idNumber;
    public String unit_name; //将user的外键unit表的字段name打平在列表里进行展示，unit_name是路径名称
    @VField(pathName = "unit_area_areaName")
    public String areaName;  //将间接外键area表里的areaName打平在user列表里显示，areaName需要用pathName路径进行映射
}
- 查询展示层里有外键表的字段，外键表的外键表的字段(甚至更深层次复杂的都可以“注入”进来)，这里你需要重点关注**pathName**这个注解,后面还会详细介绍。在VO里可以轻松实现需要什么数据就放入什么属性，只要符合Vlife规则，VLife就一定可以查询的到。

> 2.4 查询过滤条件Req

``` java
@VClazz(orders ="joinDate_desc" ) //查询里覆盖默认的排序
public class UserPageReq extends PageQuery<User> { //分页查询条件继承 pageQuery，泛型写入实体对象信息
    /**
     * 3个字段 or联合 like查询
     */
    @VField(pathName = "name",orReqFields = {"idno","tel"},opt = Opt.like)
    public String search;
    /**
     * 入职日期根据年度转换查询
     */
    @VField(pathName = "joinDate",tran = YearExpressTran.class)
    public Integer joinYear;
    /**
     * 多对1(间接)条件查询
     * 根据外键表unit的外键表area的地区编码查询（产生2个leftJoin）
     */
    public String unit_area_areaCode;
    /**
     * 1对多In查询
     * 根据角色用户表里的角色ID过滤用户
     */
    @VField(pathName = "roleUser_roleId")
    public String roleId;
    /**
     * 多对多，子查询过滤，根据用户关联角色表里的角色code来过滤用户信息
     */
    @VField(pathName = "roleUser_role_code")
    public String roleCode;
}
```
- Query对象里和VO里一样也可以是任何有关联的DO上的属性作为查询条件，service里接收到这个对象就可以完成模型里定义的复杂逻辑的查询，VO是查看你定义就完了，REQ是想有什么条件拿就定义什么就可以了。再次强调下：实现这样的灵活程度全部需要建立在符合关联映射关系。要不臣妾做不到！


## 3 代码生成

- 调用 apt:process 插件来生成DO实体类的queryDSL的映射代码(Q开头)，如User->QUser;Unit->QUnit
- 调用 vlife:createCode 插件来生成DAO，Service，API代码，注意如果已经初始化存在则不会重复生成覆盖。
- 系统生成了api,service,dao代码
![](../../static/img/codeCreate.png)

. 自动生成的API接口，直接启动项目即可运行
``` java
/**
 * user接口;
 */
@RestController
@RequestMapping("/user")
public class UserApi extends VLifeApi<User, UserService> {
  /**
   * user分页查询;
   * @param req 查询条件;
   * @return;
   */
  @GetMapping("/page")
  public PageVo<User> page(UserPageReq req) {
    return service.findPage(req);
  }

  /**
   * userVO明细查询;
   * @param id 查询id;
   * @return;
   */
  @GetMapping("/detail/{id}")
  public UserDetail detail(@PathVariable String id) {
    return service.queryOne(UserDetail.class,id);
  }

  /**
   * userDto保存;
   * @param dto userDto;
   * @return;
   */
  @PostMapping("/save")
  public UserDto save(@RequestBody UserDto dto) {
    return service.save(dto);
  }

  /**
   * user删除;
   * @param id 主键id;
   * @return;
   */
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable String id) {
    return service.remove(id);
  }
}
```
