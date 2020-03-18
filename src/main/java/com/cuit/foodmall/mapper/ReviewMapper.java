package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.vo.ReviewVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/17 17:02
 * @description:
 */
public interface ReviewMapper extends BaseMapper<Review> {

	@Select("SELECT r.*,pi.src AS src FROM review AS r\n" +
			"LEFT JOIN product_image AS pi ON pi.product_id=r.product_id\n" +
			"WHERE pi.type=0 AND r.del_flag='0' AND r.commentator_id=#{userId}")
	List<ReviewVO> listByUId(Long userId);

	@Select("SELECT r.*,o.order_number AS orderNumber FROM review AS r\n" +
			"LEFT JOIN orders AS o ON o.id=r.order_id ${ew.customSqlSegment}")
	IPage<ReviewVO> listBySId(Page<Review> ipage, @Param(Constants.WRAPPER) QueryWrapper<Review> wrapper);
}
