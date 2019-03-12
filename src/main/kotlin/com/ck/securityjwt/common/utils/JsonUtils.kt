package com.ck.securityjwt.common.utils

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import javax.servlet.http.HttpServletResponse

/**
 * Json 工具类
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
object JsonUtils {

    /**
     * 使用response发送json数据
     *
     * @param httpServletResponse res
     * @param json                json Str
     * @throws IOException io
     */
    fun sendJson(httpServletResponse: HttpServletResponse, json: String) {
        httpServletResponse.contentType = "application/json;charset=utf-8"
        val out = httpServletResponse.writer
        out.write(json)
        out.flush()
        out.close()
    }

    fun sendObjectJson(httpServletResponse: HttpServletResponse, objectMapper: ObjectMapper, any: Any) {
        sendJson(httpServletResponse, objectMapper.writeValueAsString(any))
    }
}