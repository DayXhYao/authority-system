package com.day.authority.server.model.company.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.util.CompareUtil;
import com.day.authority.server.model.company.domain.CompanyDomain;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;
import com.day.authority.server.model.company.domain.factory.CompanyFactory;
import com.day.authority.server.model.company.domain.repository.CompanyRepository;
import com.day.authority.server.model.company.infrastructure.database.mysql.mapper.CompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:41
 */
@Repository
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRepositoryImpl extends ServiceImpl<CompanyMapper, CompanyEntity> implements CompanyRepository {


    @Override
    public CompanyDomain getCompany(Long companyId) {
        if (Objects.isNull(companyId) || CompareUtil.lessAndEquals(companyId, CommonConstant.LONG_ZERO)) {
            return null;
        }

        CompanyEntity company = getById(companyId);
        if (Objects.isNull(company)) {
            return null;
        }
        return CompanyFactory.createCompany(company);
    }

    @Override
    public Boolean saveCompany(CompanyDomain companyDomain) {
        if (Objects.isNull(companyDomain) || Objects.isNull(companyDomain.getCompany())) {
            return Boolean.FALSE;
        }
        return saveOrUpdate(companyDomain.getCompany());
    }
}
