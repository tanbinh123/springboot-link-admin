package com.springboot.authorize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.authorize.dao.IUserDao;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.authorize.domain.auth.UserRole;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.Page;

/**
 * 用户数据持久层实现类
 * 
 * @ClassName: UserDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:39:04
 *
 */
@Repository
public class UserDao extends BaseDaoImpl implements IUserDao {

	@Override
	public Page<UserInfo> selectPage(UserInfo user) {
		List<UserInfo> list = super.select(
				getSqlPageHandle().handlerPagingSQL(userPageSql(user, 0),
						user.getPage(), user.getLimit()), null, UserInfo.class);
		int count = super.jdbcTemplate.queryForObject(userPageSql(user, 1),
				null, Integer.class);
		Page<UserInfo> page = new Page<UserInfo>(list, count,
				user.getLimit(), user.getPage());
		return page;
	}

	private String userPageSql(UserInfo user, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append(" select  u.uid,u.name,u.vsername,u.password,u.mobile,u.email,u.createTime,u.state,u.deptid,d.name as deptName,u.jobid,j.name as jobName from t_sys_user u  ");
			sql.append(" left join t_sys_dept d on d.id=u.deptid ");
			sql.append(" left join t_sys_job j on j.id=u.jobid ");
		} else {
			sql.append(" select count(*) from t_sys_user u  ");
			sql.append(" left join t_sys_dept d on d.id=u.deptid ");
			sql.append(" left join t_sys_job j on j.id=u.jobid ");
		}
		sql.append(" where 1=1");

		if (StringUtils.isNotBlank(user.getName())) {
			sql.append(" and u.name like '%").append(
					user.getName().trim() + "%' ");
		}
		if (StringUtils.isNotBlank(user.getVserName())) {
			sql.append(" and u.vsername like '%").append(
					user.getVserName().trim() + "%' ");
		}
		if (StringUtils.isNotBlank(user.getMobile())) {
			sql.append(" and u.mobile like '%").append(
					user.getMobile().trim() + "%' ");
		}
		if (user.getDeptid() != null && user.getDeptid() != 2) {
			sql.append(" and u.deptid=" + user.getDeptid() + "");
		}
		if (user.getState() != null) {
			sql.append(" and u.state=" + user.getState() + "");
		}
		if (type == 0) {
			if (StringUtils.isNotBlank(user.getSidx())) {
				if ((user.getSord().trim().equalsIgnoreCase("asc"))) {
					sql.append(" order by " + user.getSidx().split(" ")[0]
							+ " asc");
				} else {
					sql.append(" order by " + user.getSidx().split(" ")[0]
							+ " desc");
				}
			} else {
				sql.append(" order by createTime asc");
			}
		}
		return sql.toString();
	}

	@Override
	public UserInfo find(UserInfo user) {
		List<UserInfo> userList = super.select(user);
		if (userList == null || userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<UserInfo> findList(UserInfo user) {
		return super.select(user);
	}

	@Override
	public int insert(UserInfo user) {
		return super.insert(user);
	}

	@Override
	public int update(UserInfo user) {
		return super.update(user);
	}

	@Override
	public int delete(UserInfo user) {
		return super.delete(user);
	}

	@Override
	public UserInfo select(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select u.uid,u.name,u.password,u.vsername,u.mobile,u.createTime,u.state,d.id as deptid,d.name as deptName,j.name as jobName from t_sys_user u");
		// sql.append(" left join t_sys_user_role ur on ur.user_id=u.uid");
		// sql.append(" left join t_sys_role r on r.id=ur.role_id");
		sql.append(" left join t_sys_dept d on d.id=u.deptid");
		sql.append(" left join t_sys_job j on j.id=u.jobid ");
		sql.append(" where u.uid='" + id + "'");
		List<UserInfo> list = super
				.select(sql.toString(), null, UserInfo.class);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delete(UserRole userRelationRole) {
		return super.delete(userRelationRole);
	}

	@Override
	public int[] insert(List<UserRole> list) {
		return super.batchInsert(list);
	}

	@Override
	public int updateState(String uid, int state) {
		StringBuilder sql = new StringBuilder();
		sql.append("update t_sys_user set state=? where uid=?");
		return super.insertOrUpdateOrDelete(sql.toString(), new Object[] {
				state, uid });
	}

}
