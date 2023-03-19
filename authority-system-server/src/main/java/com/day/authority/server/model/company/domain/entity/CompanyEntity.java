package com.day.authority.server.model.company.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.day.authority.server.common.dto.BaseEntity;
import lombok.Data;

/**
 * @author DayXhYao
 * @date 2023/3/12 16:45
 */
@Data
@TableName("sys_company")
public class CompanyEntity extends BaseEntity {


    private String companyCode;

    private String companyName;

    private String domainName;

    private String address;

    private String isGroup;

}
