package com.day.authority.server.model.company.application.service.impl;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.dto.Result;
import com.day.authority.common.enums.ResponseCode;
import com.day.authority.common.util.CompareUtil;
import com.day.authority.server.model.company.application.assembler.CompanyServiceAssembler;
import com.day.authority.server.model.company.application.dto.CompanyInfoDetailDto;
import com.day.authority.server.model.company.application.service.CompanyService;
import com.day.authority.server.model.company.domain.CompanyDomain;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;
import com.day.authority.server.model.company.domain.factory.CompanyFactory;
import com.day.authority.server.model.company.domain.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:38
 */
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public Result<Boolean> modifyCompany(CompanyInfoDetailDto companyInfo) {
        if (Objects.isNull(companyInfo) || Objects.isNull(companyInfo.getCompanyId()) || CompareUtil.lessAndEquals(companyInfo.getCompanyId(), CommonConstant.LONG_ZERO)) {
            return Result.fail(ResponseCode.PARAM_IS_NULL, Boolean.FALSE);
        }

        CompanyDomain company = companyRepository.getCompany(companyInfo.getCompanyId());
        if (Objects.isNull(company)) {
            return Result.fail(ResponseCode.NOT_FOUND_RESOURCE, Boolean.FALSE);
        }

        company.updateCompanyInfo(CompanyServiceAssembler.convertDto(companyInfo));
        if (companyRepository.saveCompany(company)) {
            return Result.success(Boolean.TRUE);
        } else {
            return Result.fail(ResponseCode.FAIL, Boolean.FALSE);
        }
    }

    @Override
    public Result<Boolean> saveCompany(CompanyInfoDetailDto companyInfo) {
        CompanyDomain company = CompanyFactory.createCompany(new CompanyEntity());

        company.updateCompanyInfo(CompanyServiceAssembler.convertDto(companyInfo));

        if (companyRepository.saveCompany(company)) {
            return Result.success(Boolean.TRUE);
        } else {
            return Result.fail(ResponseCode.FAIL, Boolean.FALSE);
        }
    }
}
