package com.ck.securityjwt.model.entity

import java.io.Serializable

/**
 * 资源
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
data class Resource(var id: Int?,
                    var name: String,
                    var url: String) : Serializable