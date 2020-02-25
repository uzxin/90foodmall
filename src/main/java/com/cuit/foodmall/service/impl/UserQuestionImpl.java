package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.UserQuestion;
import com.cuit.foodmall.mapper.UserQuestionMapper;
import com.cuit.foodmall.service.UserQuestionService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/25 14:33
 * @description:
 */
@Service("UserQuestion")
public class UserQuestionImpl extends ServiceImpl<UserQuestionMapper, UserQuestion> implements UserQuestionService {
}
