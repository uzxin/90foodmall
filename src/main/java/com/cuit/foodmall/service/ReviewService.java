package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.vo.ReviewVO;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/17 17:02
 * @description:
 */
public interface ReviewService extends IService<Review> {
	/**
	 * @description: 查询用户评论
	 * @param: userId
	 * @return: java.util.List<com.cuit.foodmall.entity.Review>
	 */
	List<ReviewVO> listByUId(Long userId);
	/**
	 * @description: 查询店铺评论
	 * @param: ipage
	 * @param: wrapper
	 * @return: java.util.List<com.cuit.foodmall.entity.vo.ReviewVO>
	 */
	IPage<ReviewVO> listBySId(Page<Review> ipage, QueryWrapper<Review> wrapper);
}
