package com.day.authority.server.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DayXhYao
 * @date 2023/3/12 16:12
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableLogic
    private String isValid;

    private String createdUser;

    private String updatedUser;

    /**
     * 禁止代码中手动设置创建时间和修改时间，统一使用Mysql触发器
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;

    /**
     * 禁止代码中手动设置创建时间和修改时间，统一使用Mysql触发器
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedDate;

}
