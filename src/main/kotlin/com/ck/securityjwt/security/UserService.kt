package com.ck.securityjwt.security

import com.ck.securityjwt.service.RoleService
import com.ck.securityjwt.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * security 框架需要使用到一个实现了UserDetailsService接口的类
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var userInfoService: UserInfoService

    @Autowired
    private lateinit var roleService: RoleService

    override fun loadUserByUsername(userName: String): UserDetails {
        if (userName == "") {
            throw UsernameNotFoundException("用户未找到!!!")
        }
        val userInfo = this.userInfoService.userInfoByName(userName) ?: throw UsernameNotFoundException("用户未找到!!!")
        return UserDetailsImpl(userInfo, this.roleService.roleByUserId(userInfo.id!!))
    }

}