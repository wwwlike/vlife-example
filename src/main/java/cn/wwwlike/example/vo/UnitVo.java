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

import cn.wwwlike.example.entity.Unit;
import cn.wwwlike.vlife.annotation.VField;
import cn.wwwlike.vlife.base.VoBean;
import cn.wwwlike.vlife.query.tran.SubStrTran;
import lombok.Data;

/**
 * 类说明
 *
 * @author xiaoyu
 * @date 2022/6/4
 */
@Data
public class UnitVo implements VoBean<Unit> {
    public String id;
    /**
     * 机构名称，使用了别名
     */
    @VField(pathName = "name")
    public String unitname;

    /**
     * 机构搜在地区编码，使用了别名
     */
    @VField(pathName = "area_areaCode")
    public String unitArea;

}
