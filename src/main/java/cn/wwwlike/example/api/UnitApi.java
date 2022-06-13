package cn.wwwlike.example.api;

import cn.wwwlike.example.dto.UnitDto;
import cn.wwwlike.example.entity.Unit;
import cn.wwwlike.example.service.UnitService;
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
 * unit接口;
 */
@RestController
@RequestMapping("/unit")
public class UnitApi extends VLifeApi<Unit, UnitService> {
  /**
   * unit分页查询;
   * @return;
   */
  @GetMapping("/page")
  public PageVo<Unit> page() {
    PageQuery<Unit> req= new PageQuery<Unit>(Unit.class);
    return service.findPage(req);
  }

  /**
   * unit明细查询;
   * @param id 查询id;
   * @return;
   */
  @GetMapping("/detail/{id}")
  public Unit detail(@PathVariable String id) {
    return service.findOne(id);
  }

  /**
   * unitDto保存;
   * @param dto unitDto;
   * @return;
   */
  @PostMapping("/save")
  public UnitDto save(@RequestBody UnitDto dto) {
    return service.save(dto);
  }

  /**
   * unit删除;
   * @param id 主键id;
   * @return;
   */
  @DeleteMapping("/delete/{id}")
  public Long delete(@PathVariable String id) {
    return service.remove(id);
  }
}
