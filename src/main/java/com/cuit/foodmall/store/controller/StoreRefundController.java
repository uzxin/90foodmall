package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.RefundService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/12 17:09
 * @description: 商家退款管理
 */
@RestController
@RequestMapping("store/refund")
public class StoreRefundController {

	@Autowired
	private RefundService refundService;
	@Autowired
	private OrderService orderService;

	/**
	 * @description: 查询店铺退款订单
	 * @param: refund
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看退款")
	@GetMapping("page")
	public Object page(Refund refund, HttpSession session,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Refund> ipage = new Page<>(page, limit);
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		LambdaQueryWrapper<Refund> wrapper = new QueryWrapper<Refund>().lambda();
		if (refund.getStatus()!=null){
			wrapper.eq(Refund::getStatus, refund.getStatus());
		}
		wrapper.eq(Refund::getStoreId, storeId);
		IPage<Refund> p = refundService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 退款操作
	 * @param: refund
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "退款")
	@PostMapping("refund")
	public Object refund(Refund refund, HttpSession session){
		//修改退款记录表状态
		UpdateWrapper<Refund> wrapper1 = new UpdateWrapper<>();
		wrapper1.eq("id", refund.getId());
		wrapper1.set("status","8");
		wrapper1.set("operator", ((User) session.getAttribute("business")).getUsername());
		refundService.update(wrapper1);
		//修改订单状态为已退款
		UpdateWrapper<Order> wrapper2 = new UpdateWrapper<>();
		wrapper2.eq("id", refund.getOrderId());
		wrapper2.set("status","8");
		orderService.update(wrapper2);
		return Result.ok("退款成功!");
	}

}
