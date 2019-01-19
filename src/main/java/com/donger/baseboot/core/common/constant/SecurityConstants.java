package com.donger.baseboot.core.common.constant;

/**
 * 安全认证配置类
 *
 * @author xyx
 */
public interface SecurityConstants {
    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 密码模式
     */
    String PASSWORD = "password";
    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";

    /**
     * jwt签名
     */
    String SIGN_KEY = "chillax";
    /**
     * 请求头
     */
    String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    String TOKEN_SPLIT = "Bearer ";
}
