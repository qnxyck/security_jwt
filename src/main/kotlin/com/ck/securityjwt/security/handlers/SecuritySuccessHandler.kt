package com.ck.securityjwt.security.handlers

import com.ck.securityjwt.common.utils.JsonUtils
import com.ck.securityjwt.common.utils.TokenFormat
import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.security.UserDetailsImpl
import com.ck.securityjwt.security.config.SecurityConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登录成功处理
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@Component
class SecuritySuccessHandler : AuthenticationSuccessHandler {

    companion object {
        private val log = LoggerFactory.getLogger(SecuritySuccessHandler::class.java)
    }

    // 配置信息
    @Autowired
    private lateinit var securityConfig: SecurityConfig

    @Autowired
    private lateinit var tokenFormat: TokenFormat

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, auth: Authentication) {
        val udi = auth.principal as UserDetailsImpl
        // 生成token
        val jwtToken = tokenFormat.createJwtToken(udi.username, "CK", "")
        // 绑定到header
        response.setHeader(securityConfig.tokenHeader, jwtToken)
        // 返回登录成功后生成的Token
        JsonUtils.sendJson(response, this.objectMapper.writeValueAsString(R<Any>(data = jwtToken)))
        log.debug("{} : 登录成功", udi.username)
    }

}