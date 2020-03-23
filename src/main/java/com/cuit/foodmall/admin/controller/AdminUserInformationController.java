package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.entity.UserRole;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/23 15:52
 * @description:
 */
@RestController
@RequestMapping("admin/userinformation")
public class AdminUserInformationController {

	@Autowired
	private UserInformationService userInformationService;

	/**
	 * @description: 查询用户个人信息
	 * @param: userInformation
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(UserInformation userInformation,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<UserInformation> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<UserInformation> wrapper = new QueryWrapper<UserInformation>().lambda();
		if (StringUtils.isNotEmpty(userInformation.getUsername())){
			wrapper.eq(UserInformation::getUsername, userInformation.getUsername());
		}
		if (StringUtils.isNotEmpty(userInformation.getRealName())){
			wrapper.eq(UserInformation::getRealName, userInformation.getRealName());
		}
		if (StringUtils.isNotEmpty(userInformation.getGender())){
			wrapper.eq(UserInformation::getGender, userInformation.getGender());
		}
		IPage<UserInformation> p = userInformationService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	@PostMapping("update")
	public Object update(UserInformation userInformation){
		if (userInformationService.updateById(userInformation)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
	/**
	 * @description: 重置个人信息
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("reset")
	public Object reset(Long id){
		UserInformation u = userInformationService.getById(id);
		u.setRealName("");
		u.setBirthday("");
		u.setPhone("");
		u.setEmail("");
		u.setIdCardNumber("");
		u.setIdCardPositive("");
		u.setIdCardReverse("");
		if (userInformationService.updateById(u)){
			return Result.ok("重置成功");
		}
		return Result.error("重置失败");
	}

}
