package com.ck.securityjwt.service.impl

import com.ck.securityjwt.mapper.UserInfoMapper
import com.ck.securityjwt.model.entity.UserInfo
import com.ck.securityjwt.service.UserInfoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * 用户信息 impl
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Service
class UserInfoServiceImpl : UserInfoService {

    @Autowired
    private lateinit var userInfoMapper: UserInfoMapper

    companion object {
        private val log = LoggerFactory.getLogger(UserInfoServiceImpl::class.java)
    }

    override fun userInfoByName(userName: String) = this.userInfoMapper.selectOneByUserName(userName)

    override fun userInfoAll() = this.userInfoMapper.selectAll()

    override fun saveUserInfo(userInfo: UserInfo): Boolean {
        /**
         * 保存用户时对密码加密
         */
        userInfo.password = BCryptPasswordEncoder().encode(userInfo.password)
        return this.userInfoMapper.insertOne(userInfo)
    }

    override fun updateUserInfo(userInfo: UserInfo): Boolean {
        /**
         * 修改时对密码进行加密
         */
        userInfo.password = BCryptPasswordEncoder().encode(userInfo.password)
        return this.userInfoMapper.updateOne(userInfo)
    }

}