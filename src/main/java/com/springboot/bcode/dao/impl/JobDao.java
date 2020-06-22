package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.bcode.dao.IJobDao;
import com.springboot.bcode.domain.auth.Job;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.JqGridPage;

@Repository
public class JobDao extends BaseDaoImpl implements IJobDao {

	@Override
	public JqGridPage<Job> selectPage(Job job) {
		List<Job> list = super.select(
				getSqlPageHandle().handlerPagingSQL(pageSql(job, 0),
						job.getPage(), job.getLimit()), null, Job.class);
		int count = super.jdbcTemplate.queryForObject(pageSql(job, 1), null,
				Integer.class);
		JqGridPage<Job> page = new JqGridPage<Job>(list, count, job.getLimit(),
				job.getPage());
		return page;
	}

	private String pageSql(Job job, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append("select * from t_sys_job ");
		} else {
			sql.append("select count(*) from t_sys_job ");
		}
		sql.append(" where 1=1");

		if (StringUtils.isNotBlank(job.getName())) {
			sql.append(" and name like '%")
					.append(job.getName().trim() + "%' ");
		}

		if (job.getState() != null) {
			sql.append(" and state=" + job.getState() + "");
		}
		if (type == 0) {
			if (StringUtils.isNotBlank(job.getSidx())) {
				if ((job.getSord().trim().equalsIgnoreCase("asc"))) {
					sql.append(" order by " + job.getSidx().split(" ")[0]
							+ " asc");
				} else {
					sql.append(" order by " + job.getSidx().split(" ")[0]
							+ " desc");
				}
			} else {
				sql.append(" order by sorts asc");
			}
		}
		return sql.toString();
	}

	@Override
	public List<Job> selectAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_job where state=1 order by sorts asc");
		return super.select(sql.toString(), Job.class);
	}

	@Override
	public Job selectOne(Job job) {
		List<Job> list = super.select(job);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Job selectOne(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_job where state=1 and id="+id+"");
		return super.selectOne(sql.toString(), Job.class);
	}

	@Override
	public int insert(Job job) {
		return super.insert(job);
	}

	@Override
	public int update(Job job) {
		return super.update(job);
	}

	@Override
	public int delete(Job job) {
		return super.delete(job);
	}

	@Override
	public int updateState(Integer id, int state) {
		StringBuilder sql = new StringBuilder();
		sql.append("update t_sys_job set state=? where id=?");
		return super.insertOrUpdateOrDelete(sql.toString(), new Object[] { state,
				id });
	}

}
