package com.springboot.authorize.service;

import java.util.List;

import com.springboot.authorize.domain.auth.Permission;
import com.springboot.core.exception.AuthException;

/**
 * 权限业务层接口
 * 
 * @ClassName: IPermissionService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:46:03
 *
 */
public interface IPermissionService {

	List<Permission> queryAll();

	List<Permission> queryByRole(Integer[] roleIds);

	Permission query(Integer code) throws AuthException;

	boolean add(Permission right) throws AuthException;

	boolean update(Permission right) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
