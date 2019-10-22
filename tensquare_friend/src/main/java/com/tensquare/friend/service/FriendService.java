package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/21 11:35
 * @Description:
 */
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid, String friendid) {


        Friend friend = friendDao.findByUseridAndAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }

//        if (friendDao.selectCount(userid, friendid) > 0) {
//            return 0;
//        }
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断对方是否喜欢你，如果喜欢，将islike设置为1
        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike("1", userid, friendid);
            friendDao.updateLike("1", friendid, userid);
        }
        userClient.incFollowcount(userid, 1);
        userClient.incFanscount(friendid, 1);
        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndAndFriendid(userid, friendid);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }


}
