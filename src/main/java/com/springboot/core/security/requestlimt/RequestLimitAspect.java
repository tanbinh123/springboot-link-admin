package com.springboot.core.security.requestlimt;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.common.utils.IPUtils;
import com.springboot.core.SysManager;
import com.springboot.core.exception.RequestLimitException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.redis.IRedis;
import com.springboot.core.web.spring.SpringMVCUtil;

/**
 * 请求频率限制
 * 
 * @ClassName: RequestLimitAspect
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年11月29日 上午9:46:04
 *
 */
@Aspect
@Component
@Order(1)
public class RequestLimitAspect {

	private static final String REQ_LIMIT = "req_limit_";

	/**
	 * 定义拦截规则：拦截com.springboot.bcode.api包下面的所有类中，有@RequestLimit Annotation注解的方法
	 * 。
	 */
	@Around("execution(* com.springboot..*.*(..)) "
			+ "and @annotation(com.springboot.core.security.requestlimt.RequestLimit)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		RequestLimit limt = ((MethodSignature) pjp.getSignature()).getMethod()
				.getAnnotation(RequestLimit.class);
		if (limt == null) {
			return pjp.proceed();
		}

		HttpServletRequest request = SpringMVCUtil.getRequest();

		int time = limt.time();
		int count = limt.count();
		int waits = limt.waits();

		String ip = IPUtils.getIpAddr(request);
		String url = request.getRequestURI();

		String key = requestLimitKey(url, ip);
		IRedis redis = SysManager.getRedis();
		int nowCount = redis.get(key) == null ? 0 : Integer.valueOf(redis
				.get(key));

		if (nowCount == 0) {
			nowCount++;
			redis.set(key, String.valueOf(nowCount), time);
		} else {
			nowCount++;
			redis.set(key, String.valueOf(nowCount));
			if (nowCount >= count) {
				LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]访问次数["
						+ nowCount + "]超过了限定的次数[" + count + "]限定时间[" + waits
						+ "秒]");
				redis.expire(key, waits);
				throw new RequestLimitException("服务拒接请求");
			}
		}

		return pjp.proceed();
	}

	/**
	 * requestLimitKey: url_ip
	 * 
	 * @param url
	 * @param ip
	 * @return
	 */
	private static String requestLimitKey(String url, String ip) {

		StringBuilder sb = new StringBuilder();
		sb.append(REQ_LIMIT);
		sb.append(url);
		sb.append("_");
		sb.append(ip);
		return sb.toString();
	}

}