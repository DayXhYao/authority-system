package com.day.authority.server.model.company.application.assembler;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.util.BeanCopierUtil;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;
import com.day.authority.server.model.app.domain.objectvalue.AppObjectValue;
import com.day.authority.server.model.company.application.dto.CompanyInfoDetailDto;
import com.day.authority.server.model.company.domain.objectvalue.CompanyObjectValue;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:43
 */
public class CompanyServiceAssembler {

    public static CompanyObjectValue convertDto(CompanyInfoDetailDto detail) {
        CompanyObjectValue companyObjectValue = BeanCopierUtil.copy(detail, CompanyObjectValue.class);
        companyObjectValue.setIsGroup(detail.getIsGroup() ? CommonConstant.STR_Y : CommonConstant.STR_N);
        return companyObjectValue;
    }

}
