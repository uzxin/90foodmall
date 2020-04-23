package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.entity.Opinion;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.OpinionService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/4/23 14:16
 * @description: 意见反馈
 */
@RestController
@RequestMapping("opinion")
public class OpinionController {

	@Autowired
	private OpinionService opinionService;

	/**
	 * @description: 提意见
	 * @param: opinion
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(Opinion opinion, HttpSession session){
		User user = (User) session.getAttribute("user");
		opinion.setOperator(user.getUsername());
		if(opinionService.save(opinion)){
			return Result.ok("提交成功");
		}
		return Result.error("提交失败");
	}
}
