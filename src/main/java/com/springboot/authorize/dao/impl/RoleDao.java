package com.springboot.authorize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.authorize.dao.IRoleDao;
import com.springboot.authorize.domain.auth.Role;
import com.springboot.authorize.domain.auth.RoleDept;
import com.springboot.authorize.domain.auth.RolePermission;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.Page;

/**
 * 角色数据持久层实现类
 * 
 * @ClassName: RoleDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:40:36
 *
 */
@Repository
public class RoleDao extends BaseDaoImpl implements IRoleDao {
	@Override
	public Page<Role> selectPage(Role role) {
		List<Role> list = super.select(
				getSqlPageHandle().handlerPagingSQL(rolePageSql(role, 0),
						role.getPage(), role.getLimit()), null, Role.class);
		int count = super.jdbcTemplate.queryForObject(rolePageSql(role, 1),
				null, Integer.class);
		Page<Role> page = new Page<Role>(list, count,
				role.getLimit(), role.getPage());
		return page;
	}

	private String rolePageSql(Role role, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append("select * from t_sys_role");
		} else {
			sql.append("select count(*) from t_sys_role");
		}
		sql.append(" where 1=1");

		if (StringUtils.isNotBlank(role.getName())) {
			sql.append(" and name like '%").append(
					role.getName().trim() + "%' ");
		}
		if (type == 0) {
			if (StringUtils.isNotBlank(role.getSidx())) {
				if ((role.getSord().trim().equalsIgnoreCase("asc"))) {
					sql.append(" order by " + role.getSidx().split(" ")[0]
							+ " asc");
				} else {
					sql.append(" order by " + role.getSidx().split(" ")[0]
							+ " desc");
				}
			} else {
				sql.append(" order by id asc");
			}
		}
		return sql.toString();
	}

	@Override
	public List<Role> selectByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.id,r.name,r.data_scope from t_sys_user_role ur INNER JOIN t_sys_role r on ur.role_id=r.id ");
		sql.append(" where r.state=1 and  ur.user_id=? ");

		return super
				.select(sql.toString(), new Object[] { userId }, Role.class);
	}

	@Override
	public List<Role> select(Role role) {
		return super.select(role);
	}

	@Override
	public int insert(Role role) {
		return super.insert(role);
	}

	@Override
	public int insertRetrunId(Role role) {
		return super.insertReturnAutoIncrement(role);
	}

	@Override
	public int update(Role role) {
		return super.update(role);
	}

	@Override
	public int delete(Role role) {
		return super.delete(role);
	}

	@Override
	public int[] insert(List<RolePermission> rpList) {
		return super.batchInsert(rpList);
	}

	@Override
	public int delete(RolePermission roleRelationRight) {
		return super.delete(roleRelationRight);
	}

	@Override
	public Role select(Integer id) {
		return super.selectById(id, Role.class);
	}

	@Override
	public int[] insertRoleDetp(List<RoleDept> list) {
		return super.batchInsert(list);
	}

	@Override
	public int deleteRoleDetp(RoleDept roleDept) {
		return super.delete(roleDept);
	}

	@Override
	public List<Integer> selectRoleDetp(Integer roleId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dept_id from  t_sys_role_dept  ");
		sql.append(" where  role_id=" + roleId + " ");
		return super.getJdbcTemplate().queryForList(sql.toString(),
				Integer.class);
	}

	@Override
	public int updateState(Integer id, int state) {
		StringBuilder sql = new StringBuilder();
		sql.append("update t_sys_role set state=? where id=?");
		return super.insertOrUpdateOrDelete(sql.toString(), new Object[] {
				state, id });
	}
}
