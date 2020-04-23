package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
	@Autowired
	private UserInformationService userInformationService;

	/**
	 * @description: 登录
	 * @return: java.lang.Object
	 */
	@PostMapping("login")
	public Object login(String loginName, String password, HttpSession session){
		// 匹配用户名和密码
		LambdaQueryWrapper<User> wrapper1 = new QueryWrapper<User>().lambda();
		wrapper1.eq(User::getUsername, loginName);
		wrapper1.eq(User::getPassword, password);
		User u1 = userService.getOne(wrapper1);
		if (null != u1) {
			log.info("登陆成功");
			session.setAttribute("user", u1);
			return Result.ok("登陆成功");
		}
		//匹配手机号和密码
		LambdaQueryWrapper<UserInformation> wrapper2 = new QueryWrapper<UserInformation>().lambda();
		wrapper2.eq(UserInformation::getPhone, loginName);
		UserInformation us1 = userInformationService.getOne(wrapper2);
		if (null != us1){
			User u2 = userService.getById(us1.getUserId());
			if (u2.getPassword().equals(password)){
				log.info("登陆成功");
				session.setAttribute("user", u2);
				return Result.ok("登陆成功");
			}
		}
		//匹配邮箱和密码
		LambdaQueryWrapper<UserInformation> wrapper3 = new QueryWrapper<UserInformation>().lambda();
		wrapper3.eq(UserInformation::getEmail, loginName);
		UserInformation us2 = userInformationService.getOne(wrapper3);
		if (null != us2){
			User u3 = userService.getById(us2.getUserId());
			if (u3.getPassword().equals(password)){
				log.info("登陆成功");
				session.setAttribute("user", u3);
				return Result.ok("登陆成功");
			}
		}
		log.info("登陆失败");
		return Result.error("你输入的密码和账户名不匹配，请重新输入");
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
	public void loginout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute("user");
		//request.getRequestDispatcher("http://localhost:8081/user/home/login.html").forward(request,response);
		response.sendRedirect("/user/home/login.html");
	}


	/**
	 * @description: 修改密码
	 * @param: acount 手机号或者邮箱
	 * @param: password 新密码
	 * @return: java.lang.Object
	 */
	@PostMapping("modifyPassword")
	public Object modifyPassword(String acount, String password){
		if (StringUtils.isEmpty(acount) || StringUtils.isEmpty(password)){
			return Result.error("数据不完整");
		}
		LambdaQueryWrapper<UserInformation> wrapper = new QueryWrapper<UserInformation>().lambda();
		wrapper.eq(UserInformation::getPhone,acount).or().eq(UserInformation::getEmail,acount);
		UserInformation one = userInformationService.getOne(wrapper);
		if (null == one){
			return Result.error("未找到您的账号信息");
		}
		UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
		userUpdateWrapper.eq("id",one.getUserId());
		userUpdateWrapper.set("user_password", password);
		userService.update(userUpdateWrapper);
		return Result.ok("修改成功");
	}
}
