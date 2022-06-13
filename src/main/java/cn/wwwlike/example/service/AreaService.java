package cn.wwwlike.example.service;

import cn.wwwlike.example.dao.AreaDao;
import cn.wwwlike.example.entity.Area;
import cn.wwwlike.vlife.core.VLifeService;
import org.springframework.stereotype.Service;

@Service
public class AreaService extends VLifeService<Area, AreaDao> {
}
