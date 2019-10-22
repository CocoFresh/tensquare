package com.tensquare.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/22 17:42
 * @Description:
 */
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Zuul过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURL().toString();
        if (url.indexOf("/login") > 0) {
            System.out.println("登录页面" + url);
            return null;
        }
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if (claims.get("roles").equals("admin")) {
                    requestContext.addZuulRequestHeader("Authorization", authorization);
                    System.out.println("token验证通过，添加了头信息" + authorization);
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
