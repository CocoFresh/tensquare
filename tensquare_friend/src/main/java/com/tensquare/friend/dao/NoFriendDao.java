package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/22 11:42
 * @Description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

    NoFriend findByUseridAndAndFriendid(String userid, String friendid);
}
