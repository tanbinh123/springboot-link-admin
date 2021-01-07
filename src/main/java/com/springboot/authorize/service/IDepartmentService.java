package com.springboot.authorize.service;

import java.util.List;

import com.springboot.authorize.domain.auth.Department;

/**
 * 部门业务层接口
 * 
 * @ClassName: IDepartmentService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:47:43
 *
 */
public interface IDepartmentService {
	List<Department> queryAll();

	List<Department> queryAllCompany();

	List<Department> queryAllChild(Integer parentId);

	/**
	 * 查询部门信息
	 * 
	 * @param id
	 * @return
	 */
	Department query(Integer id);

	boolean add(Department dept);

	boolean update(Department dept);

	boolean delete(Integer id);
}
