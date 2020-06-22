package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.ModifyPwdVO;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserInfoVO;
import com.springboot.bcode.service.IUserService;
import com.springboot.common.GlobalUser;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.security.requestlimt.RequestLimit;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 用户接口
 * 
 * @ClassName: UserRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2019年10月21日 下午4:47:25
 *
 */
@RestController
@RequestMapping(value = "/rest/user")
public class UserRest extends BaseRest {

	@Autowired
	private IUserService userService;

	// 一分钟请求100次，等待300秒
	@RequestLimit(time = 60, count = 100, waits = 300)
	@OpertionBLog(title = "登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody LoginVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.login(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("登陆异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 当前在线用户的信息包括角色权限菜单等
	 *
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "info")
	public ResponseResult info() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.info());
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
			LoggerUtil.error(e.getMessage());
		}
		return rep;
	}

	@OpertionBLog(title = "退出")
	@RequestMapping("logout")
	public ResponseResult logout() {
		ResponseResult rep = new ResponseResult();
		try {
			GlobalUser.destroyUser();
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@Requestauthorize("user:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody UserInfo user) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.queryPage(user));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	@OpertionBLog(title = "添加用户")
	@Requestauthorize("user:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.add(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改用户")
	@Requestauthorize("user:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.update(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}
	
	@OpertionBLog(title = "删除用户")
	@Requestauthorize("user:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("uid") String uid) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.delete(uid);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	
	
	@OpertionBLog(title = "修改密码")
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public ResponseResult modifyPwd(@RequestBody ModifyPwdVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modifyPwd(vo);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}

	@OpertionBLog(title = "更新用户状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody UserInfoVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.updateState(vo);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}

}
