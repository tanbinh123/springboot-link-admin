package com.springboot.core.web.mvc;

import com.springboot.common.AppContext;

/**
 * 
 * @ClassName: ResponseResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:53:02
 *
 */
public class ResponseResult {

	private int code;
	private String msg;
	private Object result;

	public ResponseResult() {

	}

	public ResponseResult(int code, String msg, Object result) {
		this.setCode(code);
		this.setMsg(msg);
		this.setResult(result);
	}

	public static ResponseResult success() {
		return new ResponseResult(AppContext.CODE_20000, "success", null);
	}

	public static ResponseResult success(Object result) {
		return new ResponseResult(AppContext.CODE_20000, "success", result);
	}

	public static ResponseResult error() {
		return new ResponseResult(AppContext.CODE_50000, "error", null);
	}

	public static ResponseResult error(String msg) {
		return new ResponseResult(AppContext.CODE_50000, msg, null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
