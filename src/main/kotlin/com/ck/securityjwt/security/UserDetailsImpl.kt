package com.ck.securityjwt.security

import com.ck.securityjwt.model.entity.Role
import com.ck.securityjwt.model.entity.UserInfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


/**
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
class UserDetailsImpl(var userInfo: UserInfo, var roles: List<Role>) : UserDetails {

    /**
     * 返回用户所有角色的封装，一个Role对应一个GrantedAuthority
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        roles.forEach { authorities.add(SimpleGrantedAuthority(it.roleName)) }
        return authorities
    }

    /**
     * 获取用户名
     */
    override fun getUsername() = userInfo.userName

    /**
     * 获取用户密码
     */
    override fun getPassword() = userInfo.password

    /**
     * 信用凭证是否过期，默认没有过期
     */
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    /**
     * 账号是否已经过期，默认没有过期
     */
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    /**
     * 账号是否被锁定，默认没有锁定
     */
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    /**
     * 账号是否可用
     */
    override fun isEnabled(): Boolean {
        // 0正常, 1冻结
        return userInfo.isEnable == 0.toShort()
    }

}