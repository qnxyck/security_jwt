package com.ck.securityjwt.model.enums

/**
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
enum class ResultStatusCodes(var code: Int, var msg: String) {

    // 成功
    SUCCESS(0, "成功"),
    // 失败
    SYSTEM_ERR(1, "系统异常"),
    // 无权限
    NO_PERMISSION(2, "无权限"),
    // 被禁用
    USER_DISABLED(3, "用户被禁用"),
    // 用户名或密码错误
    WRONG_USER_NAME_OR_PASSWORD(4, "用户名或密码错误"),
    // 签名异常
    SIGNATURE_EXCEPTION(5, "签名错误, 请重新登录!"),
    // 签名过期
    SIGNATURE_EXPIRED_EXCEPTION(6, "签名过期, 请重新登录!"),
    // 没有签名
    NO_SIGNATURE_EXCEPTION(7, "未接收到签名信息!"),


    ;


}