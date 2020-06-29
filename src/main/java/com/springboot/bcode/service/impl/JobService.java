package com.springboot.bcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bcode.dao.IJobDao;
import com.springboot.bcode.domain.auth.Job;
import com.springboot.bcode.service.IJobService;
import com.springboot.common.exception.AuthException;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class JobService implements IJobService {
	@Autowired
	private IJobDao jobDao;

	@Override
	public JqGridPage<Job> queryPage(Job job) {
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
