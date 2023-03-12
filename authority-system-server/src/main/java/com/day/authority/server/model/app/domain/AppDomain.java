package com.day.authority.server.model.app.domain;

import com.day.authority.server.model.app.domain.entity.AppEntity;
import com.day.authority.server.model.app.domain.objectvalue.AppObjectValue;
import lombok.Data;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:39
 */
@Data
public class AppDomain {


    private AppEntity app;

    public AppDomain(AppEntity app) {
        this.app = app;
    }

    public void setApp(AppEntity app) {
        throw new Error("Oh Baby, that's not good");
    }


    public boolean updateAppInfo(AppObjectValue appObjectValue) {
        this.app.setAppUrl(appObjectValue.getUrl());
        this.app.setAppCode(appObjectValue.getCode());
        this.app.setAppName(appObjectValue.getName());
        return true;
    }


}
