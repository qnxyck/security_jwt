package com.ck.securityjwt.model.entity

import java.io.Serializable

/**
 * 用户信息
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
data class UserInfo(var id: Int?,
                    var userName: String,
                    var password: String,
                    var tel: String,
                    var isEnable: Short? = 0,
                    var isDelete: Short? = 0) : Serializable