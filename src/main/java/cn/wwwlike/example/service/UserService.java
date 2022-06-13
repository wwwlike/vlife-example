package cn.wwwlike.example.service;

import cn.wwwlike.example.dao.UserDao;
import cn.wwwlike.example.entity.User;
import cn.wwwlike.vlife.core.VLifeService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends VLifeService<User, UserDao> {
}
