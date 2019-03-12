package com.ck.securityjwt.security.handlers

import com.ck.securityjwt.security.config.SecurityConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 退出处理
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@Component
class SecurityLogoutSuccessHandler : LogoutSuccessHandler {

    @Autowired
    private lateinit var securityConfig: SecurityConfig

    companion object {
        private val log = LoggerFactory.getLogger(SecurityLogoutSuccessHandler::class.java)
    }

    override fun onLogoutSuccess(request: HttpServletRequest, response: HttpServletResponse?, authentication: Authentication?) {
        val userName = request.getParameter(securityConfig.usernameParameter) ?: ""

        log.info("$userName  退出登录")
    }


}