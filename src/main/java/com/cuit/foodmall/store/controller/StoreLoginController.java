package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/9 09:44
 * @description: 商家登录
 */
@RestController
@RequestMapping("store")
@Slf4j
public class StoreLoginController {

	@Autowired
	private UserService userService;

	@PostMapping("login")
	public Object login(User user, HttpSession session){
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
			return Result.error("用户名和密码不能为空");
		}
		LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
		wrapper.eq(User::getUsername, user.getUsername());
		wrapper.eq(User::getPassword, user.getPassword());
		User u = userService.getOne(wrapper);
		if (u == null){
			log.info("登录失败");
			return Result.error("您输入的用户名和密码不匹配");
		}
		session.setAttribute("business", u);
		log.info("登录成功");
		return Result.ok("登录成功");
	}

	/**
	 * @description: 是否登录
	 * @return: java.lang.Object
	 */
	@GetMapping("isLogin")
	public Object isLogin(HttpSession session){
		User user = (User) session.getAttribute("business");
		if (null != user) {
			return Result.ok(user);
		}else {
			return Result.error("未登录");
		}
	}

	/**
	 * @description: 登出
	 * @return: void
	 */
	@GetMapping("loginout")
	public void loginout(HttpSession session){
		session.removeAttribute("business");
		session.removeAttribute("store");
		log.info("退出登录");
	}
}
