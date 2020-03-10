package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.PayMethodService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.service.ShipMethodService;
import com.cuit.foodmall.util.RandomUtil;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: YX
 * @date: 2020/3/6 09:32
 * @description: 订单
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	@Autowired
	private ShipMethodService shipMethodService;
	@Autowired
	private PayMethodService payMethodService;
	@Autowired
	private ProductService productService;

	/**
	 * @description: 生成订单
	 * @param: map
	 * @return: java.lang.Object
	 */
	@PostMapping("create")
	public Object create(@RequestBody Map<String,String> map, HttpSession session){
		String orderNumber = RandomUtil.get32();//订单号
		Long addressId = null;
		Long shipMethodId = null;
		Long payMethodId = null;
		BigDecimal payAmount = null;
		Pattern pattern = Pattern.compile("[0-9]*");
		Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
		while (iterator1.hasNext()) {
			Map.Entry<String, String> next = iterator1.next();
			if ("addressId".equals(next.getKey())) {
				addressId = Long.parseLong(next.getValue());
			}
			if ("shipMethodId".equals(next.getKey())) {
				shipMethodId = Long.parseLong(next.getValue());
			}
			if ("payMethodId".equals(next.getKey())) {
				payMethodId = Long.parseLong(next.getValue());
			}
			if ("payAmount".equals(next.getKey())) {
				payAmount = new BigDecimal(next.getValue());
			}
		}
		Iterator<Map.Entry<String, String>> iterator2 = map.entrySet().iterator();
		while (iterator2.hasNext()){
			Map.Entry<String, String> next = iterator2.next();
			if (pattern.matcher(next.getKey()).matches()){
				Order order = new Order();
				order.setOrderNumber(orderNumber);//订单号
				order.setProductId(Long.parseLong(next.getKey()));//商品id
				order.setProductPrice(productService.getById(Long.parseLong(next.getKey())).getPriceSale());//单价
				order.setProductQuantity(Integer.parseInt(next.getValue()));//数量
				order.setUserId(getUser(session).getId());//用户ID
				order.setUserAddressId(addressId);//收货地址
				order.setShipMethodId(shipMethodId);//物流方式
				order.setPayMethodId(payMethodId);//支付方式
				order.setPayAmount(payAmount);//支付金额
				orderService.save(order);
			}
		}
		return Result.ok("提交成功");
	}

	/**
	 * @description: 查询物流方式
	 * @return: java.lang.Object
	 */
	@GetMapping("listShipMethod")
	public Object listShipMethod(){
		return Result.ok(shipMethodService.list());
	}

	/**
	 * @description: 查询支付方式
	 * @return: java.lang.Object
	 */
	@GetMapping("listPayMethod")
	public Object listPayMethod(){
		return Result.ok(payMethodService.list());
	}
}
