package cn.wwwlike.example.api;

import cn.wwwlike.example.entity.Area;
import cn.wwwlike.example.service.AreaService;
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
 * area接口;
 */
@RestController
@RequestMapping("/area")
public class AreaApi extends VLifeApi<Area, AreaService> {
  /**
   * area分页查询;
   * @return;
   */
  @GetMapping("/page")
  public PageVo<Area> page() {
    PageQuery<Area> req= new PageQuery<Area>(Area.class);
    return service.findPage(req);
  }

  /**
   * area明细查询;
   * @param id 查询id;
   * @return;
   */
  @GetMapping("/detail/{id}")
  public Area detail(@PathVariable String id) {
    return service.findOne(id);
  }

  /**
   * area保存;
   * @param entity area;
   * @return;
   */
  @PostMapping("/save")
  public Area save(@RequestBody Area entity) {
    return service.save(entity);
  }

  /**
   * area删除;
   * @param id 主键id;
   * @return;
   */
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable String id) {
    return service.remove(id);
  }
}
