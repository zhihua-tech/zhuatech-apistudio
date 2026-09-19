/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apistudio.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ApiProductionPublicationServiceTest {
    private final ApiProductionPublicationService service = new ApiProductionPublicationService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void publishesControlledApi() {
        var r = service.assess(new ApiProductionPublicationService.Request("v1", true, true, true,
                true, true, true, 95, true, true, true, true, true));
        assertThat(r.decision()).isEqualTo(ApiProductionPublicationService.Decision.PUBLISH);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void canariesOperationalGaps() {
        var r = service.assess(new ApiProductionPublicationService.Request("v2", true, true, true,
                true, true, true, 95, true, false, false, false, true));
        assertThat(r.actions()).hasSize(3);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnsafeApi() {
        var r = service.assess(new ApiProductionPublicationService.Request("v3", false, false, false,
                false, false, false, 30, false, true, true, true, false));
        assertThat(r.blockers()).hasSize(9);
    }
}
