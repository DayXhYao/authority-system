package com.day.authority.core.log.service;

import com.day.authority.core.log.ControllerLogDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:05
 */
public interface ControllerLogAopPostProcess {

    /**
     * 对接口日志进行后置处理，可以选择异步或者同步，由实现者决定 建议异步
     *
     * @param request
     * @param controllerLog
     */
    void postProcess(HttpServletRequest request, ControllerLogDto controllerLog);

}
