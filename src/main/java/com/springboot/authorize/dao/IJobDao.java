package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.Job;
import com.springboot.core.web.mvc.JqGridPage;
/**
 * 岗位接口
* @ClassName: IJobDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:37:55 
*
 */
public interface IJobDao {

	JqGridPage<Job> selectPage(Job job);
	
	List<Job> selectAll();

	Job selectOne(Job job);

	Job selectOne(Integer id);

	int insert(Job job);

	int update(Job job);

	int updateState(Integer id, int state);

	int delete(Job job);

}
