package com.day.authority.server.model.company.application.service;

import com.day.authority.common.dto.Result;
import com.day.authority.server.model.company.application.dto.CompanyInfoDetailDto;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:38
 */
public interface CompanyService {


    /**
     * 修改公司信息
     * @param companyInfo 公司信息
     * @return 返回通用DTO
     */
    Result<Boolean> modifyCompany(CompanyInfoDetailDto companyInfo);


    /**
     * 保存公司信息
     * @param companyInfo
     * @return
     */
    Result<Boolean> saveCompany(CompanyInfoDetailDto companyInfo);


}
