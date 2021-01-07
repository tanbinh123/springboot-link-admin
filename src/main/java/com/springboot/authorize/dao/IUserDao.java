package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.authorize.domain.auth.UserRole;
import com.springboot.core.web.mvc.JqGridPage;
/**
 * 用户接口
* @ClassName: IUserDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:36:22 
*
 */
public interface IUserDao {

	JqGridPage<UserInfo> selectPage(UserInfo user);

	UserInfo find(UserInfo user);

	List<UserInfo> findList(UserInfo user);

	UserInfo select(String id);

	int insert(UserInfo user);

	int[] insert(List<UserRole> list);

	int update(UserInfo user);

	int updateState(String uid,int state);

	int delete(UserInfo user);

	int delete(UserRole userRelationRole);

}
