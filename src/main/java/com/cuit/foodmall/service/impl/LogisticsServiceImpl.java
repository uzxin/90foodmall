package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Logistics;
import com.cuit.foodmall.mapper.LogisticsMapper;
import com.cuit.foodmall.service.LogisticsService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/12 10:48
 * @description:
 */
@Service("LogisticsService")
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {
}
