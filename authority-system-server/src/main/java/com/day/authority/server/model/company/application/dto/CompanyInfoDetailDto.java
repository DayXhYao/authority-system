package com.day.authority.server.model.company.application.dto;

import lombok.Data;

/**
 * @author DayXhYao
 * @date 2023/4/1 16:00
 */
@Data
public class CompanyInfoDetailDto {

    private Long companyId;

    private String code;

    private String name;

    private String domainName;

    private String address;

    private Boolean isGroup;

}
