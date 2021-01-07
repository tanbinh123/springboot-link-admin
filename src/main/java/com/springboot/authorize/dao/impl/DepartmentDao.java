package com.springboot.authorize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.authorize.dao.IDepartmentDao;
import com.springboot.authorize.domain.auth.Department;
import com.springboot.core.jdbc.BaseDaoImpl;

/**
 * 科室数据持久层实现类
 * 
 * @ClassName: DepartmentDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:44:08
 *
 */
@Repository
public class DepartmentDao extends BaseDaoImpl implements IDepartmentDao {

	@Override
	public List<Department> selectAll() {
		String sql = "SELECT id,name,  parent_id as parentId,levels,for_service as forService,sorts from t_sys_dept r where deleted=0  order by sorts asc ";
		return super.select(sql, Department.class);
	}

	@Override
	public Department selectOne(Integer id) {
		return super.selectById(id, Department.class);
	}

	@Override
	public List<Department> findChild(Integer parentId) {
		String sql = "SELECT id,name,  parent_id as parentId,levels,for_service as forService,sorts,(SELECT top 1 tmp1.name  FROM t_sys_dept as tmp1 WHERE tmp1.parent_id=r.id )  as tmpChildName from t_sys_dept r where r.parent_id=? order by sorts asc ";
		return super.select(sql, new Object[] { parentId }, Department.class);
	}

	@Override
	public int insert(Department dept) {
		return super.insert(dept);
	}

	@Override
	public int update(Department dept) {
		return super.update(dept);
	}

	@Override
	public int delete(Department dept) {
		return super.delete(dept);
	}

}
