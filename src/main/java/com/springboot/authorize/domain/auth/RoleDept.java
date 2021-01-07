package com.springboot.authorize.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

/**
 * 数据权限表
 * 
 * @ClassName: RolePermission
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月24日 下午2:06:14
 *
 */
@Tables(table = "t_sys_role_dept")
public class RoleDept {

	@Columns(column = "role_id")
	private Integer roleId;
	@Columns(column = "dept_id")
	private Integer deptId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}
