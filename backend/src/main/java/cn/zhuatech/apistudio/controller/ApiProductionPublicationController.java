/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apistudio.controller;

import cn.zhuatech.apistudio.service.ApiProductionPublicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/apistudio")
public class ApiProductionPublicationController {
    private final ApiProductionPublicationService service;
    public ApiProductionPublicationController(ApiProductionPublicationService service) { this.service = service; }
    @PostMapping("/api-production-publication")
    public ApiProductionPublicationService.Assessment assess(
            @Valid @RequestBody ApiProductionPublicationService.Request request) {
        return service.assess(request);
    }
}
