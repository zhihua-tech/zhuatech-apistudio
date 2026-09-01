/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apistudio.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiProductionPublicationService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.ownerAssigned()) blockers.add("API 责任人未指定");
        if (!request.openapiValidated()) blockers.add("OpenAPI 契约未通过校验");
        if (!request.versioningPolicyApplied()) blockers.add("版本与弃用策略未应用");
        if (!request.authenticationAndAuthorizationTested()) blockers.add("认证授权未通过测试");
        if (!request.requestResponseSchemaValidated()) blockers.add("请求响应模型未验证");
        if (!request.sensitiveDataScanPassed()) blockers.add("敏感数据扫描未通过");
        if (request.automatedTestCoveragePercent() < 80) blockers.add("自动化测试覆盖率低于 80%");
        if (!request.backwardCompatibilityPassed()) blockers.add("向后兼容性未通过");
        if (!request.finalApprovalComplete()) blockers.add("生产发布审批未完成");
        if (!blockers.isEmpty()) {
            actions.add("阻断 API 发布并完成契约、安全与测试整改");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.rateLimitConfigured() || !request.monitoringReady() || !request.rollbackReady()) {
            if (!request.rateLimitConfigured()) actions.add("配置租户配额、限流和突发保护");
            if (!request.monitoringReady()) actions.add("配置可用性、延迟、错误率和审计监控");
            if (!request.rollbackReady()) actions.add("准备旧版本路由与快速回滚");
            return new Assessment(Decision.CANARY, blockers, actions);
        }
        actions.add("批准 API 生产发布并记录契约、网关策略和审批版本");
        return new Assessment(Decision.PUBLISH, blockers, actions);
    }

    public record Request(@NotBlank String apiVersion, boolean ownerAssigned, boolean openapiValidated,
                          boolean versioningPolicyApplied, boolean authenticationAndAuthorizationTested,
                          boolean requestResponseSchemaValidated, boolean sensitiveDataScanPassed,
                          @Min(0) @Max(100) int automatedTestCoveragePercent,
                          boolean backwardCompatibilityPassed, boolean rateLimitConfigured,
                          boolean monitoringReady, boolean rollbackReady, boolean finalApprovalComplete) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { PUBLISH, CANARY, BLOCKED }
}
