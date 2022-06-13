package cn.wwwlike.example.service;

import cn.wwwlike.example.dao.RoleDao;
import cn.wwwlike.example.entity.Role;
import cn.wwwlike.vlife.core.VLifeService;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends VLifeService<Role, RoleDao> {
}
