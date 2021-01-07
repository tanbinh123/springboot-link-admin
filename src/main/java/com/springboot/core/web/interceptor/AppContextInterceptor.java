package com.springboot.core.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.common.AppContext;
import com.springboot.common.GlobalUser;
import com.springboot.common.utils.StringUtils;

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
public class AppContextInterceptor implements HandlerInterceptor {
	static List<String> permitUrl = new ArrayList<String>();
	static {
		permitUrl.add("/rest/user/login");
		permitUrl.add("/public/rest/");
		permitUrl.add("/index.html");
		permitUrl.add("/static");
		permitUrl.add("/favicon.ico");
	}

	private boolean permitAll(String requestURL) {
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

		String requestURL = request.getRequestURI();
		if (permitAll(requestURL)) {
			return true;
		}

		String token = GlobalUser.getToken();
		if (StringUtils.isBlank(token)) {
			returnNoLogin(request, response);
			return false;
		}

		UserInfo userInfo = GlobalUser.getUserInfo();
		if (userInfo == null) {
			returnNoLogin(request, response);
			return false;
		}
		return true;
	}

	private void returnNoLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		out.println("{\"code\":" + AppContext.CODE_50001
				+ ", \"msg\":\"Please login first!\"}");
		out.flush();
		out.close();
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
