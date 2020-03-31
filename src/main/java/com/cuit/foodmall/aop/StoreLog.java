package com.cuit.foodmall.aop;

import java.lang.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/31 12:02
 * @description: 店铺操作日志记录注解
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface StoreLog {
	String value() default "";
}
