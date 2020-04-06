package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Carousel;
import com.cuit.foodmall.mapper.CarouselMapper;
import com.cuit.foodmall.service.CarouselService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/4/6 17:41
 * @description:
 */
@Service("CarouselService")
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
}
