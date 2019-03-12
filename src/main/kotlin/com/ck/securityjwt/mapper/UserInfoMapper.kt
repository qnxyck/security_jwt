package com.ck.securityjwt.mapper

import com.ck.securityjwt.model.entity.UserInfo
import org.apache.ibatis.annotations.*

/**
 *用户
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Mapper
interface UserInfoMapper {

    /**
     * 查询所有正常状态的数据
     */
    @Select("select * from user_info where isDelete = 0")
    fun selectAll(): List<UserInfo>

    /**
     * 根据角色id查询一条
     */
    @Select("select * from user_info where id = #{id} and isDelete = 0")
    fun selectOneById(@Param("id") id: Int): UserInfo?

    /**
     * 根据用户名称查询一条用户信息
     */
    @Select("select * from user_info where userName = #{un} and isDelete = 0")
    fun selectOneByUserName(@Param("un") userName: String): UserInfo?

    /**
     * 添加用户
     * 添加成功默认启用
     */
    @Insert("insert into user_info (userName, password, tel) values (#{ui.userName}, #{ui.password}, #{ui.tel})")
    fun insertOne(@Param("ui") userInfo: UserInfo): Boolean

    /**
     * 如果是已删除的用户则不更新
     *
     */
    @Update("update user_info set password = #{password}, tel = #{tel}, isEnable = #{isEnable}, isDelete = #{isDelete} where userName = #{userName} and isDelete = 0")
    fun updateOne(userInfo: UserInfo): Boolean

}