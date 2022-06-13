package cn.wwwlike.example.api;

import cn.wwwlike.example.dto.UserDto;
import cn.wwwlike.example.entity.User;
import cn.wwwlike.example.req.UserPageReq;
import cn.wwwlike.example.service.UserService;
import cn.wwwlike.example.vo.UserDetail;
import cn.wwwlike.vlife.bean.PageVo;
import cn.wwwlike.vlife.core.VLifeApi;
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
