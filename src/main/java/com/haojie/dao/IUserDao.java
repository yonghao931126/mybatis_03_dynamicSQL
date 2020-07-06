package com.haojie.dao;

import com.haojie.domain.QueryVo;
import com.haojie.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 模糊查询
     * @param username
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);


    /**
     * 根据条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据条件ids集合查询
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
