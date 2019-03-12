package com.ck.securityjwt.exceptions

import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.model.enums.ResultStatusCodes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

/**
 * 全局异常处理
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
@RestControllerAdvice
class GlobalException {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalException::class.java)
    }

    /**
     * 全局异常处理
     *
     * @param req request
     * @param e ex
     * @return any
     * @author QianNianXiaoYao
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.OK)
    fun errHandle(req: HttpServletRequest, e: Exception): Any {
        // 请求地址
        log.error(req.requestURL.toString())

        /**
         * 拦截到指定异常
         * 记录错误日志
         * 抛出异常指定错误信息
         */
        if (e is AppRootException) {
            log.error("错误代码: ${e.resultStatusCodes.code} 错误信息: ${e.resultStatusCodes.msg}")
            return R<Any>(e.resultStatusCodes)
        }

        log.error(ResultStatusCodes.SYSTEM_ERR.msg, e)
        // 其他未知异常
        return R<Any>(ResultStatusCodes.SYSTEM_ERR)
    }
}