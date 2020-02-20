package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/2/20 10:42
 * @description: 用户信息
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController{

	@Autowired
	private UserInformationService userInformationService;

	/**
	 * @description: 获取个人信息
	 * @param: userId
	 * @return: java.lang.Object
	 */
	@GetMapping("getUser")
	public Object getUserInfo(HttpSession session){
		Long userId = getUser(session).getId();
		LambdaQueryWrapper<UserInformation> wrapper = new QueryWrapper<UserInformation>().lambda();
		wrapper.eq(UserInformation::getUserId, userId);
		UserInformation u = userInformationService.getOne(wrapper);
		return Result.ok(u);
	}

	/**
	 * @description: 修改个人信息
	 * @param: userInformation
	 * @return: java.lang.Object
	 */
	@PostMapping("UpdataUserInfo")
	public Object UpdataUserInfo(UserInformation userInformation){
		userInformationService.updateById(userInformation);
		return Result.ok("修改成功");
	}
}
