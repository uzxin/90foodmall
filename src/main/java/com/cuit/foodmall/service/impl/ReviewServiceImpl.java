package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.mapper.ReviewMapper;
import com.cuit.foodmall.service.ReviewService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/17 17:03
 * @description:
 */
@Service("ReviewService")
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {
}
