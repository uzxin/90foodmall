package com.cuit.foodmall;

import com.cuit.foodmall.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author: YX
 * @date: 2020/2/24 11:27
 * @description:
 */
@SpringBootTest
public class TestEmailSend {

	@Autowired
	private EmailUtil emailUtil;

	@Test
	public void test1() throws InterruptedException {
			emailUtil.send("2449172727@qq.com","邀请","不知今晚是否有机会能跟厂长一起吃鸡");
	}
}
