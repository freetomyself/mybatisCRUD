package com.itcast.dao;

import com.itcast.domain.QueryVo;
import com.itcast.domain.User;


import java.util.List;

/**
 * @program: day02_eesy_01mybatisCRUD--com.itcast.dao
 * @author: WaHotDog 2019-08-08 16:07
 **/

/**
 * 用户持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    public List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    public void inserUser(User user);

    /**
     * 添加一个用户
     * @param user
     */
    public void updateUserById(User user);

    /**
     * 通过id删除用户
     * @param user
     */
    public void deleteUser(User user);

    /**
     * 查询一个用户
     * @param uid
     * @return
     */
    public User findById(Integer uid);

    /**
     * 通过名字模糊查询
     * @param username
     * @return
     */
    public List<User> findByName(String username);

    /**
     *查询所有用户条数
     * @return
     */
    public int findTotal();

    /**
     * 根据queryVO中的条件查询用户
     * @param vo
     */
    public List<User> findUserByVo(QueryVo vo);
}
