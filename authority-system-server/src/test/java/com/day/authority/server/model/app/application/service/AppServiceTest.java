package com.day.authority.server.model.app.application.service;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.dto.Result;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author DayXhYao
 * @date 2023/4/1 16:59
 */
@SpringBootTest
public class AppServiceTest {

    @Autowired
    private AppService appService;


    @Test
    public void modifyAppTest() {
        AppInfoDetailDto dto = new AppInfoDetailDto();
        dto.setCode("BAI_DU_TIEBA");
        dto.setAppId(CommonConstant.INT_TWO.longValue());
        dto.setName("百度贴吧");
        dto.setUrl("https://tieba.baidu.com/index.html");

        Result<Boolean> booleanResult = appService.modifyApp(dto);
        Assert.isTrue(Result.isSuccess(booleanResult), "保存失败");
    }


    @Test
    public void saveAppTest() {
        AppInfoDetailDto dto = new AppInfoDetailDto();
        dto.setCode("222");
        dto.setAppId(CommonConstant.INT_ONE.longValue());
        dto.setName("测试应用");
        dto.setUrl("www.baidu.com");

        Result<Boolean> booleanResult = appService.saveApp(dto);
        Assert.isTrue(Result.isSuccess(booleanResult), "保存失败");
    }

}
