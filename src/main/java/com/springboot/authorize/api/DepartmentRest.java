package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.Department;
import com.springboot.authorize.service.IDepartmentService;
import com.springboot.common.algorithm.DepartmentAlgorithm;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 部门接口
 * 
 * @ClassName: DepartmentRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:56:18
 *
 */
@RestController
@RequestMapping(value = "/rest/department")
public class DepartmentRest extends BaseRest {
	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		return ResponseResult.success(DepartmentAlgorithm
				.tree(departmentService.queryAll()));
	}

	/**
	 * 保存部门
	 * 
	 * @param dept
	 * @return
	 */
	@CheckPermission("dept:add")
	@OpertionBLog(title = "新增部门")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Department dept) {
		departmentService.add(dept);
		return ResponseResult.success();
	}

	/**
	 * 修改
	 * 
	 * @param dept
	 * @return
	 */
	@CheckPermission("dept:edit")
	@OpertionBLog(title = "修改部门")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Department dept) {
		departmentService.update(dept);
		return ResponseResult.success();
	}

	/**
	 * 移除
	 * 
	 * @param id
	 * @return
	 */
	@CheckPermission("dept:del")
	@OpertionBLog(title = "删除部门")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		departmentService.delete(id);
		return ResponseResult.success();
	}
}
