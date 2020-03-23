package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.entity.UserRole;
import com.cuit.foodmall.entity.vo.UserVO;
import com.cuit.foodmall.service.UserInformationService;
import com.cuit.foodmall.service.UserRoleService;
import com.cuit.foodmall.service.UserService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/23 09:27
 * @description: 用户管理
 */
@RestController
@RequestMapping("admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserInformationService userInformationService;
	@Autowired
	private UserRoleService userRoleService;
	@Value("${user.loginPassword}")
	private String loginPassword;
	@Value("${user.payPassword}")
	private String payPassword;

	@GetMapping("page")
	private Object page(UserVO user,@RequestParam(required = false,defaultValue = "1") int page,
	                    @RequestParam(required = false,defaultValue = "10") int limit){
		Page<UserVO> ipage = new Page<>(page, limit);
		QueryWrapper<UserVO> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(user.getNickName())){
			wrapper.eq("ui.user_nickname", user.getNickName());
		}
		if (StringUtils.isNotEmpty(user.getUsername())){
			wrapper.eq("u.user_username", user.getUsername());
		}
		if (StringUtils.isNotEmpty(user.getGender())){
			wrapper.eq("ui.user_gender", user.getGender());
		}
		wrapper.eq("u.del_flag", "0");
		IPage<UserVO> p = userService.listUser(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 修改帐号状态
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("changeStatus")
	public Object changeStatus(Long id){
		User user = userService.getById(id);
		if ("1".equals(user.getAccountStatus())) {
			user.setAccountStatus("2");
		} else {
			user.setAccountStatus("1");
		}
		if (userService.updateById(user)) {
			return Result.ok("修改成功");
		}
		return Result.ok("修改失败");
	}

	/**
	 * @description: 重置登陆密码
	 * @param: user
	 * @return: java.lang.Object
	 */
	@PostMapping("resetLoginPassword")
	public Object resetLoginPassword(User user){
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("user_password", loginPassword);
		if (userService.update(wrapper)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}

	/**
	 * @description: 重置支付密码
	 * @param: user
	 * @return: java.lang.Object
	 */
	@PostMapping("resetPayPassword")
	public Object resetPayPassword(User user){
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", user.getId());
		wrapper.set("user_pay_password", payPassword);
		if (userService.update(wrapper)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}

	/**
	 * @description: 删除用户
	 * @param: id 用户ID
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object del(Long id){
		//删除个人信息表
		LambdaQueryWrapper<UserInformation> wrapper1 = new QueryWrapper<UserInformation>().lambda();
		wrapper1.eq(UserInformation::getUserId, id);
		//删除用户角色表
		LambdaQueryWrapper<UserRole> wrapper2 = new QueryWrapper<UserRole>().lambda();
		wrapper2.eq(UserRole::getUserId, id);
		if (userService.removeById(id) && userInformationService.remove(wrapper1) && userRoleService.remove(wrapper2)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 添加用户
	 * @param: userVO
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(UserVO userVO){
		//查询账号是否唯一
		LambdaQueryWrapper<User> wrapper1 = new QueryWrapper<User>().lambda();
		wrapper1.eq(User::getUsername, userVO.getUsername());
		if (null != userService.getOne(wrapper1)){
			return Result.error("该账号已存在");
		}
		//保存用户账号信息
		User user = new User();
		user.setUsername(userVO.getUsername());
		user.setPassword(loginPassword);
		user.setPayPassword(payPassword);
		userService.save(user);
		//拿到刚才插入的user
		LambdaQueryWrapper<User> wrapper2 = new QueryWrapper<User>().lambda();
		wrapper2.eq(User::getUsername, userVO.getUsername());
		User u = userService.getOne(wrapper2);
		//保存用户个人信息
		UserInformation userInformation = new UserInformation();
		userInformation.setUserId(u.getId());
		userInformation.setUsername(u.getUsername());
		userInformation.setNickName(userVO.getNickName());
		userInformation.setGender(userVO.getGender());
		userInformationService.save(userInformation);
		//保存用户角色信息
		UserRole userRole = new UserRole();
		userRole.setUserId(u.getId());
		userRole.setRoleId(userVO.getRoleId());
		userRole.setRoleName(userVO.getRoleName());
		userRoleService.save(userRole);
		return Result.ok("添加成功");
	}

	/**
	 * @description: 修改用户
	 * @param: userVO
	 * @return: java.lang.Object
	 */
	@PostMapping("update")
	public Object update(UserVO userVO){
		//查询账号是否唯一
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("user_username", userVO.getUsername());
		wrapper.ne("id",userVO.getId());
		if (null != userService.getOne(wrapper)){
			return Result.error("该账号已存在");
		}
		//修改user表
		UpdateWrapper<User> wrapper1 = new UpdateWrapper<>();
		wrapper1.eq("id", userVO.getId());
		wrapper1.set("user_username", userVO.getUsername());
		if (userService.update(wrapper1)){
			//修改user_information表
			LambdaUpdateWrapper<UserInformation> wrapper2 = new UpdateWrapper<UserInformation>().lambda();
			wrapper2.eq(UserInformation::getUserId, userVO.getId());
			wrapper2.set(UserInformation::getUsername,userVO.getUsername());
			wrapper2.set(UserInformation::getNickName,userVO.getNickName());
			wrapper2.set(UserInformation::getGender, userVO.getGender());
			if (userInformationService.update(wrapper2)){
				//修改user_role表
				LambdaUpdateWrapper<UserRole> wrapper3 = new UpdateWrapper<UserRole>().lambda();
				wrapper3.eq(UserRole::getUserId, userVO.getId());
				wrapper3.set(UserRole::getRoleId, userVO.getRoleId());
				wrapper3.set(UserRole::getRoleName, userVO.getRoleName());
				if (userRoleService.update(wrapper3)){
					return Result.ok("修改成功");
				}
			}
		}
		return Result.error("修改失败");
	}
}
