package com.ck.securityjwt.exceptions

import com.ck.securityjwt.model.enums.ResultStatusCodes


/**
 * 自定义根异常
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/9
 */
class AppRootException(val resultStatusCodes: ResultStatusCodes) : Exception()