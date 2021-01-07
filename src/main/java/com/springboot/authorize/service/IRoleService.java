package com.springboot.authorize.service;

import java.util.List;

import com.springboot.authorize.domain.auth.Role;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.JqGridPage;

/**
 * 角色业务层接口
 * 
 * @ClassName: IRoleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:45:37
 *
 */
public interface IRoleService {

	JqGridPage<Role> queryPage(Role role);

	List<Role> queryAll() throws AuthException;

	List<Role> queryByUser(String userId) throws AuthException;

	Role query(Integer code) throws AuthException;

	boolean add(Role role) throws AuthException;

	boolean update(Role role) throws AuthException;

	boolean delete(Integer code) throws AuthException;

	boolean saveRelationDataScope(Role role);

	List<Integer> queryDataScope(Integer roleId);

	boolean updateState(Role role);

}
