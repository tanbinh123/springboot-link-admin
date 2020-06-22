package com.springboot.core.security.authorize;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.common.AppContext;
import com.springboot.common.GlobalUser;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.IPUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.logger.LoggerUtil;

/**
 * 权限验证aop
 * 
 * @ClassName: RequestauthorizeAspect
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:54:07
 *
 */
@Order(2)
@Aspect
@Component
public class RequestauthorizeAspect {

	/**
	 * 定义拦截规则：拦截com.springboot.bcode.api包下面的所有类中，有@Requestauthorize注解的方法 。
	 */
	@Around("execution(* com.springboot.bcode.api..*(..)) "
			+ "and @annotation(com.springboot.core.security.authorize.Requestauthorize)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		Requestauthorize limt = method.getAnnotation(Requestauthorize.class);
		if (limt == null) {
			return pjp.proceed();
		}

		String value = limt.value();
		if (StringUtils.isBlank(value)) {
			return pjp.proceed();
		}

		HttpServletRequest request = HttpUtils.getRequest();
		HttpServletResponse response = HttpUtils.getResponse();

		UserInfo userInfo = GlobalUser.getUserInfo();
		if (userInfo == null) {
			try {
				return returnAuthorizeRequests(request, response);
			} catch (IOException e) {
			}
		}

		String ip = IPUtils.getIpAddr(request);
		String url = request.getRequestURI();

		// 验证是否演示账号
		if (userInfo.getName().equals("editor")) {
			// 不允许操作
			if (url.indexOf("add") > -1 || url.indexOf("save") > -1
					|| url.indexOf("update") > -1 || url.indexOf("modify") > -1
					|| url.indexOf("delete") > -1 || url.indexOf("remove") > -1)
				return returnDemoSystem(request, response);
		}

		List<Permission> permissions = userInfo.getPermissions();
		if (permissions == null || permissions.isEmpty()) {
			try {
				LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂未分配任何权限");
				return returnAuthorizeRequests(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			boolean hasPermission = false;
			for (Permission perm : permissions) {
				if (value.equals(perm.getPermissionFlag())) {
					hasPermission = true;
					break;
				}
			}
			if (!hasPermission) {
				try {
					LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂无权限");
					return returnAuthorizeRequests(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return pjp.proceed();
	}

	/**
	 * 无权限操作
	 *
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 *
	 */
	private String returnAuthorizeRequests(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		out.println("{\"code\":"
				+ AppContext.CODE_50002
				+ ", \"msg\":\"No permission, please contact the administrator!\"}");
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 演示系统无权限
	 *
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 *
	 */
	private String returnDemoSystem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		out.println("{\"code\":" + AppContext.CODE_50003
				+ ", \"msg\":\"Demo system does not allow operation!\"}");
		out.flush();
		out.close();
		return null;
	}

}
