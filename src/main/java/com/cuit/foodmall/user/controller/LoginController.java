package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/2/17 15:09
 * @description: 用户登录
 */
@RestController
@RequestMapping("user")
@Slf4j
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * @description: 登录
	 * @return: java.lang.Object
	 */
	@PostMapping("login")
	public Object login(User user, HttpSession session){
		LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
		wrapper.eq(User::getUsername, user.getUsername());
		wrapper.eq(User::getPassword, user.getPassword());
		User u = userService.getOne(wrapper);
		if (null != u) {
			log.info("登陆成功");
			u.setPassword("");
			session.setAttribute("user", u);
			return Result.ok("登陆成功");
		}else {
			log.info("登陆失败");
			return Result.error("你输入的密码和账户名不匹配，请重新输入");
		}
	}

	/**
	 * @description: 是否登录
	 * @return: java.lang.Object
	 */
	@GetMapping("isLogin")
	public Object isLogin(HttpSession session){
		User user = (User) session.getAttribute("user");
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
		session.removeAttribute("user");
	}
}
