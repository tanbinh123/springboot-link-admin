package com.springboot.authorize.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.authorize.dao.IJobDao;
import com.springboot.authorize.domain.auth.Job;
import com.springboot.authorize.service.IJobService;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.exception.AuthException;
import com.springboot.core.web.mvc.Page;

/**
 * 岗位业务层实现类
 * 
 * @ClassName: JobService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:53:14
 *
 */
@Service
public class JobService implements IJobService {
	@Autowired
	private IJobDao jobDao;

	@Override
	public Page<Job> queryPage(Job job) {
		if (job == null) {
			throw new AuthException("参数不能为空");
		}
		return jobDao.selectPage(job);
	}

	@Override
	public List<Job> queryAll() {
		return jobDao.selectAll();
	}

	@Override
	public boolean add(Job job) {

		if (StringUtils.isBlank(job.getName())) {
			throw new AuthException("岗位名不能为空");
		}
		if (job.getState() == null) {
			throw new AuthException("状态不能为空");
		}
		jobDao.insert(job);
		return true;
	}

	@Override
	public boolean update(Job job) {

		if (job.getId() == null) {
			throw new AuthException("id为空");
		}
		if (StringUtils.isBlank(job.getName())) {
			throw new AuthException("岗位名不能为空");
		}
		if (job.getState() == null) {
			throw new AuthException("状态不能为空");
		}
		jobDao.update(job);
		return true;

	}

	@Override
	public boolean delete(Integer id) {
		if (id == null) {
			throw new AuthException("id不能为空");
		}
		Job job = new Job();
		job.setId(id);
		jobDao.delete(job);
		return true;
	}

	@Override
	public Job find(Integer id) {
		if (id == null) {
			throw new AuthException("id不能为空");
		}
		return jobDao.selectOne(id);
	}

	@Override
	public Job find(Job job) {
		return jobDao.selectOne(job);
	}

	@Override
	public boolean updateState(Job job) {
		if (job.getId() == null) {
			throw new AuthException("id为空");
		}
		jobDao.updateState(job.getId(), job.getState());
		return true;
	}
}
