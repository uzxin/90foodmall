package com.cuit.foodmall.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: YX
 * @date: 2020/2/24 14:06
 * @description: 发送邮件
 */
@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	private static EmailUtil emailUtil;
	@PostConstruct
	public void initialize() {
		emailUtil= this;
		emailUtil.javaMailSender = this.javaMailSender;
	}

	/**
	 * @description: 发送
	 * @param: recept 收件人
	 * @param: subject 邮件主题
	 * @param: text 内容
	 * @return 0代表成功，1代表失败
	 */
	public static int send(String recept, String subject, String text){
		Logger logger = LoggerFactory.getLogger(EmailUtil.class);
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom("862273665@qq.com");
			simpleMailMessage.setTo(recept);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(text);
			emailUtil.javaMailSender.send(simpleMailMessage);
			logger.info("邮件发送成功");
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("邮件发送失败");
			return 1;
		}
	}
}
