package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/6 10:16
 * @description:
 */
public interface OrderMapper extends BaseMapper<Order> {

	@Select("SELECT o.*,p.name AS productName, s.name AS statusName,sm.name AS shipMethodName,pm.name AS payMethodName FROM orders AS o \n" +
			"\tLEFT JOIN product AS p ON o.product_id=p.id \n" +
			"\tLEFT JOIN ship_method AS sm ON o.ship_method_id=sm.id \n" +
			"\tLEFT JOIN pay_method AS pm ON o.pay_method_id=pm.id \n" +
			"\tLEFT JOIN status AS s ON o.status=s.id ${ew.customSqlSegment}")
	IPage<OrderVO> listOrderBySid(Page<OrderVO> ipage, @Param(Constants.WRAPPER) QueryWrapper<OrderVO> wrapper);

	@Select("SELECT o.*,p.name AS productName,pi.src AS src, s.name AS statusName,sm.name AS shipMethodName,pm.name AS payMethodName FROM orders AS o \n" +
			"\tLEFT JOIN product AS p ON o.product_id=p.id \n" +
			"\tLEFT JOIN product_image AS pi ON o.product_id=pi.product_id \n" +
			"\tLEFT JOIN ship_method AS sm ON o.ship_method_id=sm.id \n" +
			"\tLEFT JOIN pay_method AS pm ON o.pay_method_id=pm.id \n" +
			"\tLEFT JOIN status AS s ON o.status=s.id ${ew.customSqlSegment}")
	List<OrderVO> listOrderByUid(@Param(Constants.WRAPPER) QueryWrapper<OrderVO> wrapper);

	@Select("SELECT o.*,p.name AS productName,pi.src AS src, s.name AS statusName,sm.name AS shipMethodName,pm.name AS payMethodName FROM orders AS o \n" +
			"\tLEFT JOIN product AS p ON o.product_id=p.id \n" +
			"\tLEFT JOIN product_image AS pi ON o.product_id=pi.product_id \n" +
			"\tLEFT JOIN ship_method AS sm ON o.ship_method_id=sm.id \n" +
			"\tLEFT JOIN pay_method AS pm ON o.pay_method_id=pm.id \n" +
			"\tLEFT JOIN status AS s ON o.status=s.id WHERE o.id=#{orderId} AND o.del_flag=0 AND pi.type=0")
	OrderVO getByOrderId(Long orderId);

	@Select("SELECT * FROM orders\n" +
			"WHERE date_sub(curdate(), interval 7 day) <= date(create_time)\n" +
			"AND del_flag=0\n" +
			"AND product_id IN(SELECT id FROM product WHERE store_id =#{storeId})")
	List<OrderVO> listForTheLastSevenDays(Long storeId);
}
