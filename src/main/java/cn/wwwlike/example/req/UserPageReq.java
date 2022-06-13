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

package cn.wwwlike.example.req;

import cn.wwwlike.example.entity.User;
import cn.wwwlike.vlife.annotation.VClazz;
import cn.wwwlike.vlife.annotation.VField;
import cn.wwwlike.vlife.dict.Opt;
import cn.wwwlike.vlife.query.req.PageQuery;
import cn.wwwlike.vlife.query.tran.YearExpressTran;
import lombok.Data;

import java.util.List;

/**
 * 用户分页查询条件
 *
 * @author xiaoyu
 * @date 2022/6/4
 */
@VClazz(orders ="joinDate_desc" )
@Data
public class UserPageReq extends PageQuery<User> {
    /**
     * 3个字段 or联合 like查询
     */
    @VField(pathName = "name",orReqFields = {"idno","tel"},opt = Opt.like)
    public String search;
    /**
     * 入职日期根据年度转换查询
     */
    @VField(pathName = "joinDate",tran = YearExpressTran.class)
    public Integer joinYear;

    /**
     * 多对1(间接)条件查询
     * 根据外键表unit的外键表area的地区编码查询（产生2个leftJoin）
     */
    public String unit_area_areaCode;

    /**
     * 1对多In查询
     * 子查询内部里过滤
     */
    @VField(pathName = "roleUser_roleId")
    public String roleId;

    /**
     * 多对多，子查询过滤
     */
    @VField(pathName = "roleUser_role_code")
    public String roleCode;
}
