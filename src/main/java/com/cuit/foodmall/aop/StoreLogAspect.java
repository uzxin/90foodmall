package com.cuit.foodmall.aop;

import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.StoreOperationLog;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.StoreOperationLogService;
import com.cuit.foodmall.util.IpAddress;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author: YX
 * @date: 2020/3/31 12:03
 * @description: 店铺日志：切面处理类
 */
@Aspect
@Component
public class StoreLogAspect {

	@Autowired
	private StoreOperationLogService storeOperationLogService;

	//定义切点 @Pointcut
	//在注解的位置切入代码
	@Pointcut("@annotation( com.cuit.foodmall.aop.StoreLog)")
	public void logPoinCut() {
	}

	//切面 配置通知
	@AfterReturning("logPoinCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		//保存日志
		StoreOperationLog log = new StoreOperationLog();
		//从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//获取切入点所在的方法
		Method method = signature.getMethod();
		//获取操作
		StoreLog storeLog = method.getAnnotation(StoreLog.class);
		if (storeLog != null) {
			String value = storeLog.value();
			log.setOperating(value);//保存获取的操作
		}
		//获取请求的类名
		String className = joinPoint.getTarget().getClass().getName();
		//获取请求的方法名
		String methodName = method.getName();
		log.setMethod(className + "." + methodName);
		//请求的参数
		//Object[] args = joinPoint.getArgs();
		//获取request对象
		HttpServletRequest request = ((ServletRequestAttributes)
				RequestContextHolder.getRequestAttributes()).getRequest();
		log.setIp(IpAddress.getIpAddress(request));//ip地址
		log.setDevice(System.getProperty("os.name"));//操作系统
		log.setBrowserType(UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser().getName());//浏览器名称
		HttpSession session = request.getSession();
		Store store = (Store) session.getAttribute("store");
		User user = (User) session.getAttribute("business");
		if (null != store && null != user){
			log.setStoreId(store.getId());
			log.setOperatorId(user.getId());
			log.setOperatorName(user.getUsername());
			//插入数据库
			storeOperationLogService.save(log);
		}
	}
}
