package com.ck.securityjwt.service

import com.ck.securityjwt.model.entity.Role

/**
 * 角色
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
interface RoleService {

    /**
     * 根据用户id查询当前用户所有角色
     *
     * @param uid 用户id
     * @return 角色信息
     */
    fun roleByUserId(uid: Int): List<Role>
}