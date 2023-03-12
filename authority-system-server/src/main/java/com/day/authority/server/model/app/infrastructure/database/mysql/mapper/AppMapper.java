package com.day.authority.server.model.app.infrastructure.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.day.authority.server.model.app.domain.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author DayXhYao
 * @date 2023/3/12 13:42
 */
@Mapper
public interface AppMapper extends BaseMapper<AppEntity> {
}
