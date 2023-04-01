package com.day.authority.server.model.company.application.service;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.dto.Result;
import com.day.authority.server.model.app.application.dto.AppInfoDetailDto;
import com.day.authority.server.model.app.application.service.AppService;
import com.day.authority.server.model.company.application.dto.CompanyInfoDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author DayXhYao
 * @date 2023/4/1 16:59
 */
@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;


    @Test
    public void modifyAppTest() {
        CompanyInfoDetailDto dto = new CompanyInfoDetailDto();
        dto.setCode("BAI_DU");
        dto.setCompanyId(CommonConstant.INT_FIVE.longValue());
        dto.setName("百度贴吧aaa");
        dto.setDomainName("https://tieba.baidu.com/index.html");
        dto.setIsGroup(false);
        dto.setAddress("那就");
        Result<Boolean> booleanResult = companyService.modifyCompany(dto);
        Assert.isTrue(Result.isSuccess(booleanResult), "保存失败");
    }


    @Test
    public void saveAppTest() {
        CompanyInfoDetailDto dto = new CompanyInfoDetailDto();
        dto.setCode("BAI_DU");
        dto.setCompanyId(CommonConstant.INT_TWO.longValue());
        dto.setName("百度贴吧");
        dto.setDomainName("https://tieba.baidu.com/index.html");
        dto.setIsGroup(true);
        dto.setAddress("北京");

        Result<Boolean> booleanResult = companyService.saveCompany(dto);
        Assert.isTrue(Result.isSuccess(booleanResult), "保存失败");
    }

}
