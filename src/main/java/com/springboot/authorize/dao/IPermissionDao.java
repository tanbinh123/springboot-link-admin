package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.Permission;

/**
 * 权限接口
* @ClassName: IPermissionDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:37:06 
*
 */
public interface IPermissionDao {
	Permission select(Integer id);

	List<Permission> selectAll();

	List<Permission> selectByRole(Integer[] roleIds);

	List<Permission> find(Permission right);

	List<Permission> findChild(Integer parentCode);

	int insert(Permission right);

	int update(Permission right);

	int delete(Permission right);

}
