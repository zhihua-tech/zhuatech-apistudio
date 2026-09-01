# 企业级 API 生产发布

`POST /api/enterprise/apistudio/api-production-publication` 检查责任人、OpenAPI 契约、版本策略、认证授权、数据模型、敏感数据、测试覆盖率、兼容性、限流、监控和回滚，返回 `PUBLISH / CANARY / BLOCKED`。

生产环境应对接 API 网关、CI/CD、密钥管理和可观测平台，并将契约与网关策略作为同一发布版本管理。
