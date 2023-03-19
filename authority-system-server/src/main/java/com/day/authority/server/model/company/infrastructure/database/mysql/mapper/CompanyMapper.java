package com.day.authority.server.model.company.infrastructure.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.day.authority.server.model.company.domain.entity.CompanyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:42
 */
@Mapper
public interface CompanyMapper extends BaseMapper<CompanyEntity> {
}
