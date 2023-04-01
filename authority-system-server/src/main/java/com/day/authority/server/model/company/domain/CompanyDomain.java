package com.day.authority.server.model.company.domain;

import com.day.authority.server.model.company.domain.entity.CompanyEntity;
import com.day.authority.server.model.company.domain.objectvalue.CompanyObjectValue;
import lombok.Getter;

/**
 * @author DayXhYao
 * @date 2023/3/12 16:45
 */
@Getter
public class CompanyDomain {


    private CompanyEntity company;

    public CompanyDomain(CompanyEntity company) {
        this.company = company;
    }

    public void setCompany(CompanyEntity company) {
        throw new Error("Oh Baby, that's not good");
    }


    public boolean updateCompanyInfo(CompanyObjectValue appObjectValue) {
        this.company.setDomainName(appObjectValue.getDomainName());
        this.company.setCompanyCode(appObjectValue.getCode());
        this.company.setCompanyName(appObjectValue.getName());
        this.company.setIsGroup(appObjectValue.getIsGroup());
        this.company.setAddress(appObjectValue.getAddress());
        return true;
    }

}
