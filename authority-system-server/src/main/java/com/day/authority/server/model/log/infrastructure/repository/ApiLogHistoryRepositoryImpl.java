package com.day.authority.server.model.log.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.day.authority.server.model.log.domain.entity.ApiLogHistoryEntity;
import com.day.authority.server.model.log.domain.repository.ApiLogHistoryRepository;
import com.day.authority.server.model.log.infrastructure.database.mysql.mapper.ApiLogHistoryMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:48
 */
@Repository
public class ApiLogHistoryRepositoryImpl extends ServiceImpl<ApiLogHistoryMapper, ApiLogHistoryEntity> implements ApiLogHistoryRepository {


    @Async
    @Override
    public void saveApiLogHistoryAsync(ApiLogHistoryEntity entity) {
        if (Objects.isNull(entity)) {
            return;
        }

        try {
            save(entity);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
