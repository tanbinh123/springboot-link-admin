package com.springboot.core.security.permission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.authorize.domain.auth.Permission;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.common.UserHolder;
import com.springboot.common.utils.IPUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.exception.NotPermissionException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.web.spring.SpringMVCUtil;

/**
 * 权限验证aop
 * 
 * @ClassName: CheckPermissionAspect
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:54:07
 *
 */
@Order(2)
@Aspect
@Component
public class CheckPermissionAspect {

	/**
	 * 定义拦截规则：拦截com.springboot包下面的所有类中，有@CheckPermission注解的方法 。
	 */
	@Around("execution(* com.springboot..*.*(..)) "
			+ "and @annotation(com.springboot.core.security.permission.CheckPermission)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		CheckPermission checkPermission = ((MethodSignature) pjp.getSignature())
				.getMethod().getAnnotation(CheckPermission.class);
		if (checkPermission == null) {
			return pjp.proceed();
		}

		String value = checkPermission.value();
		if (StringUtils.isBlank(value)) {
			return pjp.proceed();
		}

		HttpServletRequest request = SpringMVCUtil.getRequest();
		UserInfo userInfo = UserHolder.getUserInfo();
		String ip = IPUtils.getIpAddr(request);
		String url = request.getRequestURI();

		List<Permission> permissions = userInfo.getPermissions();
		if (permissions == null || permissions.isEmpty()) {

			LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂未分配任何权限");

			throw new NotPermissionException("用户IP[" + ip + "]访问地址[" + url
					+ "]暂未分配任何权限");

		} else {
			boolean hasPermission = false;
			for (Permission perm : permissions) {
				if (value.equals(perm.getPermissionFlag())) {
					hasPermission = true;
					break;
				}
			}
			if (!hasPermission) {

				LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂无权限");

				throw new NotPermissionException("用户IP[" + ip + "]访问地址[" + url
						+ "]暂无权限");
			}
		}

		return pjp.proceed();
	}

}
