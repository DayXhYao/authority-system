package com.day.authority.server.model.app.application.assembler;

import com.day.authority.common.util.BeanCopierUtil;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;
import com.day.authority.server.model.app.domain.objectvalue.AppObjectValue;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:43
 */
public class AppServiceAssembler {

    public static AppObjectValue convertDto(AppInfoDetailDto detail) {
        return BeanCopierUtil.copy(detail, AppObjectValue.class);
    }



}
