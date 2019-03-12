package com.ck.securityjwt

import com.ck.securityjwt.common.utils.TokenFormat
import com.ck.securityjwt.mapper.UserInfoMapper
import com.ck.securityjwt.service.UserInfoService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SecurityJwtApplicationTests {

    @Autowired
    private lateinit var userInfoService: UserInfoService
    @Autowired
    private lateinit var userInfoMapper: UserInfoMapper
    @Autowired
    private lateinit var tokenFormat: TokenFormat

    @Test
    fun contextLoads() {
//        println(this.userInfoMapper.selectOneByUserName("admin"))
//        println(this.userInfoMapper.selectOneByUserName("admin1"))
//
//        println("---")
//        println(this.userInfoService.userInfoByName("admin"))
//        println(this.userInfoService.userInfoByName("admin1"))

        val jwtToken = tokenFormat.createJwtToken("001", "Ck", "ChenKun")
        println("jwtToken = ${jwtToken}")

    }

}
