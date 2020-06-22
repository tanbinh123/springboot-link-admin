package com.springboot.bcode.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
import com.springboot.core.web.mvc.JqGridParam;

/**
 * 角色表
 * 
 * @ClassName: Role
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月23日 下午2:25:23
 *
 */
@Tables(table = "t_sys_role")
public class Role extends JqGridParam {

	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "name")
	private String name;
	@Columns(column = "levels")
	private Integer levels;// 新增用户时只能赋予比自己级别低的角色

	@Columns(column = "description")
	private String description;

	@Columns(column = "data_scope")
	private Integer data_scope;// 数据范围（1：全部数据权限 2：自定数据权限 3：本部门及以下数据权限4：本部门数据权限 5：本人）
	// 自定义数据权限
	private Integer deptIds[];
	// 权限id
	private Integer permIds[];

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer[] getPermIds() {
		return permIds;
	}

	public void setPermIds(Integer[] permIds) {
		this.permIds = permIds;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getData_scope() {
		return data_scope;
	}

	public void setData_scope(Integer data_scope) {
		this.data_scope = data_scope;
	}

	public Integer[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(Integer[] deptIds) {
		this.deptIds = deptIds;
	}

}
