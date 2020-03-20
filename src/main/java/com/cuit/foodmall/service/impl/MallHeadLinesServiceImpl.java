package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.MallHeadLines;
import com.cuit.foodmall.mapper.MallHeadLinesMapper;
import com.cuit.foodmall.service.MallHeadLinesService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/19 10:40
 * @description:
 */
@Service("MallHeadLinesService")
public class MallHeadLinesServiceImpl extends ServiceImpl<MallHeadLinesMapper, MallHeadLines> implements MallHeadLinesService {
}
