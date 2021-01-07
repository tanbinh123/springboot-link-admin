package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.Role;
import com.springboot.authorize.domain.auth.RoleDept;
import com.springboot.authorize.domain.auth.RolePermission;
import com.springboot.core.web.mvc.JqGridPage;
/**
 * 角色接口
* @ClassName: IRoleDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:36:39 
*
 */
public interface IRoleDao {
	JqGridPage<Role> selectPage(Role role);

	List<Role> selectByUserId(String userId);

	Role select(Integer id);

	List<Role> select(Role role);

	int insertRetrunId(Role role);

	int insert(Role role);

	int update(Role role);

	int delete(Role role);

	int[] insert(List<RolePermission> rpList);

	int delete(RolePermission roleRelationRight);

	int[] insertRoleDetp(List<RoleDept> list);

	int deleteRoleDetp(RoleDept roleDept);

	List<Integer> selectRoleDetp(Integer roleId);

	int updateState(Integer id, int state);

}
