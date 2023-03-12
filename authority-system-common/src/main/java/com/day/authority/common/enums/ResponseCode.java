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
    NOT_FOUND_PAGE(404, "这儿没你要的东西!"),

    /**
     * 失败 默认
     */
    FAIL(500, "服务器出小差了...");

    private Integer code;
    private String message;
}
