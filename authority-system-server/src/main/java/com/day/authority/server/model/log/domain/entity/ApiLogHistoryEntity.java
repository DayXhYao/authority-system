package com.day.authority.server.model.log.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:46
 */
@Data
@TableName("api_log_hisotry")
public class ApiLogHistoryEntity {

    /**
     * 协议
     */
    private String protocol;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口号
     */
    private Integer port;


    /**
     * 请求url
     */
    private String requestUrl;


    /**
     * 请求方法类型
     */
    private String requestMethod;


    /**
     * 线程名称
     */
    private String threadName;


    /**
     * 使用时间
     */
    private Long useTime;


    /**
     * 请求参数
     */
    private String requestBody;


    /**
     * 返回参数
     */
    private String responseBody;


    /**
     * 内容类型
     */
    private String contentType;


    /**
     * 创建人
     */
    private String createdUser;


    /**
     * 创建时间
     */
    private Date createdDate;

}
