package com.ck.securityjwt.security.filters

import com.ck.securityjwt.common.utils.JsonUtils
import com.ck.securityjwt.common.utils.TokenFormat
import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.model.enums.ResultStatusCodes
import com.ck.securityjwt.security.config.SecurityConfig
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 请求之前过滤器
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@Component
class SecurityBeforeFilter : OncePerRequestFilter() {

    // 配置信息
    @Autowired
    private lateinit var securityConfig: SecurityConfig

    // Token 工具
    @Autowired
    private lateinit var tokenFormat: TokenFormat

    // Json转换
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        private val log = LoggerFactory.getLogger(SecurityBeforeFilter::class.java)
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val tokenVal = request.getHeader(securityConfig.tokenHeader)
        // (判断域名是否合法)
        val requestURL = request.requestURL

        if (tokenVal == null) {
            // 未接收到签名信息
            JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.NO_SIGNATURE_EXCEPTION)))
            log.debug("当前请求 {} 未接收到Token", requestURL)
        } else {
            try {
                // 更新token时间(更新时间会解析token, 可能发生异常)
                val updateTokenTime = tokenFormat.updateTokenTime(tokenVal)
                response.setHeader(securityConfig.tokenHeader, updateTokenTime)
                // 放行
                log.debug("验证通过 {}", requestURL)
                filterChain.doFilter(request, response)
            } catch (e: ExpiredJwtException) {
                // 已过期异常
                JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.SIGNATURE_EXPIRED_EXCEPTION)))
                log.debug("当前请求Token已过期 {}", requestURL)
            } catch (e: Exception) {
                //  签名异常 格式异常  重新登录
                JsonUtils.sendJson(response, objectMapper.writeValueAsString(R<Any>(ResultStatusCodes.SIGNATURE_EXCEPTION)))
                log.info("签名异常/签名信息格式异常 Token: {}, 请求地址: {}", tokenVal, requestURL)
            }
        }
    }

}