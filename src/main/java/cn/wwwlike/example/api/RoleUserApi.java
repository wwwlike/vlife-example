package cn.wwwlike.example.api;

import cn.wwwlike.example.entity.RoleUser;
import cn.wwwlike.example.service.RoleUserService;
import cn.wwwlike.vlife.bean.PageVo;
import cn.wwwlike.vlife.core.VLifeApi;
import cn.wwwlike.vlife.query.req.PageQuery;
import java.lang.Long;
import java.lang.String;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * roleUser接口;
 */
@RestController
@RequestMapping("/roleUser")
public class RoleUserApi extends VLifeApi<RoleUser, RoleUserService> {
  /**
   * roleUser分页查询;
   * @return;
   */
  @GetMapping("/page")
  public PageVo<RoleUser> page() {
    PageQuery<RoleUser> req= new PageQuery<RoleUser>(RoleUser.class);
    return service.findPage(req);
  }

  /**
   * roleUser明细查询;
   * @param id 查询id;
   * @return;
   */
  @GetMapping("/detail/{id}")
  public RoleUser detail(@PathVariable String id) {
    return service.findOne(id);
  }

  /**
   * roleUser保存;
   * @param entity roleUser;
   * @return;
   */
  @PostMapping("/save")
  public RoleUser save(@RequestBody RoleUser entity) {
    return service.save(entity);
  }

  /**
   * roleUser删除;
   * @param id 主键id;
   * @return;
   */
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable String id) {
    return service.remove(id);
  }
}
