package com.cuit.foodmall.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author: YX
 * @date: 2020/3/6 10:59
 * @description: 生成随机数
 */
public class RandomUtil {

	/*
	获取32位随机数字
	 */
	public static String get32(){
		DateTimeFormatter fmt16 = DateTimeFormatter.ofPattern("yyyyMMddhhmmssSS");
		int hashCode = UUID.randomUUID().toString().hashCode();
		//可能为负数
		if(hashCode<0){
			hashCode = -hashCode;
		}
		return (LocalDateTime.now().format(fmt16)+String.format("%016d", hashCode));
	}
}
