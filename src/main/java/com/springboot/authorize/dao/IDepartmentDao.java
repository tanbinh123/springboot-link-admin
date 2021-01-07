package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.Department;

/**
 * 部门接口
* @ClassName: IDepartmentDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:38:39 
*
 */
public interface IDepartmentDao {

	Department selectOne(Integer id);

	List<Department> findChild(Integer parentId);

	List<Department> selectAll();

	int insert(Department dept);

	int update(Department dept);
	
	int delete(Department dept);

}
