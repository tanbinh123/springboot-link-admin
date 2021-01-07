package com.springboot.authorize.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
/**
 * 角色权限对应表
* @ClassName: RolePermission 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:33:45 
*
 */
@Tables(table = "t_sys_role_permission")
public class RolePermission {

	@Columns(column = "role_id")
	private Integer roleId;
	@Columns(column = "perm_id")
	private Integer permId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	

}
