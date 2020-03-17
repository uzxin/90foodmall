package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.entity.vo.OrderVO;
import com.cuit.foodmall.entity.vo.UserAddressVO;
import com.cuit.foodmall.service.*;
import com.cuit.foodmall.util.RandomUtil;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
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
	@Autowired
	private UserInformationService userInformationService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private LogisticsService logisticsService;

	/**
	 * @description: 生成订单
	 * @param: map
	 * @return: java.lang.Object
	 */
	@PostMapping("create")
	public Object create(@RequestBody Map<String,String> map, HttpSession session){
		Long addressId = null;
		Long shipMethodId = null;
		Long payMethodId = null;
		String message = null;
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
		}
		Iterator<Map.Entry<String, String>> iterator2 = map.entrySet().iterator();
		while (iterator2.hasNext()){
			Map.Entry<String, String> next = iterator2.next();
			if (pattern.matcher(next.getKey()).matches()){
				Order order = new Order();
				order.setOrderNumber(RandomUtil.get32());//订单号
				order.setProductId(Long.parseLong(next.getKey()));//商品id
				order.setProductPrice(productService.getById(Long.parseLong(next.getKey())).getPriceSale());//单价
				order.setProductQuantity(Integer.parseInt(next.getValue()));//数量
				order.setUserId(getUser(session).getId());//用户ID
				order.setUserAddressId(addressId);//收货地址
				order.setShipMethodId(shipMethodId);//物流方式
				order.setPayMethodId(payMethodId);//支付方式
				//支付金额=单价*数量
				order.setPayAmount(productService.getById(Long.parseLong(next.getKey())).getPriceSale().multiply(new BigDecimal(Integer.parseInt(next.getValue()))));
				orderService.save(order);
			}
		}
		return Result.ok("提交成功");
	}

	/**
	 * @description: 查询用户名下订单
	 * @param: order
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Order order, HttpSession session){
		Long userId = ((User) session.getAttribute("user")).getId();//用户ID
		QueryWrapper<OrderVO> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(order.getStatus())){
			wrapper.eq("o.status", order.getStatus());
		}
		wrapper.eq("o.user_id", userId);
		wrapper.eq("pi.type", "0");
		wrapper.eq("o.del_flag", "0");
		List<OrderVO> list = orderService.listOrderByUid(wrapper);
		return Result.ok(list);
	}

	/**
	 * @description: 订单详情
	 * @param: orderId
	 * @return: java.lang.Object
	 */
	@GetMapping("details")
	public Object details(Long orderId){
		return Result.ok(orderService.getByOrderId (orderId));
	}

	/**
	 * @description: 提交退款申请
	 * @param: refund
	 * @return: java.lang.Object
	 */
	@PostMapping("refund")
	public Object refund(Refund refund){
		Long orderId = refund.getOrderId();
		Order order = orderService.getById(orderId);
		//查询下单人信息
		UserInformation ui = userInformationService.getById(order.getUserId());
		refund.setRefundUserName(ui.getRealName());
		refund.setRefundUserPhone(ui.getPhone());
		//查询店铺信息
		Product product = productService.getById(order.getProductId());
		Store store = storeService.getById(product.getStoreId());
		refund.setStoreId(store.getId());
		refundService.save(refund);
		return Result.ok("提交成功");
	}

	/**
	 * @description: 删除订单
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long id){
		orderService.removeById(id);
		return Result.ok("删除成功");
	}

	/**
	 * @description: 确认收货
	 * @param: orderId
	 * @return: java.lang.Object
	 */
	@GetMapping("confirmReceipt")
	public Object confirmReceipt(Long orderId){
		//将订单状态置为待评价
		UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
		wrapper.set("status", "6");
		wrapper.eq("id",orderId);
		orderService.update(wrapper);
		return Result.ok();
	}

	/**
	 * @description: 查询物流
	 * @param: orderId
	 * @return: java.lang.Object
	 */
	@GetMapping("getLogistics")
	public Object getLogistics(Long orderId){
		LambdaQueryWrapper<Logistics> wrapper = new QueryWrapper<Logistics>().lambda();
		wrapper.eq(Logistics::getOrderId, orderId);
		return Result.ok(logisticsService.getOne(wrapper));
	}

	/**
	 * @description: 查询收货人信息
	 * @param: userAddressId
	 * @return: java.lang.Object
	 */
	@GetMapping("getAddress")
	public Object getAddress(Long userAddressId){
		UserAddress u = userAddressService.getById(userAddressId);
		UserAddressVO vo = new UserAddressVO();
		BeanUtils.copyProperties(u, vo);
		//区县
		Address country = addressService.getById(u.getAddressId());
		vo.setCountry(country.getDistrict());
		//市
		Address city = addressService.getById(country.getPid());
		vo.setCity(city.getDistrict());
		//省
		Address province = addressService.getById(city.getPid());
		vo.setProvince(province.getDistrict());
		return Result.ok(vo);
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
