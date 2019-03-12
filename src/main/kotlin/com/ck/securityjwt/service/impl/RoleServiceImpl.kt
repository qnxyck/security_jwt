package com.ck.securityjwt.service.impl

import com.ck.securityjwt.mapper.RoleMapper
import com.ck.securityjwt.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 角色 impl
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Service
class RoleServiceImpl : RoleService {

    @Autowired
    private lateinit var roleMapper: RoleMapper

    override fun roleByUserId(uid: Int) = this.roleMapper.selectAllByUserId(uid)
}