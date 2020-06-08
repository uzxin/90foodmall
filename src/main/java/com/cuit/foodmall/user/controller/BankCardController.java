package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.UserBankCard;
import com.cuit.foodmall.service.BankService;
import com.cuit.foodmall.service.UserBankCardService;
import com.cuit.foodmall.util.Result;
import com.cuit.foodmall.util.SMSModel;
import com.cuit.foodmall.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: YX
 * @date: 2020/2/27 14:38
 * @description: 用户绑定银行卡
 */
@RestController
@RequestMapping("bankcard")
@Slf4j
public class BankCardController extends BaseController{

	@Autowired
	private BankService bankService;
	@Autowired
	private UserBankCardService userBankCardService;

	/**
	 * @description: 查询所有绑定的银行卡
	 * @return: java.lang.Object
	 */
	@GetMapping("listBankCard")
	public Object listBankCard(HttpServletRequest request){
		LambdaQueryWrapper<UserBankCard> wrapper = new QueryWrapper<UserBankCard>().lambda();
		wrapper.eq(UserBankCard::getUserId, getUser(request).getId());
		return Result.ok(userBankCardService.list(wrapper));
	}

	/**
	 * @description: 绑定银行卡
	 * @param: userBankCard
	 * @return: java.lang.Object
	 */
	@PostMapping("save")
	public Object save(UserBankCard userBankCard, HttpServletRequest request){
		userBankCard.setUserId(getUser(request).getId());
		userBankCardService.save(userBankCard);
		return Result.ok("绑定成功");
	}

	/**
	 * @description: 解绑
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object del(Long id){
		if(userBankCardService.removeById(id)){
			return Result.ok("解绑成功");
		}
		return Result.ok("解绑失败");
	}

	/**
	 * @description: 查询所有银行
	 * @return: java.lang.Object
	 */
	@GetMapping("listBank")
	public Object listBank(){
		return Result.ok(bankService.list());
	}

	/**
	 * @description: 获取手机验证码
	 * @return: java.lang.Object
	 */
	@GetMapping("getMobleCode")
	public Object getMobleCode(String mobile) throws IOException, DocumentException {
		if (null == mobile || "".equals(mobile)){
			return Result.error("手机号不正确");
		}
		String mobileCode = SMSUtil.send(mobile, SMSModel.bindBankCard());//短信验证码
		if (null == mobileCode){
			return Result.error("短信发送失败");
		}
		return Result.ok("发送成功", mobileCode);
	}
}
