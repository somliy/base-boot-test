package com.donger.baseboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Shiro配置文件处理类
 *
 * @author lizhi
 */
@Component
@ConfigurationProperties(prefix = "security.shiro")
public class ShiroConfigProperties {
    /**
     * 免登陆的接口
     */
    private List<String> anonUrls;

    public List<String> getAnonUrls() {
        return anonUrls;
    }

    public void setAnonUrls(List<String> anonUrls) {
        this.anonUrls = anonUrls;
    }
}
