package com.springboot.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.common.utils.IPUtils;
import com.springboot.common.utils.MD5Utils;
import com.springboot.common.utils.StringUtils;
import com.springboot.common.utils.UUIDUtils;
import com.springboot.core.SysManager;
import com.springboot.core.config.TokenConfig;
import com.springboot.core.web.mvc.CookieContext;
import com.springboot.core.web.spring.SpringMVCUtil;

/**
 * 
 * @ClassName: UserHolder
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:52:13
 *
 */
public class UserHolder {

	public final static Integer user_enable = 1;// 正常
	public final static Integer user_unable = 0;// 禁用

	/**
	 * set user informateion to redis
	 * 
	 * @param userInfoContext
	 */
	public static void setUserInfo(UserInfo userInfo) {
		if (userInfo == null) {
			return;
		}
		SysManager.getRedis().set(
				AppContext.USER_INFO + "" + userInfo.getToken(),
				JSONObject.toJSONString(userInfo),
				SysManager.getConfig().getTimeout());
	}

	/**
	 * get user information from redis
	 * 
	 * @param token
	 * @return
	 */
	public static UserInfo getUserInfo() {
		UserInfo userInfo = null;
		String str = SysManager.getRedis().get(
				AppContext.USER_INFO + "" + getToken());
		if (StringUtils.isBlank(str)) {
			return null;
		}
		userInfo = JSONObject.parseObject(str, UserInfo.class);

		return userInfo;
	}

	public static void destroyUser() {
		SysManager.getRedis().delete(AppContext.USER_INFO + "" + getToken());
	}

	public static String getToken() {

		TokenConfig config = SysManager.getConfig();
		HttpServletRequest request = SpringMVCUtil.getRequest();
		String tokenName = SysManager.getConfig().getTokenName();

		String tokenValue = null;

		// 1. 尝试从请求体里面读取
		if (tokenValue == null && config.getIsReadBody()) {
			tokenValue = request.getParameter(tokenName);
		}
		// 2. 尝试从header里读取
		if (tokenValue == null && config.getIsReadHead()) {
			tokenValue = request.getHeader(tokenName);
		}
		// 3. 尝试从cookie里读取
		if (tokenValue == null && config.getIsReadCookie()) {
			tokenValue = CookieContext.get(tokenName);
		}

		// 4. 返回
		return tokenValue;
	}

	/**
	 * thread security of generate token
	 * 
	 * @param request
	 * @return
	 */
	public static synchronized String generateToken() {

		StringBuilder token = new StringBuilder();
		token.append(UUIDUtils.generateUUID());
		token.append("_");
		token.append(IPUtils.getIpAddr(SpringMVCUtil.getRequest()));
		return MD5Utils.getMD5AndBase64(token.toString());

	}

}
