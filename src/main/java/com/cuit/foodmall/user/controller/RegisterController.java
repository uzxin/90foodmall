package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.EmailUtil;
import com.cuit.foodmall.util.Result;
import com.cuit.foodmall.util.SMSModel;
import com.cuit.foodmall.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
	@Autowired
	private UserInformationService userInformationService;

	/**
	 * @description: 获取手机验证码
	 * @return: java.lang.Object
	 */
	@GetMapping("getMobleCode")
	public Object getMobleCode(String mobile) throws IOException, DocumentException {
		if (null == mobile || "".equals(mobile)){
			return Result.error("手机号不正确");
		}
		String mobileCode = SMSUtil.send(mobile, SMSModel.register());//短信验证码
		if (null == mobileCode){
			return Result.error("短信发送失败");
		}
		return Result.ok("发送成功",mobileCode);
	}

	/**
	 * @description: 获取邮箱验证码
	 * @param: email 邮箱
	 * @return: java.lang.Object
	 */
	@GetMapping("getEmailCode")
	public Object getEmailCode(String email){
		int email_code = (int)((Math.random()*9+1)*100000);
		int result = EmailUtil.send(email, "绑定邮箱",
				"验证码："+email_code+"，请即时输入。您正在进行绑定手机号，绑定后有效提升您的账号安全。");
		if (result == 1){
			return Result.error("发送失败");
		}
		log.info("邮箱验证码为："+email_code);
		return Result.ok("发送成功", email_code);
	}

	/**
	 * @description: 注册用户
	 * @param: user
	 * @return: java.lang.Object
	 */
	@PostMapping("register")
	public Object register(User user, @RequestParam(required = false) String phone,
	                       @RequestParam(required = false) String email, HttpSession session){
		LambdaQueryWrapper<UserInformation> userInforWrapper = new QueryWrapper<UserInformation>().lambda();
		// 判断手机号是否注册
		if (StringUtils.isNotEmpty(phone)){
			userInforWrapper.eq(UserInformation::getPhone, phone);
			if (null != userInformationService.getOne(userInforWrapper)){
				return Result.error("该手机号已被注册");
			}
		}
		// 判断邮箱是否注册
		if (StringUtils.isNotEmpty(email)){
			userInforWrapper.eq(UserInformation::getEmail, email);
			if (null != userInformationService.getOne(userInforWrapper)){
				return Result.error("该邮箱已被注册");
			}
		}
		// 判断用户名是否唯一
		LambdaQueryWrapper<User> userWrapper = new QueryWrapper<User>().lambda();
		userWrapper.eq(User::getUsername, user.getUsername());
		if (null != userService.getOne(userWrapper)){
			return Result.error("该用户名已被注册");
		}
		//保存用户
		userService.save(user);
		// 将用户信息存入sesssion
		User u = userService.getOne(userWrapper);
		session.setAttribute("user", u);
		// 保存用户个人信息
		userInformationService.save(new UserInformation(u.getId(),phone,email));
		return Result.ok("注册成功");
	}
}
