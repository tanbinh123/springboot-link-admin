package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.Role;
import com.springboot.authorize.service.IRoleService;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 角色接口
 * 
 * @ClassName: RoleRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:56:02
 *
 */
@RestController
@RequestMapping(value = "/rest/role")
public class RoleRest extends BaseRest {

	@Autowired
	private IRoleService roleService;

	@CheckPermission("role:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Role role) {
		return ResponseResult.success(roleService.queryPage(role));

	}

	@RequestMapping(value = "all")
	public ResponseResult all() {
		return ResponseResult.success(roleService.queryAll());

	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@CheckPermission("role:add")
	@OpertionBLog(title = "新增角色")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Role role) {
		roleService.add(role);
		return ResponseResult.success();
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@CheckPermission("role:edit")
	@OpertionBLog(title = "修改角色")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Role role) {
		roleService.update(role);
		return ResponseResult.success();
	}

	/**
	 * 移除角色
	 * 
	 * @param id
	 * @return
	 */
	@CheckPermission("role:del")
	@OpertionBLog(title = "删除角色")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		roleService.delete(id);
		return ResponseResult.success();
	}

	/**
	 * 分配角色对应的数据权限
	 * 
	 * @param roleRelationRightVO
	 * @return
	 */
	@CheckPermission("role:datascope")
	@OpertionBLog(title = "角色分配数据权限")
	@RequestMapping(value = "saveDataScope", method = RequestMethod.POST)
	public ResponseResult saveDataScope(@RequestBody Role role) {
		roleService.saveRelationDataScope(role);
		return ResponseResult.success();
	}

	@RequestMapping(value = "queryDataScope")
	public ResponseResult queryDataScope(@RequestParam("id") Integer roleId) {
		return ResponseResult.success(roleService.queryDataScope(roleId));
	}

	@OpertionBLog(title = "更新角色状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody Role role) {
		roleService.updateState(role);
		return ResponseResult.success();

	}

}
