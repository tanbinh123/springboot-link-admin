package com.springboot.authorize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.authorize.dao.IPermissionDao;
import com.springboot.authorize.domain.auth.Permission;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;

/**
 * 权限数据持久层实现类
 * 
 * @ClassName: PermissionDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:41:52
 *
 */
@Repository
public class PermissionDao extends BaseDaoImpl implements IPermissionDao {

	@Override
	public List<Permission> selectAll() {
		String sql = "SELECT id,name, parent_id as parentId,types,i_frame,url,levels ,description,sorts,component_name,component_path,icon,cache,hidden,permission_flag as permissionFlag from t_sys_permission order by sorts asc";
		return super.select(sql, Permission.class);
	}

	@Override
	public List<Permission> selectByRole(Integer[] roleIds) {
		String sql = "SELECT r.id,r.name, r.parent_id as parentId,r.types,r.i_frame,r.url,r.levels ,r.description,r.sorts,component_name,component_path,icon,cache,hidden,permission_flag as permissionFlag  from t_sys_role_permission rr inner join t_sys_permission r on rr.perm_id=r.id where rr.role_id in ("
				+ StringUtils.join(roleIds, ",") + ") order by r.sorts asc";
		return super.select(sql, Permission.class);
	}

	@Override
	public int insert(Permission right) {
		return super.insert(right);
	}

	@Override
	public int update(Permission right) {
		return super.update(right);
	}

	@Override
	public int delete(Permission right) {
		return super.delete(right);
	}

	@Override
	public List<Permission> find(Permission right) {
		return super.select(right);
	}

	@Override
	public List<Permission> findChild(Integer parentCode) {
		String sql = "SELECT id,name, parent_id as parentId,types,css,url,levels,sorts,dataScope,permission_flag as permissionFlag, (SELECT top 1 tmp1.name  FROM t_sys_right as tmp1 WHERE tmp1.parent_id=r.id )  as tmpChildName from t_sys_right r where r.parent_id=? order by sorts asc ";
		return super.select(sql, new Object[] { parentCode }, Permission.class);
	}

	@Override
	public Permission select(Integer id) {
		return super.selectById(id, Permission.class);
	}
}
