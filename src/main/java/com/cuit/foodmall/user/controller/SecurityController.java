package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.entity.UserQuestion;
import com.cuit.foodmall.service.SecurityQuestionService;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserQuestionService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.EmailUtil;
import com.cuit.foodmall.util.Result;
import com.cuit.foodmall.util.SMSModel;
import com.cuit.foodmall.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
	@Autowired
	private SecurityQuestionService securityQuestionService;
	@Autowired
	private UserQuestionService userQuestionService;

	/**
	 * @description: 修改登录密码
	 * @param: user
	 * @param: newPassword 新密码
	 * @param: session
	 * @return: java.lang.Object
	 */
	@PostMapping("changePassword")
	public Object changePassword(User user, String newPassword, HttpServletRequest request, HttpSession session){
		User u = getUser(request);
		if (!u.getPassword().equals(user.getPassword())){
			return Result.error("原密码不正确");
		}
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.set("user_password", newPassword);
		wrapper.eq("id", u.getId());
		userService.update(wrapper);
		// 更新session中的登录信息
		session.setAttribute("user",userService.getById(u.getId()));
		return Result.ok("修改成功");
	}

	/**
	 * @description: 修改支付密码
	 * @param: user
	 * @return: java.lang.Object
	 */
	@PostMapping("changePayPassword")
	public Object changePayPassword(User user, HttpServletRequest request){
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.set("user_pay_password", user.getPayPassword());
		wrapper.eq("id", getUser(request).getId());
		userService.update(wrapper);
		return Result.ok("修改成功");
	}

	/**
	 * @description: 绑定手机号
	 * @param: userInformation
	 * @return: java.lang.Object
	 */
	@PostMapping("bindPhone")
	public Object bindPhone(HttpServletRequest request, UserInformation userInformation){
		UpdateWrapper<UserInformation> wrapper = new UpdateWrapper<>();
		wrapper.set("user_phone", userInformation.getPhone());
		wrapper.eq("id", getUser(request).getId());
		userInformationService.update(wrapper);
		return Result.ok("保存成功");
	}

	/**
	 * @description: 绑定邮箱
	 * @param: userInformation
	 * @return: java.lang.Object
	 */
	@PostMapping("bindEmail")
	public Object bindEmail(HttpServletRequest request, UserInformation userInformation){
		UpdateWrapper<UserInformation> wrapper = new UpdateWrapper<>();
		wrapper.set("user_email", userInformation.getEmail());
		wrapper.eq("id", getUser(request).getId());
		userInformationService.update(wrapper);
		return Result.ok("保存成功");
	}

	/**
	 * @description: 实名认证
	 * @param: userInformation
	 * @return: java.lang.Object
	 */
	@PostMapping("verified")
	public Object verified(UserInformation userInformation){
		if (userInformationService.updateById(userInformation)){
			return Result.ok("保存成功");
		}
		return Result.error("保存失败");
	}

	/**
	 * @description: 设置密保问题
	 * @param: map
	 * @return: java.lang.Object
	 */
	@PostMapping("setSafetyQuestion")
	public Object setSafetyQuestion(@RequestBody Map<Long,String> map, HttpServletRequest request){
		Long userId = getUser(request).getId();
		ArrayList<UserQuestion> userQuestions = new ArrayList<>();
		for (Map.Entry<Long,String> entry: map.entrySet()){
			UserQuestion userQuestion = new UserQuestion(userId, entry.getKey(), entry.getValue());
			userQuestions.add(userQuestion);
		}
		for (UserQuestion u : userQuestions) {
			LambdaQueryWrapper<UserQuestion> wrapper1 = new QueryWrapper<UserQuestion>().lambda();
			wrapper1.eq(UserQuestion::getUserId,userId);
			wrapper1.eq(UserQuestion::getQuestionId,u.getQuestionId());
			if (null == userQuestionService.getOne(wrapper1)){
				userQuestionService.save(u);// 插入
			}else {
				LambdaUpdateWrapper<UserQuestion> wrapper2 = new UpdateWrapper<UserQuestion>().lambda();
				wrapper2.set(UserQuestion::getAnswer,u.getAnswer());
				wrapper2.eq(UserQuestion::getUserId,userId);
				wrapper2.eq(UserQuestion::getQuestionId,u.getQuestionId());
				userQuestionService.update(wrapper2);// 更新
			}
		}
		return Result.ok("保存成功");
	}

	/**
	 * @description: 获取手机验证码
	 * @return: java.lang.Object
	 */
	@GetMapping("getMobleCode")
	public Object getMobleCode(String mobile) throws IOException, DocumentException {
		if (null == mobile || "".equals(mobile)){
			return Result.error("手机号不正确");
		}
		String mobileCode = SMSUtil.send(mobile, SMSModel.common());//短信验证码
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
	 * @description: 获取密保问题列表
	 * @return: java.lang.Object
	 */
	@GetMapping("getQuestion")
	public Object getQuestion(){
		return Result.ok(securityQuestionService.list());
	}

}
