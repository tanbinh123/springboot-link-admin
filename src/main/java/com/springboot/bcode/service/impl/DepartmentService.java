package com.springboot.bcode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.common.AppContext;
import com.springboot.common.algorithm.DepartmentAlgorithm;
import com.springboot.common.exception.AuthException;
import com.springboot.common.utils.BeanUtils;
import com.springboot.core.redis.RedisUtils;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public List<Department> queryAll() {
		return departmentDao.selectAll();
	}

	@Override
	public List<Department> queryAllCompany() {
		List<Department> companylList = new ArrayList<Department>();
		String departmentStr = RedisUtils.getRedis().get(
				AppContext.Department_Key);
		List<Department> allDeptList = JSONArray.parseArray(departmentStr,
				Department.class);
		for (Department department : allDeptList) {
			if (department.getLevels() == 1) {
				companylList.add(department);
			}
		}
		return companylList;
	}

	@Override
	public List<Department> queryAllChild(Integer parentId) {
		List<Department> deptList = new ArrayList<Department>();
		String departmentStr = RedisUtils.getRedis().get(
				AppContext.Department_Key);
		List<Department> allDeptList = JSONArray.parseArray(departmentStr,
				Department.class);
		List<Department> list = DepartmentAlgorithm.findAllChild(parentId,
				allDeptList);
		if (list != null && !list.isEmpty()) {
			for (Department dept : list) {
				if (dept.getForService() == 1) {
					deptList.add(dept);
				}
			}
		}
		return deptList;
	}

	@Override
	public Department query(Integer id) {
		Department dept = departmentDao.selectOne(id);
		return dept;
	}

	@Override
	public boolean add(Department dept) {
		if (dept.getParentId() == null) {
			dept.setParentId(0);
		}
		dept.setDeleted(0);
		int result = departmentDao.insert(dept);
		if (result < 0) {
			throw new AuthException("操作失败");
		}

		// 更新redis
		List<Department> deptList = departmentDao.selectAll();
		if (deptList != null && deptList.isEmpty()) {
			RedisUtils.getRedis().set(AppContext.Department_Key,
					JSONArray.toJSONString(deptList));
		}

		return true;
	}

	@Override
	public boolean update(Department dept) {
		Department deptInfo = departmentDao.selectOne(dept.getId());
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		BeanUtils.copyObject(deptInfo, dept);
		int result = departmentDao.update(deptInfo);
		if (result < 0) {
			throw new AuthException("操作失败");
		}

		// 更新redis
		List<Department> deptList = departmentDao.selectAll();
		if (deptList != null && deptList.isEmpty()) {
			RedisUtils.getRedis().set(AppContext.Department_Key,
					JSONArray.toJSONString(deptList));
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		Department deptInfo = departmentDao.selectOne(id);
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		Department dept = new Department();
		dept.setId(deptInfo.getId());
		int result = departmentDao.delete(dept);
		if (result < 0) {
			throw new AuthException("操作失败");
		}

		// 更新redis
		List<Department> deptList = departmentDao.selectAll();
		if (deptList != null && deptList.isEmpty()) {
			RedisUtils.getRedis().set(AppContext.Department_Key,
					JSONArray.toJSONString(deptList));
		}
		return true;
	}

}
