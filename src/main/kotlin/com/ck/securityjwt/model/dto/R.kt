package com.ck.securityjwt.model.dto

import com.ck.securityjwt.model.enums.ResultStatusCodes
import java.io.Serializable

/**
 * 响应信息主体
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
data class R<T>(var msg: String = "success", private var resultStatusCodes: ResultStatusCodes = ResultStatusCodes.SUCCESS, var data: T? = null) : Serializable {

    var code: Int = resultStatusCodes.code

    constructor(msg: String, resultStatusCodes: ResultStatusCodes) : this() {
        this.msg = msg
        this.code = resultStatusCodes.code
    }

    constructor(e: Throwable) : this() {
        this.msg = e.message ?: ResultStatusCodes.SYSTEM_ERR.msg
        this.code = ResultStatusCodes.SYSTEM_ERR.code
    }

    constructor(data: T?) : this() {
        this.data = data
    }

    constructor(resultStatusCodes: ResultStatusCodes) : this() {
        this.code = resultStatusCodes.code
        this.msg = resultStatusCodes.msg
    }

    override fun toString(): String {
        return "R(msg='$msg', code=$code, data=$data)"
    }
}
