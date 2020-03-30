package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.dto.OrderAndAmount;
import com.cuit.foodmall.entity.dto.TransactionDTO;
import com.cuit.foodmall.entity.vo.OrderVO;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/30 09:00
 * @description: 交易统计
 */
@RestController
@RequestMapping("store/transactionStatistics")
public class StoreTransactionStatisticsController {

	@Autowired
	private OrderService orderService;

	/**
	 * @description: 根据不同状态统计订单
	 * @param: session
	 * @return: java.lang.Object
	 */
	@GetMapping("getData")
	public Object getData(HttpSession session){
		TransactionDTO t = new TransactionDTO();
		BigDecimal estimatedIncome = new BigDecimal(BigInteger.ZERO);//预计收入
		BigDecimal arrived = new BigDecimal(BigInteger.ZERO);//已到账
		BigDecimal unaccounted = new BigDecimal(BigInteger.ZERO);//未到账
		BigDecimal refunded = new BigDecimal(BigInteger.ZERO);//已退款
		BigDecimal notRefunded = new BigDecimal(BigInteger.ZERO);//未退款
		int pendingPayment = 0;//待支付订单数
		int toBeDelivered = 0;//待发货订单数
		int toBeReceived = 0;//待收货订单数
		int comment = 0;//待评价订单数
		int pendingRefund = 0;//待退款订单数
		int refunded_orders = 0;//已退款订单数
		int completed = 0;//已完成订单数
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		List<Order> orders = orderService.list(new QueryWrapper<Order>().last("AND product_id IN(SELECT id FROM product WHERE store_id ="+storeId+")"));
		t.setTotalOrders(orders.size());//订单总数
		for (Order order : orders) {
			switch (order.getStatus()){
				case "3":pendingPayment += 1;
						unaccounted = unaccounted.add(order.getPayAmount());
						estimatedIncome = estimatedIncome.add(order.getPayAmount());break;
				case "4":toBeDelivered += 1;
						unaccounted = unaccounted.add(order.getPayAmount());
						estimatedIncome = estimatedIncome.add(order.getPayAmount());break;
				case "5":toBeReceived += 1;
						unaccounted = unaccounted.add(order.getPayAmount());
						estimatedIncome = estimatedIncome.add(order.getPayAmount());break;
				case "6":comment += 1;
						arrived = arrived.add(order.getPayAmount());
						estimatedIncome = estimatedIncome.add(order.getPayAmount());break;
				case "7":pendingRefund += 1;
						notRefunded = notRefunded.add(order.getPayAmount());break;
				case "8":refunded_orders += 1;
						refunded = refunded.add(order.getPayAmount());break;
				case "9":completed += 1;
					arrived = arrived.add(order.getPayAmount());
					estimatedIncome = estimatedIncome.add(order.getPayAmount());break;
			}
		}
		t.setEstimatedIncome(estimatedIncome);//预计收入
		t.setArrived(arrived);//已到账
		t.setUnaccounted(unaccounted);//未到账
		t.setRefunded(refunded);//已退款
		t.setNotRefunded(notRefunded);//未退款
		t.setPendingPayment(pendingPayment);//待支付订单数
		t.setToBeDelivered(toBeDelivered);//待发货订单数
		t.setToBeReceived(toBeReceived);//待收货订单数
		t.setComment(comment);//待评价订单数
		t.setPendingRefund(pendingRefund);//待退款订单数
		t.setRefunded_orders(refunded_orders);//已退款订单数
		t.setCompleted(completed);//已完成订单数
		return Result.ok(t);
	}

	/**
	 * @description: 最近七天的销售统计
	 * @param: session
	 * @return: java.lang.Object
	 */
	@GetMapping("lastWeek")
	public Object lastWeek(HttpSession session){
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		List<OrderVO> list = orderService.listForTheLastSevenDays(storeId);
		OrderAndAmount day1 = new OrderAndAmount(LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day2 = new OrderAndAmount(LocalDateTime.now().minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day3 = new OrderAndAmount(LocalDateTime.now().minusDays(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day4 = new OrderAndAmount(LocalDateTime.now().minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day5 = new OrderAndAmount(LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day6 = new OrderAndAmount(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		OrderAndAmount day7 = new OrderAndAmount(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 0, new BigDecimal(BigInteger.ZERO));
		for (OrderVO orderVO : list) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(orderVO.getCreateTime());
			if (date.equals(day1.getDate())){
				day1.setOrders(day1.getOrders()+1);day1.setAmount(day1.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day2.getDate())){
				day2.setOrders(day2.getOrders()+1);day2.setAmount(day2.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day3.getDate())){
				day3.setOrders(day3.getOrders()+1);day3.setAmount(day3.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day4.getDate())){
				day4.setOrders(day4.getOrders()+1);day4.setAmount(day4.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day5.getDate())){
				day5.setOrders(day5.getOrders()+1);day5.setAmount(day5.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day6.getDate())){
				day6.setOrders(day6.getOrders()+1);day6.setAmount(day6.getAmount().add(orderVO.getPayAmount()));
			}else if(date.equals(day7.getDate())){
				day7.setOrders(day7.getOrders()+1);day7.setAmount(day7.getAmount().add(orderVO.getPayAmount()));
			}
		}
		List<OrderAndAmount> datas = Arrays.asList(day1,day2,day3,day4,day5,day6,day7);
		return Result.ok(datas);
	}

	/**
	 * @description: 获取当前时间段内的收入支出情况
	 * @param: startDate
	 * @param: endDate
	 * @return: java.lang.Object
	 */
	@GetMapping("fundStatistics")
	public Object fundStatistics(String startDate, String endDate, HttpSession session) throws ParseException {
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		/*QueryWrapper<Order> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
			wrapper.between("create_time", startDate, endDate);
		}else{
			//默认查询最近七天
			wrapper.between("create_time", LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		wrapper.last("AND product_id IN(SELECT id FROM product WHERE store_id ="+storeId+")");
		List<Order> orders = orderService.list(wrapper);*/
		LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM--dd"));
		LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM--dd"));
		for (LocalDateTime local = start.plusDays(1); local.isBefore(end); ){
			System.out.println(local);
			System.out.println(end.minusDays(1));
		}
		System.out.println(end);

		return Result.ok();
	}
}
