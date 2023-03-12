package com.day.authority.core.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControllerLogDto {

    private String resJson;

    private Long runTime;

    private Object[] args;

}