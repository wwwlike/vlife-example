/*
 *  vlife http://github.com/wwwlike/vlife
 *
 *  Copyright (C)  2018-2022 vlife
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.wwwlike.example.vo;

import cn.wwwlike.example.entity.Area;
import cn.wwwlike.example.entity.Role;
import cn.wwwlike.example.entity.User;
import cn.wwwlike.vlife.annotation.VField;
import cn.wwwlike.vlife.base.VoBean;
import lombok.Data;
import java.util.List;

/**
 *  明细信息查看userVO对象封装
 *  能够做到需要什么都放什么,查询的工作交给框架来处理
 * @author xiaoyu
 * @date 2022/6/4
 */
@Data
public class UserDetail implements VoBean<User> {
    /**
     * vo必须有id字段
     */
    public String id;

    /**
     * user属性省略 见UserDetail里的用法
     * 1. 对象注入
     * 2. 属性超级打平注入
     */

    /**
     * 外键关联对象的VO形式的注入
     * 用户机构信息VO
     */
    public UnitVo unit;

    /**
     * 外键对象间接注入
     * 用户机构所在地区
     */
    @VField(pathName = "unit_area")
    public Area area;

    /**
     * 多对多关联表
     * 用户角色信息
     */
    @VField(pathName = "roleUser_role")
    public List<Role> roles;

    /**
     * 多对多，对方表的指定属性打平
     * 用户角色名称集合
     */
    @VField(pathName = "roleUser_role_roleName")
    public List<String> roleName;


    /**
     * 多对1,指定字段打平
     * 用户角色ID集合
     */
    @VField(pathName = "roleUser_roleId")
    public List<String> roleIds;



}
