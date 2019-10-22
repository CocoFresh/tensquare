package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/21 11:24
 * @Description:
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    public Friend findByUseridAndAndFriendid(String userid, String friendid);

    @Query(value = "select count(*) from tb_friend as f where f.userid=? and f.friendid=?", nativeQuery = true)
    public int selectCount(String userid, String friendid);

    @Modifying
    @Query(value = "update tb_friend f set f.islike=?  where f.userid=? and f.friendid =?", nativeQuery = true)
    public void updateLike(String islike, String userid, String friendid);
}
