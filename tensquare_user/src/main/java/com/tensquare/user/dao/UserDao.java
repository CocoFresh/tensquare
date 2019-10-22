package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByMobile(String mobile);

    @Modifying
    @Query(value = "update tb_user u set u.fanscount=u.fanscount+? where u.id=?", nativeQuery = true)
    void incFanscount(int x, String userid);

    @Modifying
    @Query(value = "update tb_user u set u.followcount=u.followcount+? where u.id =?", nativeQuery = true)
    void incFollowcount(int x, String userid);
}
