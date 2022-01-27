package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.Permission;
import com.springboot.authorize.service.IPermissionService;
import com.springboot.common.algorithm.PermissionAlgorithm;
import com.springboot.core.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 权限接口
 * 
 * @ClassName: PermissionRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:56:06
 *
 */
@RestController
@RequestMapping(value = "rest/permission")
public class PermissionRest extends BaseRest {

	@Autowired
	private IPermissionService rightService;

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	@CheckPermission("permission:list")
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		return ResponseResult.success(PermissionAlgorithm.tree(rightService
				.queryAll()));
	}

	/**
	 * 根据角色id获取对应的权限
	 *
	 * @param @param roleId
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "allByRole/{roleId}")
	public ResponseResult queryAllCheckByRole(
			@PathVariable("roleId") Integer roleId) {
		return ResponseResult.success(PermissionAlgorithm.tree(rightService
				.queryByRole(new Integer[] { roleId })));
	}

	@CheckPermission("permission:add")
	@OpertionBLog(title = "新增权限")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Permission permission) {
		rightService.add(permission);
		return ResponseResult.success();
	}

	@CheckPermission("permission:edit")
	@OpertionBLog(title = "修改权限")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Permission right) {
		rightService.update(right);
		return ResponseResult.success();
	}

	@CheckPermission("permission:del")
	@OpertionBLog(title = "删除权限")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		rightService.delete(id);
		return ResponseResult.success();
	}

}
