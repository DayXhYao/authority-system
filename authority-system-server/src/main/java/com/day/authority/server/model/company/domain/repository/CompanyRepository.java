package com.day.authority.server.model.company.domain.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.day.authority.server.model.app.domain.AppDomain;
import com.day.authority.server.model.company.domain.CompanyDomain;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:40
 */
public interface CompanyRepository extends IService<CompanyEntity> {


    /**
     * 通过公司Id获取公司域
     * @param companyId 公司Id
     * @return 返回公司域
     */
    CompanyDomain getCompany(Long companyId);


    /**
     * 保存应用信息
     * @param companyDomain 应用域
     * @return 返回修改结果
     */
    Boolean saveCompany(CompanyDomain companyDomain);

}
