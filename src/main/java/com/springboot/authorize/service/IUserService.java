package com.springboot.authorize.service;

import com.springboot.authorize.domain.auth.LoginVO;
import com.springboot.authorize.domain.auth.ModifyPwdVO;
import com.springboot.authorize.domain.auth.UserInfo;
import com.springboot.authorize.domain.auth.UserInfoVO;
import com.springboot.core.web.mvc.JqGridPage;

/**
 * 用户业务层接口
 * 
 * @ClassName: IUserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:45:23
 *
 */
public interface IUserService {

	JqGridPage<UserInfo> queryPage(UserInfo user);

	String login(LoginVO vo) throws Exception;

	UserInfo info();

	UserInfo find(String uid);

	UserInfo find(UserInfo user);

	void modifyPwd(ModifyPwdVO vo);

	boolean add(UserInfoVO vo);

	boolean update(UserInfoVO vo);

	boolean delete(String uid);

	boolean updateState(UserInfoVO vo);

}
