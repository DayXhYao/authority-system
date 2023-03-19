package com.day.authority.server.model.log.infrastructure.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.day.authority.server.model.log.domain.entity.ApiLogHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:48
 */
@Mapper
public interface ApiLogHistoryMapper extends BaseMapper<ApiLogHistoryEntity> {


}
