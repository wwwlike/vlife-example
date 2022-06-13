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

import cn.wwwlike.example.entity.User;
import cn.wwwlike.vlife.annotation.VField;
import cn.wwwlike.vlife.base.VoBean;
import cn.wwwlike.vlife.query.tran.YearExpressTran;
import lombok.Data;

/**
 * 列表查询
 * @author xiaoyu
 * @date 2022/6/4
 */
@Data
public class UserVo implements VoBean<User> {
    public String id;
    /**
     * 转换
     */
    @VField(pathName = "joinDate",tran = YearExpressTran.class)
    public Integer joinYear;
    /**
     * 当前表信息
     */
    public String name;
    public String tel;
    @VField(pathName = "idno")
    public String idNumber;
    /**
     * 外键表属性打平
     */
    public String unit_name;
    /**
     * 间接外键表属性打平
     */
    @VField(pathName = "unit_area_areaName")
    public String areaName;
}
