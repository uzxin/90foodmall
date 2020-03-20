package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.service.ReviewService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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
		Product product = productService.getById(order.getProductId());//产品
		review.setStoreId(product.getStoreId());//店铺ID
		review.setProductName(product.getName());//产品名字
		review.setCommentatorId(user.getId());//评论人ID
		review.setCommentatorName(user.getUsername());//评论人账号
		reviewService.save(review);
		return Result.ok("评价成功");
	}

	/**
	 * @description: 查询用户评论
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(HttpSession session){
		Long userId = ((User) session.getAttribute("user")).getId();//用户ID
		return Result.ok(reviewService.listByUId(userId));
	}

	@GetMapping("listReviewByPid")
	public Object listReviewByPid(Long pid){
		LambdaQueryWrapper<Review> wrapper = new QueryWrapper<Review>().lambda();
		wrapper.eq(Review::getProductId, pid);
		List<Review> list = reviewService.list(wrapper);
		return new Result(0,"",list.size(),list);
	}
}
