package com.day.authority.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DayXhYao
 * @date 2023/3/11 10:45
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200, "访问成功"),

    /**
     * 错误参数
     */
    PARAM_ERROR(400, "错误参数"),

    /**
     * 无效资源
     */
    NOT_FOUND_RESOURCE(404, "I don't have what you requested"),

    /**
     * 失败 默认
     */
    FAIL(500, "Oh, shit, there's something wrong with my program"),

    /**
     * 参数为空
     */
    PARAM_IS_NULL(600, "I guess there's no bullet in your gun");

    private Integer code;
    private String message;
}
