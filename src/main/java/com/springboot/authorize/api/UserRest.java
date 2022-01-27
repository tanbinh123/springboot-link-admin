package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.LoginVO;
import com.springboot.authorize.domain.auth.ModifyPwdVO;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.authorize.domain.auth.UserInfoVO;
import com.springboot.authorize.service.IUserService;
import com.springboot.common.UserHolder;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
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
	// @RequestLimit(time = 60, count = 100, waits = 300)
	@OpertionBLog(title = "登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody LoginVO vo) {
		return ResponseResult.success(userService.login(vo));
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
		return ResponseResult.success(userService.info());
	}

	@OpertionBLog(title = "退出")
	@RequestMapping("logout")
	public ResponseResult logout() {
		UserHolder.destroyUser();
		return ResponseResult.success();
	}

	@CheckPermission("user:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody UserInfo user) {
		return ResponseResult.success(userService.queryPage(user));
	}

	@OpertionBLog(title = "添加用户")
	@CheckPermission("user:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody UserInfoVO user) {
		userService.add(user);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "修改用户")
	@CheckPermission("user:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody UserInfoVO user) {
		userService.update(user);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "删除用户")
	@CheckPermission("user:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("uid") String uid) {
		userService.delete(uid);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "修改密码")
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public ResponseResult modifyPwd(@RequestBody ModifyPwdVO vo) {
		userService.modifyPwd(vo);
		return ResponseResult.success();

	}

	@OpertionBLog(title = "更新用户状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody UserInfoVO vo) {
		userService.updateState(vo);
		return ResponseResult.success();

	}

}
