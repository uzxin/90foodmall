package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.vo.ReviewVO;
import com.cuit.foodmall.mapper.ReviewMapper;
import com.cuit.foodmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/17 17:03
 * @description:
 */
@Service("ReviewService")
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public List<ReviewVO> listByUId(Long userId) {
		return reviewMapper.listByUId(userId);
	}

	@Override
	public IPage<ReviewVO> listBySId(Page<Review> ipage, QueryWrapper<Review> wrapper) {
		return reviewMapper.listBySId(ipage, wrapper);
	}
}
