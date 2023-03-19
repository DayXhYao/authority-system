package com.day.authority.server.model.company.domain.factory;

import com.day.authority.server.model.company.domain.CompanyDomain;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 17:55
 */
public class CompanyFactory {

    public static CompanyDomain createCompany(CompanyEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return new CompanyDomain(entity);
    }

}
