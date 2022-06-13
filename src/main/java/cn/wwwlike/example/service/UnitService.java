package cn.wwwlike.example.service;

import cn.wwwlike.example.dao.UnitDao;
import cn.wwwlike.example.entity.Unit;
import cn.wwwlike.vlife.core.VLifeService;
import org.springframework.stereotype.Service;

@Service
public class UnitService extends VLifeService<Unit, UnitDao> {
}
