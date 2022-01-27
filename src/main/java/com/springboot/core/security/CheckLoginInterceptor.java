package com.springboot.core.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.common.UserHolder;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.exception.NotLoginException;

/**
 * 拦截器，优先执行，验证用户是否登录
 * 
 * @ClassName: AppContextInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:53:20
 *
 */
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {
	static List<String> permitUrl = new ArrayList<String>();
	static {
		permitUrl.add("/rest/user/login");
		permitUrl.add("/public/rest/");
		permitUrl.add("/index.html");
		permitUrl.add("/static");
		permitUrl.add("/favicon.ico");
	}

	private boolean permitUrl(String requestURL) {
		for (String url : permitUrl) {
			if (requestURL.startsWith(url) || "/".equals(requestURL)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (!permitUrl(request.getRequestURI())) {
			String token = UserHolder.getToken();
			if (StringUtils.isBlank(token)) {
				throw new NotLoginException("无效的token,请先登录!");
			}

			UserInfo userInfo = UserHolder.getUserInfo();
			if (userInfo == null) {
				throw new NotLoginException("未获取到有效的用户信息,请先登录!");
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
	}
}
