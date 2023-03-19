package com.day.authority.server.model.company.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;
import com.day.authority.server.model.company.domain.repository.CompanyRepository;
import com.day.authority.server.model.company.infrastructure.database.mysql.mapper.CompanyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:41
 */
@Repository
public class CompanyRepositoryImpl extends ServiceImpl<CompanyMapper, CompanyEntity> implements CompanyRepository {



}
