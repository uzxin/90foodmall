package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.SecurityQuestion;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.SecurityQuestionService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/25 12:19
 * @description: 密保问题
 */
@RestController
@RequestMapping("admin/securityQuestion")
public class AdminSecurityQuestionController {

	@Autowired
	private SecurityQuestionService securityQuestionService;

	/**
	 * @description: 查询密保问题
	 * @param: securityQuestion
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(SecurityQuestion securityQuestion, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<SecurityQuestion> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<SecurityQuestion> wrapper = new QueryWrapper<SecurityQuestion>().lambda();
		if (StringUtils.isNotEmpty(securityQuestion.getQuestion())){
			wrapper.like(SecurityQuestion::getQuestion, securityQuestion.getQuestion());
		}
		IPage<SecurityQuestion> p = securityQuestionService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(SecurityQuestion securityQuestion, HttpSession session){
		if (null == securityQuestion.getId()){
			User user = (User) session.getAttribute("admin");
			securityQuestion.setCreateUserId(user.getId());
			securityQuestion.setCreateUserName(user.getUsername());
		}
		if (securityQuestionService.saveOrUpdate(securityQuestion)){
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * @description: 删除
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long id){
		if (securityQuestionService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
