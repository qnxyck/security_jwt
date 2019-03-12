package com.ck.securityjwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * 启动入口
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@SpringBootApplication
class SecurityJwtApplication

fun main(args: Array<String>) {
    runApplication<SecurityJwtApplication>(*args)
}
