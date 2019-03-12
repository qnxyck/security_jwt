package com.ck.securityjwt.service

import com.ck.securityjwt.model.entity.UserInfo

/**
 * 用户信息
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
interface UserInfoService {

    /**
     * 根据用户名称获取用户信息
     *
     * @param userName 用户名称
     * @return UserInfo
     */
    fun userInfoByName(userName: String): UserInfo?

    /**
     * 查询所有用户信息
     */
    fun userInfoAll(): List<UserInfo>

    /**
     * 添加用户信息
     */
    fun saveUserInfo(userInfo: UserInfo): Boolean

    /**
     * 修改用户信息
     */
    fun updateUserInfo(userInfo: UserInfo): Boolean
}