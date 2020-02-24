package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import com.cuit.foodmall.util.SMSModel;
import com.cuit.foodmall.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: YX
 * @date: 2020/2/21 13:50
 * @description: 安全设置
 */
@RestController
@RequestMapping("security")
@Slf4j
public class SecurityController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private UserInformationService userInformationService;

	/**
	 * @description: 修改登录密码
	 * @param: user
	 * @param: newPassword 新密码
	 * @param: session
	 * @return: java.lang.Object
	 */
	@PostMapping("changePassword")
	public Object changePassword(User user, String newPassword, HttpSession session){
		if (!getUser(session).getPassword().equals(user.getPassword())){
			return Result.error("原密码不正确");
		}
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.set("user_password", newPassword);
		wrapper.eq("id", getUser(session).getId());
		userService.update(wrapper);
		// 更新session中的登录信息
		session.setAttribute("user",userService.getById(getUser(session).getId()));
		return Result.ok("修改成功");
	}

	@PostMapping("changePayPassword")
	public Object changePayPassword(User user, HttpSession session){
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.set("user_pay_password", user.getPayPassword());
		wrapper.eq("id", getUser(session).getId());
		userService.update(wrapper);
		return Result.ok("修改成功");
	}

	@GetMapping("getMobleCode")
	public Object getMobleCode(HttpSession session) throws IOException, DocumentException {
		String mobile = userInformationService.getById(((User)session.getAttribute("user")).getId()).getPhone();
		if (null == mobile || "".equals(mobile)){
			return Result.error("手机号不正确");
		}
		String mobileCode = SMSUtil.send(mobile, SMSModel.common());
		if (null == mobile){
			return Result.error("短信发送失败");
		}
		return Result.ok("发送成功",mobileCode);
	}
}
