package com.ck.securityjwt.mapper

import com.ck.securityjwt.model.entity.Role
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *角色
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Mapper
interface RoleMapper {

    /**
     * 查询所有角色
     */
    @Select("select * from role")
    fun selectAll(): List<Role>

    /**
     * 根据角色id查询一条角色
     */
    @Select("select * from role where id = #{id}")
    fun selectOneById(@Param("id") id: Int): Role?

    /**
     * 添加角色
     */
    @Insert("insert into role (role_name) values (#{role.roleName})")
    fun insertOne(@Param("role") role: Role): Boolean

    /**
     * 根据userId查询当前用户所有角色
     */
    @Select("select r.* from user_role ur left join role r on ur.rid = r.id where ur.uid = #{userId}")
    fun selectAllByUserId(@Param("userId") userId: Int): List<Role>

}