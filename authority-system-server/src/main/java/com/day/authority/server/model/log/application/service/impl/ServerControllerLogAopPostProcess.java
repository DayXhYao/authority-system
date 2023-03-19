package com.day.authority.server.model.log.application.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import com.day.authority.common.util.JsonUtil;
import com.day.authority.core.log.ControllerLogDto;
import com.day.authority.core.log.service.ControllerLogAopPostProcess;
import com.day.authority.server.model.log.domain.entity.ApiLogHistoryEntity;
import com.day.authority.server.model.log.domain.repository.ApiLogHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:50
 */
@Component
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ServerControllerLogAopPostProcess implements ControllerLogAopPostProcess {

    private final ApiLogHistoryRepository apiLogHistoryRepository;


    @Override
    public void postProcess(HttpServletRequest request, ControllerLogDto controllerLog) {
        ApiLogHistoryEntity apiLogHistoryEntity = packageApiLogEntity(request, controllerLog);
        apiLogHistoryEntity.setUseTime(controllerLog.getRunTime());
        apiLogHistoryEntity.setResponseBody(controllerLog.getResJson());
        //异步存储
        apiLogHistoryRepository.saveApiLogHistoryAsync(apiLogHistoryEntity);
    }


    /**
     * 从request获取请求信息
     *
     * @param request
     * @return
     */
    private ApiLogHistoryEntity packageApiLogEntity(HttpServletRequest request, ControllerLogDto controllerLogDto) {
        ApiLogHistoryEntity entity = new ApiLogHistoryEntity();
        entity.setProtocol(request.getProtocol());
        entity.setIp(ServletUtil.getClientIP(request));
        entity.setPort(request.getRemotePort());
        entity.setRequestMethod(request.getMethod());
        entity.setRequestUrl(Optional.ofNullable(request.getRequestURL()).toString());
        entity.setRequestBody(JsonUtil.toJson(controllerLogDto.getArgs()));
        entity.setThreadName(Thread.currentThread().getName());
        entity.setContentType(request.getContentType());
        entity.setCreatedUser("system");
        return entity;
    }

}
