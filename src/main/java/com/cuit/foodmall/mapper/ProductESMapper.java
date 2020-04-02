package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.foodmall.es.ProductES;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/2 10:05
 * @description:
 */
public interface ProductESMapper extends BaseMapper<ProductES> {

	@Select("SELECT p.id,p.name,c.name AS category,s.name AS storeName,p.price_sale,p.sales,pi.src FROM product AS p\n" +
			"INNER JOIN store AS s ON s.id=p.store_id\n" +
			"INNER JOIN category AS c ON c.id=p.category_id\n" +
			"INNER JOIN product_image AS pi ON p.id=pi.product_id\n" +
			"WHERE pi.type=0 AND p.del_flag=0 AND p.status=11")
	List<ProductES> list();
}
