package cn.wwwlike.example.service;

import cn.wwwlike.example.dao.RoleUserDao;
import cn.wwwlike.example.entity.RoleUser;
import cn.wwwlike.vlife.core.VLifeService;
import org.springframework.stereotype.Service;

@Service
public class RoleUserService extends VLifeService<RoleUser, RoleUserDao> {
}
