package com.day.authority.common.annotations;

import java.lang.annotation.*;

/**
 * 保存接口日志信息
 * @author DayXhYao
 * @date 2023/3/12 12:58
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaveApiLog {
}
