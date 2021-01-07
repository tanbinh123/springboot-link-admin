package com.springboot.authorize.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

/**
 * 用户角色对应表
 * 
 * @ClassName: UserRole
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:34:35
 *
 */
@Tables(table = "t_sys_user_role")
public class UserRole {
	@Columns(column = "user_id")
	private String userId;
	@Columns(column = "role_id")
	private Integer roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
