package com.day.authority.server.model.log.domain.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.day.authority.server.model.log.domain.entity.ApiLogHistoryEntity;

/**
 * @author DayXhYao
 * @date 2023/3/19 11:47
 */
public interface ApiLogHistoryRepository extends IService<ApiLogHistoryEntity> {


    /**
     * 异步保存日志记录
     * @param entity
     */
    void saveApiLogHistoryAsync(ApiLogHistoryEntity entity);

}
