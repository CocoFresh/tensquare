package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/21 13:41
 * @Description:
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        //验证是否登录，并且拿到当前用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }
        String userid = claims.getId();

        //判断
        if (type != null) {
            if (type.equals("1")) {
                int flag = friendService.addFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友");
                } else {
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            } else {
                int flag = friendService.addNoFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加");
                } else {
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            }
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }
    }
}
