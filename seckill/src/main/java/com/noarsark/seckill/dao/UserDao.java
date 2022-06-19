package com.noarsark.seckill.dao;

import com.noarsark.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 用户数据库访问DAO
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/3 7:52 PM
 * @project: seckill
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户id/手机号查询用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM miaosha_user WHERE id = #{id}")
    public User getUserById(@Param("id") Long id);


    @Insert("INSERT INTO miaosha_user VALUES (#{id}, #{nickname}, #{password}, #{salt}, #{head}, #{registerDate}, #{lastLoginDate}, #{loginCount})")
    public void insert(User user);
}
