package com.springboot.common;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.redis.RedisUtils;
import com.springboot.core.web.session.CookieContext;

/**
 * 
 * @ClassName: GlobalUser
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:52:13
 *
 */
public class GlobalUser {

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
		RedisUtils.getRedis().set(AppContext.USER_INFO + "" + userInfo.getToken(),
				JSONObject.toJSONString(userInfo), 90, TimeUnit.DAYS);
	}

	/**
	 * get user information to redis
	 * 
	 * @param token
	 * @return
	 */
	public static UserInfo getUserInfo() {
		String token = getToken();
		if (StringUtils.isBlank(token)) {
			return null;
		}
		UserInfo userInfo = null;
		String str = RedisUtils.getRedis().get(AppContext.USER_INFO + "" + token);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		userInfo = JSONObject.parseObject(str, UserInfo.class);

		return userInfo;
	}

	public static void destroyUser() {
		RedisUtils.getRedis().delete(AppContext.USER_INFO + "" + getToken());
	}

	public static String getToken() {
		String token = HttpUtils.getRequest().getHeader(AppContext.TOKEN);
		if (StringUtils.isBlank(token)) {
			token = CookieContext.get(AppContext.TOKEN);
		}
		return token;
	}

}
