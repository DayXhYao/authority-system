package com.day.authority.server.model.app.application.service.impl;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.dto.Result;
import com.day.authority.common.enums.ResponseCode;
import com.day.authority.common.util.BeanCopierUtil;
import com.day.authority.common.util.CompareUtil;
import com.day.authority.server.model.app.application.assembler.AppServiceAssembler;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;
import com.day.authority.server.model.app.application.service.AppService;
import com.day.authority.server.model.app.domain.AppDomain;
import com.day.authority.server.model.app.domain.entity.AppEntity;
import com.day.authority.server.model.app.domain.factory.AppFactory;
import com.day.authority.server.model.app.domain.objectvalue.AppObjectValue;
import com.day.authority.server.model.app.domain.repository.AppRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:38
 */
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AppServiceImpl implements AppService {

    private final AppRepository appRepository;


    @Override
    public Result<Boolean> modifyApp(AppInfoDetailDto appInfo) {
        if (Objects.isNull(appInfo) || Objects.isNull(appInfo.getAppId()) || CompareUtil.lessAndEquals(appInfo.getAppId(), CommonConstant.LONG_ZERO)) {
            return Result.fail(ResponseCode.PARAM_IS_NULL, Boolean.FALSE);
        }

        AppDomain app = appRepository.getApp(appInfo.getAppId());
        if (Objects.isNull(app)) {
            return Result.fail(ResponseCode.NOT_FOUND_RESOURCE, Boolean.FALSE);
        }

        app.updateAppInfo(AppServiceAssembler.convertDto(appInfo));
        if (appRepository.saveApp(app)) {
            return Result.success(Boolean.TRUE);
        } else {
            return Result.fail(ResponseCode.FAIL, Boolean.FALSE);
        }
    }

    @Override
    public Result<Boolean> saveApp(AppInfoDetailDto appInfo) {
        AppDomain app = AppFactory.createApp(new AppEntity());
        app.updateAppInfo(BeanCopierUtil.copy(appInfo, AppObjectValue.class));

        if (appRepository.saveApp(app)) {
            return Result.success(Boolean.TRUE);
        } else {
            return Result.fail(ResponseCode.FAIL, Boolean.FALSE);
        }
    }
}
