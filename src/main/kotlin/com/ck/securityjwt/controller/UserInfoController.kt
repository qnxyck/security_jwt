package com.ck.securityjwt.controller

import com.ck.securityjwt.model.dto.R
import com.ck.securityjwt.model.entity.UserInfo
import com.ck.securityjwt.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 用户信息 controller
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/7
 */

@RestController
@RequestMapping("/userInfo")
class UserInfoController {

    @Autowired
    private lateinit var userInfoService: UserInfoService

    /**
     * 添加用户信息
     */
    @PostMapping("/ui")
    fun addUserInfo(userInfo: UserInfo): R<Boolean> {
        return R(data = userInfoService.saveUserInfo(userInfo))
    }

    /**
     * 获取全部用户信息
     */
    @GetMapping("/ui")
    fun getAll() = R(data = this.userInfoService.userInfoAll())

    /**
     * 更新用户信息
     */
    @PutMapping("/ui")
    fun editUserInfo(userInfo: UserInfo): R<Any> {
        return R(data = this.userInfoService.updateUserInfo(userInfo))
    }


}

