package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/22 13:21
 * @Description:
 */
@FeignClient("tensquare-user")
public interface UserClient {
    @RequestMapping(value = "/user/incfans/{userid}/{x}", method = RequestMethod.POST)
    public void incFanscount(@PathVariable("userid") String userid,
                             @PathVariable("x") int x);

    @RequestMapping(value = "/user/incfollows/{userid}/{x}", method = RequestMethod.POST)
    public void incFollowcount(@PathVariable("userid") String userid,
                               @PathVariable("x") int x);
}
