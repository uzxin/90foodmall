package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Opinion;
import com.cuit.foodmall.mapper.OpinionMapper;
import com.cuit.foodmall.service.OpinionService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/4/23 14:14
 * @description:
 */
@Service("OpinionService")
public class OpinionServiceImpl extends ServiceImpl<OpinionMapper, Opinion> implements OpinionService {
}
