package cn.wwwlike.example.api;

import cn.wwwlike.example.entity.Role;
import cn.wwwlike.example.service.RoleService;
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
 * role接口;
 */
@RestController
@RequestMapping("/role")
public class RoleApi extends VLifeApi<Role, RoleService> {
  /**
   * role分页查询;
   * @return;
   */
  @GetMapping("/page")
  public PageVo<Role> page() {
    PageQuery<Role> req= new PageQuery<Role>(Role.class);
    return service.findPage(req);
  }

  /**
   * role明细查询;
   * @param id 查询id;
   * @return;
   */
  @GetMapping("/detail/{id}")
  public Role detail(@PathVariable String id) {
    return service.findOne(id);
  }

  /**
   * role保存;
   * @param entity role;
   * @return;
   */
  @PostMapping("/save")
  public Role save(@RequestBody Role entity) {
    return service.save(entity);
  }

  /**
   * role删除;
   * @param id 主键id;
   * @return;
   */
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable String id) {
    return service.remove(id);
  }
}
