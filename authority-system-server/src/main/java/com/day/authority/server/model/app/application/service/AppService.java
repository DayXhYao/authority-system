package com.day.authority.server.model.app.application.service;

import com.day.authority.common.dto.Result;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:38
 */
public interface AppService {


    /**
     * 修改应用信息
     * @param appInfo 应用信息
     * @return 返回通用DTO
     */
    Result<Boolean> updateApp(AppInfoDetailDto appInfo);



}
