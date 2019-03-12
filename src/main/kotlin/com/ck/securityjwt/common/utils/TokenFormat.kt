package com.ck.securityjwt.common.utils

import com.ck.securityjwt.security.config.SecurityConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

/**
 * Token Utils
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Component
class TokenFormat {

    @Autowired
    private lateinit var securityConfig: SecurityConfig

    /**
     * 生成Token
     *
     * @param id      编号
     * @param issuer  该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的；
     * @return token String
     * @author QianNianXiaoYao
     */
    fun createJwtToken(id: String, issuer: String, subject: String): String {

        // 生成签发时间
        val nowMillis = Date()
        // 设置JWT声明
        val builder = Jwts.builder().setId(id)
                // iat: jwt的签发时间
                .setIssuedAt(nowMillis)
                // 代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userId，roleId之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                .setIssuer(issuer)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(securityConfig.processedSignatureAlgorithm(),
                        SecretKeySpec(DatatypeConverter.parseBase64Binary(securityConfig.secret), securityConfig.processedSignatureAlgorithm().jcaName))

        // 设置过期时间
        builder.setExpiration(Date(nowMillis.time + securityConfig.expireDate))

        // 构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact()

    }

    /**
     * 解析和读取JWT
     *
     * @param jwt token
     * @return Claims
     * @author QianNianXiaoYao
     */
    fun parseJWT(jwt: String): Claims {
        // 如果它不是签名的JWS（如预期的那样），则该行将抛出异常
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(securityConfig.secret))
                .parseClaimsJws(jwt)
                .body
    }

    /**
     * 更新token过期时间
     *
     * @param token tokenStr
     * @return update token
     * @author QianNianXiaoYao
     */
    fun updateTokenTime(token: String): String {
        val parseJWT = parseJWT(token)
        return createJwtToken(parseJWT.id, parseJWT.issuer, parseJWT.subject)
    }

}