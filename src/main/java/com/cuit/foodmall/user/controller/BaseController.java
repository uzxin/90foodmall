package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: YX
 * @date: 2020/2/20 11:29
 * @description:
 */
public class BaseController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static BaseController baseController;
    @PostConstruct
    public void initialize() {
        baseController= this;
        baseController.redisTemplate = this.redisTemplate;
    }


    /**
	 * 从redis中获取用户信息
	 * @param: request
	 * @return: com.cuit.foodmall.entity.User
	 * @author: uzxin
	 * @date: 2020/6/8 11:22
	 */
	public static User getUser(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (null != cookies){
			for (Cookie cookie : cookies) {
				if ("Token_Login_User".equals(cookie.getName())){
					User user = (User) baseController.redisTemplate.opsForValue().get("Token_Login_User:" + cookie.getValue());
					if (null != user){
						return user;
					}
				}
			}
		}
		return null;
	}
}
