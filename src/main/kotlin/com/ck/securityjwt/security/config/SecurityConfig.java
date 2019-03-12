package com.ck.securityjwt.security.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Security 配置类
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/10
 */
@ConfigurationProperties(prefix = "ck.jwt")
@Configuration
@Component
public class SecurityConfig {

    /**
     * 支持的签名算法
     * <p>
     * none
     * <p>
     * PS384
     * ES384
     * RS384
     * HS256
     * HS512
     * ES256
     * RS256
     * HS384
     * ES512
     * PS256
     * PS512
     * RS512
     */
    private static final Map<String, SignatureAlgorithm> signatureAlgorithmMap = Stream.of(SignatureAlgorithm.values())
            .collect(Collectors.toMap(SignatureAlgorithm::getValue, o -> o, (o1, o2) -> o1));

    private String tokenHeader = "Authorization";

    // 过期时间(默认三十分钟)
    private Long expireDate = 1800000L;

    // 签名密钥
    private String secret = "token";

    // 签名算法
    private String signatureAlgorithm = "HS256";

    // 登录地址
    private String loginUrl = "/login";

    // 退出地址
    private String logoutUrl = "/logout";

    // 登录时用户名参数
    private String usernameParameter = "username";

    // 登录时密码参数
    private String passwordParameter = "password";

    /**
     * 获取签名算法
     *
     * @return SignatureAlgorithm
     * @author QianNianXiaoYao
     */
    public SignatureAlgorithm processedSignatureAlgorithm() {
        SignatureAlgorithm algorithm = signatureAlgorithmMap.get(this.signatureAlgorithm.toUpperCase());
        return algorithm == null ? SignatureAlgorithm.HS256 : algorithm;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }
}
