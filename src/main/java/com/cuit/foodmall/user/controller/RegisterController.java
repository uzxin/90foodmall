package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/2/19 14:47
 * @description: 用户注册
 */
@RestController
@RequestMapping("user")
@Slf4j
public class RegisterController {

	@Autowired
	private UserService userService;

	@PostMapping("register")
	public Object register(User user){
		LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
		wrapper.eq(User::getUsername, user.getUsername());
		if (null != userService.getOne(wrapper)){
			log.info("注册失败");
			return Result.error("该邮箱已被注册");
		}
		userService.save(user);
		log.info("注册成功");
		return Result.ok("注册成功");
	}
}
