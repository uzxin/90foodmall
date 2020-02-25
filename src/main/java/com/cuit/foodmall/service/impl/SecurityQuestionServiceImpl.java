package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.SecurityQuestion;
import com.cuit.foodmall.mapper.SecurityQuestionMapper;
import com.cuit.foodmall.service.SecurityQuestionService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/25 14:32
 * @description:
 */
@Service("SecurityQuestionService")
public class SecurityQuestionServiceImpl extends ServiceImpl<SecurityQuestionMapper, SecurityQuestion> implements SecurityQuestionService {
}
