-- Copyright 2026 上海如静知华信息科技有限公司
CREATE TABLE IF NOT EXISTS request_plan_history(id BIGINT PRIMARY KEY AUTO_INCREMENT,http_method VARCHAR(12),target_host VARCHAR(255),risk_level VARCHAR(16),created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
