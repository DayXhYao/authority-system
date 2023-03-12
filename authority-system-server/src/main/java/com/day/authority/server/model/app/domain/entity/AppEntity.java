package com.day.authority.server.model.app.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.day.authority.server.common.dto.BaseEntity;
import lombok.Data;

/**
 * @author DayXhYao
 * @date 2023/3/12 16:45
 */
@Data
@TableName("sys_app")
public class AppEntity extends BaseEntity {


    private String appCode;

    private String appName;

    private String appUrl;

}
