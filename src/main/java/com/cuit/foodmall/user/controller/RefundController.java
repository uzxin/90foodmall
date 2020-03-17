package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.RefundService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/16 17:33
 * @description: 退款管理
 */
@RestController
@RequestMapping("refund")
public class RefundController {

	@Autowired
	private RefundService refundService;
	/**
	 * @description: 查询退款申请
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(HttpSession session){
		Long userId = ((User) session.getAttribute("user")).getId();//用户ID
		return Result.ok(refundService.listByUId(userId));
	}
}
