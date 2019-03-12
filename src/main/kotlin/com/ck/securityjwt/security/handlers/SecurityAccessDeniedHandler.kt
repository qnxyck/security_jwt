package com.ck.securityjwt.security.handlers

import com.ck.securityjwt.common.utils.JsonUtils
import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.model.enums.ResultStatusCodes
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 访问拒绝处理程序
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@Component
class SecurityAccessDeniedHandler : AccessDeniedHandler {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        private val log = LoggerFactory.getLogger(SecurityAccessDeniedHandler::class.java)
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: AccessDeniedException?) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        JsonUtils.sendObjectJson(response, objectMapper, R<Any>(ResultStatusCodes.NO_PERMISSION))
        log.debug("当前请求 {} 请求方式 {} 被拒绝", request.requestURL, request.method)
    }
}