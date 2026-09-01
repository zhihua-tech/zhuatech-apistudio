/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apistudio.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiProductionPublicationServiceTest {
    private final ApiProductionPublicationService service = new ApiProductionPublicationService();
    @Test void publishesControlledApi() {
        var r = service.assess(new ApiProductionPublicationService.Request("v1", true, true, true,
                true, true, true, 95, true, true, true, true, true));
        assertThat(r.decision()).isEqualTo(ApiProductionPublicationService.Decision.PUBLISH);
    }
    @Test void canariesOperationalGaps() {
        var r = service.assess(new ApiProductionPublicationService.Request("v2", true, true, true,
                true, true, true, 95, true, false, false, false, true));
        assertThat(r.actions()).hasSize(3);
    }
    @Test void blocksUnsafeApi() {
        var r = service.assess(new ApiProductionPublicationService.Request("v3", false, false, false,
                false, false, false, 30, false, true, true, true, false));
        assertThat(r.blockers()).hasSize(9);
    }
}
