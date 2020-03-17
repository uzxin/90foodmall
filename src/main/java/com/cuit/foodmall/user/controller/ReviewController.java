package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.service.ReviewService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/17 15:19
 * @description: 用户评价
 */
@RestController
@RequestMapping("review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;

	/**
	 * @description: 用户评价订单
	 * @param: review
	 * @return: java.lang.Object
	 */
	@PostMapping("create")
	public Object create(Review review, HttpSession session){
		//修改订单状态为已完成
		UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", review.getOrderId());
		wrapper.set("status","9");
		orderService.update(wrapper);
		//保存评价信息
		User user = (User) session.getAttribute("user");//用户
		Order order = orderService.getById(review.getOrderId());//订单
		review.setProductId(order.getProductId());//产品ID
		review.setStoreId(productService.getById(order.getProductId()).getStoreId());//店铺ID
		review.setCommentatorId(user.getId());
		review.setCommentatorName(user.getUsername());
		reviewService.save(review);
		return Result.ok("评价成功");
	}
}
