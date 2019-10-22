package com.tensequare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/17 18:03
 * @Description:
 */
public class ParseJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTg0NzMwODA4MjE3Mzc0NzIwIiwic3ViIjoi5bCP6amsIiwiaWF0IjoxNTcxMzY1NjQwLCJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTcxMzY5MjQwfQ.svwWWQrxhgWWuPtfxgru4ulJ0LfzuQZnk6ypvcIOLjY")
                .getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("用户角色:" + claims.get("roles"));
        System.out.println("登录时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
    }
}
