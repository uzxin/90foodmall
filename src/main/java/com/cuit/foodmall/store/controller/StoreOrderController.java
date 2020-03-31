package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.entity.vo.OrderVO;
import com.cuit.foodmall.entity.vo.UserAddressVO;
import com.cuit.foodmall.service.AddressService;
import com.cuit.foodmall.service.LogisticsService;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.UserAddressService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/11 16:16
 * @description: 商家订单管理
 */
@RestController
@RequestMapping("store/order")
public class StoreOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private AddressService addressService;

	/**
	 * @description: 查询店铺所有订单
	 * @param: order
	 * @param: begin 开始时间
	 * @param: end 结束时间
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看订单")
	@GetMapping("page")
	public Object page(Order order, String begin, String end,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit,
	                   HttpSession session){
		Page<OrderVO> ipage = new Page<>(page, limit);
		Long storeId = ((Store) session.getAttribute("store")).getId();
		QueryWrapper<OrderVO> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(order.getStatus())){
			wrapper.eq("o.status", order.getStatus());
		}
		if (StringUtils.isNotEmpty(begin) && StringUtils.isNotEmpty(end)){
			wrapper.between("o.create_time",begin,end);
		}
		wrapper.eq("o.del_flag", "0");
		wrapper.last("AND o.product_id IN (select id from product WHERE store_id="+storeId+")");
		IPage<OrderVO> orders = orderService.listOrderBySid(ipage, wrapper);
		return new Result(0,"",orders.getTotal(),orders.getRecords());
	}

	/**
	 * @description: 发货
	 * @param: logistics
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "发货")
	@PostMapping("send")
	public Object send(Logistics logistics,HttpSession session){
		//修改订单状态为已发货(待收货)
		UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
		wrapper.set("status", "5");
		wrapper.eq("id",logistics.getOrderId());
		orderService.update(wrapper);
		//录入物流信息
		User business = (User) session.getAttribute("business");
		Store store = (Store) session.getAttribute("store");
		logistics.setStoreId(store.getId());
		logistics.setCreateUserId(business.getId());
		logistics.setCreateUserName(business.getUsername());
		logisticsService.save(logistics);
		return Result.ok("发货成功");
	}

	/**
	 * @description: 查询物流信息
	 * @param: orderId
	 * @return: java.lang.Object
	 */
	@GetMapping("queryLogistics")
	public Object queryLogistics(Long orderId){
		LambdaQueryWrapper<Logistics> wrapper = new QueryWrapper<Logistics>().lambda();
		wrapper.eq(Logistics::getOrderId, orderId);
		return Result.ok(logisticsService.getOne(wrapper));
	}

	/**
	 * @description: 删除订单
	 * @param: id
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "删除订单")
	@GetMapping("del")
	public Object del(Long id){
		if(orderService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	@GetMapping("getAddress")
	public Object getAddress(Long addressId){
		UserAddress po = userAddressService.getById(addressId);
		UserAddressVO vo = new UserAddressVO();
		BeanUtils.copyProperties(po, vo);
		//区县
		Address country = addressService.getById(po.getAddressId());
		vo.setCountry(country.getDistrict());
		//市
		Address city = addressService.getById(country.getPid());
		vo.setCity(city.getDistrict());
		//省
		Address province = addressService.getById(city.getPid());
		vo.setProvince(province.getDistrict());
		return Result.ok(vo);
	}
}
