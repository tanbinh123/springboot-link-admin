package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.common.algorithm.DepartmentAlgorithm;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
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
	@Requestauthorize("dept:list")
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(DepartmentAlgorithm.tree(departmentService.queryAll()));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 保存部门
	 * 
	 * @param dept
	 * @return
	 */
	@Requestauthorize("dept:add")
	@OpertionBLog(title = "新增部门")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.add(dept);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}

		return rep;
	}

	/**
	 * 修改
	 * 
	 * @param dept
	 * @return
	 */
	@Requestauthorize("dept:edit")
	@OpertionBLog(title = "修改部门")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.update(dept);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 移除
	 * 
	 * @param id
	 * @return
	 */
	@Requestauthorize("dept:del")
	@OpertionBLog(title = "删除部门")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.delete(id);
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
