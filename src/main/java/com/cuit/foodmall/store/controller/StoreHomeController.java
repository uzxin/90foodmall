package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.entity.dto.StoreHomeDTO;
import com.cuit.foodmall.service.*;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/27 08:54
 * @description: 商家首页
 */
@RestController
@RequestMapping("store/home")
public class StoreHomeController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private ReviewService reviewService;

	/**
	 * @description: 统计数据
	 * @param: session
	 * @return: java.lang.Object
	 */
	@GetMapping("statistics")
	public Object statistics(HttpSession session){
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		//商品
		List<Product> products = productService.list(new QueryWrapper<Product>().lambda().eq(Product::getStoreId, storeId));
		//订单
		List<Order> orders = orderService.list(new QueryWrapper<Order>().last("AND product_id IN(SELECT id FROM product WHERE store_id ="+storeId+")"));
		//退款
		List<Refund> refunds = refundService.list(new QueryWrapper<Refund>().lambda().eq(Refund::getStoreId, storeId));
		//评论
		List<Review> reviews = reviewService.list(new QueryWrapper<Review>().lambda().eq(Review::getStoreId, storeId));
		//物流
		List<Logistics> logisticses = logisticsService.list(new QueryWrapper<Logistics>().lambda().eq(Logistics::getStoreId, storeId));
		StoreHomeDTO sh = new StoreHomeDTO();
		sh.setProduct_number(products.size());//商品总数
		sh.setOrder_number(orders.size());//订单总数
		sh.setReview_number(reviews.size());//评论数
		sh.setRefund_number(refunds.size());//退款总数
		sh.setLogistics_number(logisticses.size());//物流数
		BigDecimal Turnover = new BigDecimal(BigInteger.ZERO);
		int product_pending_review = 0;//待审核商品
		int product_psss = 0;//通过
		int product_nopsss = 0;//未通过
		int order_completed = 0;//已完成订单数
		int order_toBeDelivered = 0;//待发货订单数
		int order_toBeReceived = 0;//待收货订单数
		int refund_pending = 0;//待退款
		int refund_refunded = 0;//已退款
		int review_replied = 0;//已回复评论
		int review_pendingReply = 0;//未回复评论
		for (Product product : products) {
			if (product.getStatus() == 10){
				product_pending_review += 1;
			}else if (product.getStatus() == 11){
				product_psss += 1;
			}else if (product.getStatus() == 12){
				product_nopsss += 1;
			}
		}
		for (Order order : orders) {
			if (order.getStatus().equals("6") || order.getStatus().equals("9")){
				order_completed += 1;
				Turnover = Turnover.add(order.getPayAmount());
			}else if (order.getStatus().equals("4")){
				order_toBeDelivered += 1;
			}else if (order.getStatus().equals("5")){
				order_toBeReceived += 1;
			}
		}
		for (Refund refund : refunds) {
			if (refund.getStatus() == 7){
				refund_pending += 1;
			}else if (refund.getStatus() == 8){
				refund_refunded += 1;
			}
		}
		for (Review review : reviews) {
			if (review.getStatus().equals("0")){
				review_pendingReply += 1;
			}else if (review.getStatus().equals("1")){
				review_replied += 1;
			}
		}
		sh.setProduct_pending_review(product_pending_review);
		sh.setProduct_psss(product_psss);
		sh.setProduct_nopass(product_nopsss);
		sh.setOrder_completed(order_completed);
		sh.setOrder_toBeDelivered(order_toBeDelivered);
		sh.setOrder_toBeReceived(order_toBeReceived);
		sh.setRefund_pending(refund_pending);
		sh.setRefund_refunded(refund_refunded);
		sh.setReview_pendingReply(review_pendingReply);
		sh.setReview_replied(review_replied);
		sh.setTurnover(Turnover);
		return Result.ok(sh);
	}

	/**
	 * @description: 获取销量前10的商品
	 * @return: java.lang.Object
	 */
	@GetMapping("getProductBySale")
	public Object getProductBySale(HttpSession session){
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		LambdaQueryWrapper<Product> wrapper = new QueryWrapper<Product>().lambda();
		wrapper.eq(Product::getStoreId, storeId);
		wrapper.orderByDesc(Product::getSales);
		wrapper.last("limit 10");
		return Result.ok(productService.list(wrapper));
	}

}
