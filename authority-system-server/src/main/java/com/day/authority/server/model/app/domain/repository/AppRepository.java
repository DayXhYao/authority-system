package com.day.authority.server.model.app.domain.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.day.authority.server.model.app.domain.AppDomain;
import com.day.authority.server.model.app.domain.entity.AppEntity;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:40
 */
public interface AppRepository extends IService<AppEntity> {


    /**
     * 通过AppId获取App
     * @param appId 应用Id
     * @return 返回应用域
     */
    AppDomain getApp(Long appId);


    /**
     * 保存应用信息
     * @param appDomain 应用域
     * @return 返回修改结果
     */
    Boolean saveApp(AppDomain appDomain);


}
