package com.cuit.foodmall.util;

/**
 * @author: YX
 * @date: 2020/2/21 15:25
 * @description: 短信模板
 */
public class SMSModel {

	/*
	常用模板
	 */
	public static String common(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return "您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。";
	}
	/*
	注册模板
	 */
	public static String register(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return "验证码为："+mobile_code+"，您正在注册成为【玖零食品商城】会员，感谢您的支持！";
	}
	/*
	登陆模板
	 */
	public static String login(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return "您的登录验证码是："+mobile_code+"。打死不要告诉别人！";
	}
	/*
	找回密码模板
	 */
	public static String findPassword(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return "您正在找回密码，验证码："+mobile_code+"。";
	}
	/*
	绑定模板
	 */
	public static String bound(){
		int mobile_code = (int)((Math.random()*9+1)*100000);
		return "验证码："+mobile_code+"，请即时输入。您正在进行绑定手机号，绑定后有效提升您的账号安全。";
	}
}
