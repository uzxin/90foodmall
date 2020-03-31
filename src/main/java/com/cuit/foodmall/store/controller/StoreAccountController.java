package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/20 14:14
 * @description: 账号信息
 */
@RestController
@RequestMapping("store/account")
public class StoreAccountController {

	@Autowired
	private UserInformationService userInformationService;
	@Autowired
	private UserService userService;

	/**
	 * @description: 查询个人信息
	 * @param: session
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看个人信息")
	@GetMapping("myInformation")
	public Object myInformation(HttpSession session){
		User user = (User) session.getAttribute("business");
		LambdaQueryWrapper<UserInformation> wrapper = new QueryWrapper<UserInformation>().lambda();
		wrapper.eq(UserInformation::getUserId, user.getId());
		return Result.ok(userInformationService.getOne(wrapper));
	}

	/**
	 * @description: 修改个人信息
	 * @param: userInformation
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "修改个人信息")
	@PostMapping("update")
	public Object update(UserInformation userInformation){
		if (userInformationService.updateById(userInformation)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}

	/**
	 * @description: 修改密码
	 * @param: user
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "修改密码")
	@PostMapping("updatePassword")
	public Object updatePassword(String oldPassword, String newPassword, HttpSession session){
		User user = (User) session.getAttribute("business");
		if (!user.getPassword().equals(oldPassword)){
			return Result.error("原密码错误");
		}
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("user_password", newPassword);
		if (userService.update(wrapper)){
			//更新session
			user.setPassword(newPassword);
			session.setAttribute("business", user);
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
}
