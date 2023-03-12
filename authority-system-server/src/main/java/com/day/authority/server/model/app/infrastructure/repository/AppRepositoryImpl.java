package com.day.authority.server.model.app.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.util.CompareUtil;
import com.day.authority.server.model.app.domain.AppDomain;
import com.day.authority.server.model.app.domain.entity.AppEntity;
import com.day.authority.server.model.app.domain.factory.AppFactory;
import com.day.authority.server.model.app.domain.repository.AppRepository;
import com.day.authority.server.model.app.infrastructure.database.mysql.mapper.AppMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:40
 */
@Service
public class AppRepositoryImpl extends ServiceImpl<AppMapper, AppEntity> implements AppRepository {



    @Override
    public AppDomain getApp(Long appId) {
        if (Objects.isNull(appId) || CompareUtil.lessAndEquals(appId, CommonConstant.LONG_ZERO)) {
            return null;
        }

        AppEntity appEntity = getById(appId);
        if (Objects.isNull(appEntity)) {
            return null;
        }
        return AppFactory.createApp(appEntity);
    }

    @Override
    public Boolean saveApp(AppDomain appDomain) {
        if (Objects.isNull(appDomain) || Objects.isNull(appDomain.getApp())) {
            return Boolean.FALSE;
        }
        return saveOrUpdate(appDomain.getApp());
    }
}
