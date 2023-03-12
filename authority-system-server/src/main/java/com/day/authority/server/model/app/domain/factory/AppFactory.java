package com.day.authority.server.model.app.domain.factory;

import com.day.authority.server.model.app.domain.AppDomain;
import com.day.authority.server.model.app.domain.entity.AppEntity;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 17:55
 */
public class AppFactory {

    public static AppDomain createApp(AppEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return new AppDomain(entity);
    }

}
