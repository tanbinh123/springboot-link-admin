package com.springboot.authorize.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.authorize.dao.IDictDao;
import com.springboot.authorize.domain.auth.Dict;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.Page;

/**
 * 字典数据持久层实现类
 * 
 * @ClassName: DictDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:43:42
 *
 */
@Repository
public class DictDao extends BaseDaoImpl implements IDictDao {
	@Override
	public Page<Dict> selectPage(Dict dict) {
		List<Dict> list = super.select(
				getSqlPageHandle().handlerPagingSQL(pageSql(dict, 0),
						dict.getPage(), dict.getLimit()), null, Dict.class);
		int count = super.jdbcTemplate.queryForObject(pageSql(dict, 1), null,
				Integer.class);
		Page<Dict> page = new Page<Dict>(list, count,
				dict.getLimit(), dict.getPage());
		return page;
	}

	private String pageSql(Dict dict, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append("select * from t_sys_dict");
		} else {
			sql.append("select count(*) from t_sys_dict");
		}
		sql.append(" where 1=1");

		if (StringUtils.isNotBlank(dict.getData_type())) {
			sql.append(" or data_type like '%").append(
					dict.getData_type().trim() + "%' ");
			sql.append(" or data_key like '%").append(
					dict.getData_type().trim() + "%' ");
			sql.append(" or data_value like '%").append(
					dict.getData_type().trim() + "%' ");
		}
		if (type == 0) {
			if (StringUtils.isNotBlank(dict.getSidx())) {
				if ((dict.getSord().trim().equalsIgnoreCase("asc"))) {
					sql.append(" order by " + dict.getSidx().split(" ")[0]
							+ " asc");
				} else {
					sql.append(" order by " + dict.getSidx().split(" ")[0]
							+ " desc");
				}
			} else {
				sql.append(" order by id desc");
			}
		}
		return sql.toString();
	}

	@Override
	public List<Dict> select(Dict dict) {
		return super.select(dict);
	}

	@Override
	public int insert(Dict dict) {
		return super.insert(dict);
	}

	@Override
	public int update(Dict dict) {
		return super.update(dict);
	}

	@Override
	public int delete(Dict dict) {
		return super.delete(dict);
	}

	@Override
	public Dict select(Integer id) {
		return super.selectById(id, Dict.class);
	}

}
