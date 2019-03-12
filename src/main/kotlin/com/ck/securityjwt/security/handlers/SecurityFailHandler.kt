package com.ck.securityjwt.security.handlers

import com.ck.securityjwt.common.utils.JsonUtils
import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.model.enums.ResultStatusCodes
import com.ck.securityjwt.security.config.SecurityConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登录失败处理
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@Component
class SecurityFailHandler : AuthenticationFailureHandler {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var securityConfig: SecurityConfig

    companion object {
        private val log = LoggerFactory.getLogger(SecurityFailHandler::class.java)
    }

    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException) {
        log.debug("登录失败用户名称: ${request.getParameter(securityConfig.usernameParameter) ?: ""}")
        val userName = request.getParameter(securityConfig.usernameParameter)
        when (e) {
            // 无权限 坏凭证异常
            is UsernameNotFoundException, is BadCredentialsException -> {
                JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.WRONG_USER_NAME_OR_PASSWORD)))
                log.debug("登录失败, 账号或密码错误. :$userName")
            }
            // 用户被禁用
            is DisabledException -> {
                JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.USER_DISABLED)))
                log.debug("登录失败, 该用户被禁用. :$userName")
            }
            // 登录失败
            else -> {
                JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.SYSTEM_ERR)))
                log.error("登录失败 $userName", e)
            }
        }
    }


}
