package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: YX
 * @date: 2020/3/26 10:20
 * @description:
 */
@RestController
@RequestMapping("admin")
public class AdminLoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Value("${TOKEN_EXPIRE}")
	private Integer TOKEN_EXPIRE;

	/**
	 * @description: 登录
	 * @param: user
	 * @param: response
	 * @return: java.lang.Object
	 */
	@PostMapping("login")
	public Object login(User user, HttpServletResponse response){
		LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
		wrapper.eq(User::getUsername,user.getUsername());
		wrapper.eq(User::getPassword,user.getPassword());
		User database_user = userService.getOne(wrapper);
		if (null == database_user){
			return Result.error("用户名密码错误");
		}
		String token = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set("Token_Login_Admin:"+ token, database_user,TOKEN_EXPIRE, TimeUnit.SECONDS);
		//将token返回客户端
		Cookie cookie = new Cookie("Token_Login_Admin", token);
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);
		return Result.ok("登陆成功");
	}

	/**
	 * @description: 登出
	 * @param: request
	 * @return: java.lang.Object
	 */
	@GetMapping("logout")
	public Object logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("Token_Login_Admin".equals(cookie.getName())){
				/*cookie.setValue("");
				response.addCookie(cookie);*/
				redisTemplate.delete("Token_Login_Admin:"+cookie.getValue());
			}
		}
		request.getSession().removeAttribute("admin");
		return Result.ok();
	}

	/**
	 * @description: 修改密码
	 * @param: oldPassword 旧密码
	 * @param: newPassword 新密码
	 * @return: java.lang.Object
	 */
	@PostMapping("changePassword")
	public Object changePassword(HttpSession session,String oldPassword, String newPassword){
		User user = (User) session.getAttribute("admin");
		if(user != null){
			if (oldPassword.equals(user.getPassword())){
				user.setPassword(newPassword);
				if (userService.updateById(user)){
					return Result.ok("修改成功");
				}
				return Result.error("修改失败");
			}
			return Result.error("旧密码错误");
		}
		return Result.error("未登录");
	}


	/**
	 * @description: 查询用户是否存在
	 * @param: session
	 * @return: java.lang.Object
	 */
	@GetMapping("getUser")
	public Object getUser(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies){
			for (Cookie cookie : cookies) {
				if ("Token_Login_Admin".equals(cookie.getName())){
					User user = (User) redisTemplate.opsForValue().get("Token_Login_Admin:" + cookie.getValue());
					if (null != user){
						return Result.ok(user);
					}
				}
			}
		}
		return Result.error();
	}
}
