package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.vo.RefundVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/12 17:05
 * @description:
 */
public interface RefundMapper extends BaseMapper<Refund> {

	@Select("SELECT r.*,pi.src AS src FROM refund AS r\n" +
			"LEFT JOIN product_image AS pi ON r.product_id=pi.product_id\n" +
			"WHERE r.order_id in(SELECT id from orders WHERE user_id=#{userId}) AND pi.type=0")
	List<RefundVO> listByUId(Long userId);
}
