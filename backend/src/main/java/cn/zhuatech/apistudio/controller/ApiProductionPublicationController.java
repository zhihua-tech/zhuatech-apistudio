/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apistudio.controller;

import cn.zhuatech.apistudio.service.ApiProductionPublicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/apistudio")
public class ApiProductionPublicationController {
    private final ApiProductionPublicationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ApiProductionPublicationController(ApiProductionPublicationService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/api-production-publication")
    public ApiProductionPublicationService.Assessment assess(
            @Valid @RequestBody ApiProductionPublicationService.Request request) {
        return service.assess(request);
    }
}
