package com.springboot.core.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.common.AppContext;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 全局异常
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年11月11日 下午1:28:23
 *
 */
@ControllerAdvice
class GlobalExceptionHandler {

	// json exceptin
	@ExceptionHandler
	@ResponseBody
	public ResponseResult jsonErrorHandler(HttpServletRequest req, Exception e)
			throws Exception {
		ResponseResult result = new ResponseResult();
		if (e instanceof NotLoginException) { // 如果是未登录异常
			result.setCode(AppContext.CODE_50001);
			result.setMsg(e.getMessage());
		} else if (e instanceof NotPermissionException) { // 如果是权限异常
			result.setCode(AppContext.CODE_50002);
			result.setMsg(e.getMessage());
		} else if (e instanceof RequestLimitException) { // 请求限制异常
			result.setCode(AppContext.CODE_50004);
			result.setMsg(e.getMessage());
		} else if (e instanceof AuthException || e instanceof SystemException) {
			result.setCode(AppContext.CODE_50000);
			result.setMsg(e.getMessage());
		} else {
			result.setCode(AppContext.CODE_50000);
			result.setMsg("系统异常,请稍后再试！");
			LoggerUtil.error(e.getMessage());
		}

		return result;
	}
}