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

package cn.wwwlike.example.dto;
import cn.wwwlike.example.entity.Area;
import cn.wwwlike.example.entity.Role;
import cn.wwwlike.example.entity.Unit;
import cn.wwwlike.example.entity.User;
import cn.wwwlike.vlife.annotation.VField;
import cn.wwwlike.vlife.base.SaveBean;
import lombok.Data;

import java.util.List;

/**
 * 用户保存dto对象
 */
@Data
public class UserDto implements SaveBean<User> {
    public String id;
    @VField(pathName ="name")
    public String username;
    public String tel;
    public String idno;
    @VField(pathName="roleUser_roleId")
    public List<String> roleId;
    @VField(pathName="roleUser_role")
    public List<Role> roles;
    public UnitDto unit;
}
