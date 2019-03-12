package com.ck.securityjwt.mapper

import com.ck.securityjwt.model.entity.Resource
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *资源
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Mapper
interface ResourceMapper {

    /**
     * 查询所有
     */
    @Select("select * from resource")
    fun selectAll(): List<Resource>

    /**
     * 根据资源id查询一条
     */
    @Select("select * from resource where id = #{id}")
    fun selectOneById(@Param("id") id: Int): Resource?

    /**
     * 根据角色id查询所有资源信息
     */
    @Select("select r.* from resource r left join role_res rr on r.id = rr.sid where rr.rid = #{rid}")
    fun selectAllByRoleId(@Param("rid") id: Int): List<Resource>

    /**
     * 添加一条资源
     */
    @Insert("insert into resource (name, url) values (#{re.name}, #{re.url})")
    fun insertOne(@Param("re") resource: Resource): Boolean
}