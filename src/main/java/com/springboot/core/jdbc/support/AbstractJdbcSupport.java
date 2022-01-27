package com.springboot.core.jdbc.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.JdbcException;
import com.springboot.core.jdbc.sql.DynamicSql;
import com.springboot.core.jdbc.sql.SqlObject;

public abstract class AbstractJdbcSupport implements IObjectOperation,
		ISqlOperation {
	protected abstract JdbcTemplate getJdbcTemplate();

	// ---------------------------------object---------------------------------
	@Override
	public <T> T selectById(Object id, Class<T> tClass) {
		if (id == null) {
			throw new JdbcException("selectById id 不能为空");
		}
		SqlObject sqlObject = DynamicSql.findByIdSql(id, tClass);
		if (sqlObject == null) {
			throw new JdbcException("selectById sqlObject 不能为空");
		}

		List<T> list = getJdbcTemplate().query(sqlObject.getSql(),
				sqlObject.getParams().toArray(),
				new BeanPropertyRowMapper<T>((Class<T>) tClass));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T selectOne(T entity) {
		if (entity == null) {
			throw new JdbcException("selectOne entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.findSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("selectOne sqlObject 不能为空");
		}
		List<T> list = getJdbcTemplate().query(sqlObject.getSql(),
				sqlObject.getParams().toArray(),
				new BeanPropertyRowMapper<T>((Class<T>) entity.getClass()));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> select(T entity) {
		if (entity == null) {
			throw new JdbcException("select entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.findSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("select sqlObject 不能为空");
		}
		return getJdbcTemplate().query(sqlObject.getSql(),
				sqlObject.getParams().toArray(),
				new BeanPropertyRowMapper<T>((Class<T>) entity.getClass()));
	}

	@Override
	public <T> int insert(T entity) {
		if (entity == null) {
			throw new JdbcException("insert entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.insertSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("insert sqlObject 不能为空");
		}
		return insertOrUpdateOrDelete(sqlObject.getSql(), sqlObject.getParams()
				.toArray());
	}

	@Override
	public <T> Integer insertReturnAutoIncrement(T entity) {
		if (entity == null) {
			throw new JdbcException("insert entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.insertSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("insert sqlObject 不能为空");
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final Object[] params = sqlObject.getParams().toArray();
		if (params == null || params.length == 0) {
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(
							sqlObject.getSql(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					return ps;
				}
			}, keyHolder);

		} else {
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(
							sqlObject.getSql(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					for (int i = 0; i < params.length; i++)
						ps.setObject(i + 1, params[i]);
					return ps;
				}
			}, keyHolder);
		}
		return keyHolder.getKey().intValue();
	}

	@Override
	public <T> int update(T entity) {
		if (entity == null) {
			throw new JdbcException("update entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.updateSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("update sqlObject 不能为空");
		}
		return insertOrUpdateOrDelete(sqlObject.getSql(), sqlObject.getParams()
				.toArray());
	}

	@Override
	public <T> int delete(T entity) {
		if (entity == null) {
			throw new JdbcException("delete entity 不能为空");
		}
		SqlObject sqlObject = DynamicSql.deleteSql(entity);
		if (sqlObject == null) {
			throw new JdbcException("delete sqlObject 不能为空");
		}
		return insertOrUpdateOrDelete(sqlObject.getSql(), sqlObject.getParams()
				.toArray());
	}

	@Override
	public <T> int[] batchUpdate(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			throw new JdbcException("updateBatch collection 不能为空");
		}
		int row[] = null;
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		String sql = "";
		for (T t : collection) {
			SqlObject sqlObject = DynamicSql.updateSql(t);
			if (sqlObject == null) {
				throw new JdbcException("updateBatch sqlObject 不能为空");
			}
			if (StringUtils.isBlank(sql)) {
				sql = sqlObject.getSql();
			}
			batchArgs.add(sqlObject.getParams().toArray());
		}
		row = getJdbcTemplate().batchUpdate(sql, batchArgs);
		return row;
	}

	@Override
	public <T> int[] batchInsert(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			throw new JdbcException("batchInsert collection不能为空");
		}
		int row[] = null;
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		String sql = "";
		for (T t : collection) {
			SqlObject sqlObject = DynamicSql.insertSql(t);
			if (sqlObject == null) {
				throw new JdbcException("batchInsert sqlObject 不能为空");
			}
			if (StringUtils.isBlank(sql)) {
				sql = sqlObject.getSql();
			}
			batchArgs.add(sqlObject.getParams().toArray());
		}
		row = getJdbcTemplate().batchUpdate(sql, batchArgs);
		return row;
	}

	@Override
	public <T> int[] batchDelete(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			throw new JdbcException("batchDelete collection不能为空");
		}
		int row[] = null;
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		String sql = "";
		for (T t : collection) {
			SqlObject sqlObject = DynamicSql.deleteSql(t);
			if (sqlObject == null) {
				throw new JdbcException("batchDelete sqlObject 不能为空");
			}
			if (StringUtils.isBlank(sql)) {
				sql = sqlObject.getSql();
			}
			batchArgs.add(sqlObject.getParams().toArray());
		}
		row = getJdbcTemplate().batchUpdate(sql, batchArgs);
		return row;
	}

	// ---------------------------------sql---------------------------------
	@Override
	public <T> List<T> select(String sql, Class<T> tClass) {
		return select(sql, null, tClass);
	}

	@Override
	public <T> List<T> select(String sql, Object[] params, Class<T> tClass) {
		List<T> resultList = null;
		if (params != null && params.length > 0)
			resultList = getJdbcTemplate().query(sql, params,
					new BeanPropertyRowMapper<T>(tClass));
		else
			// BeanPropertyRowMapper是自动映射实体类的
			resultList = getJdbcTemplate().query(sql,
					new BeanPropertyRowMapper<T>(tClass));
		return resultList;
	}

	@Override
	public <T> T selectOne(String sql, Class<T> tClass) {
		return selectOne(sql, null, tClass);
	}

	@Override
	public <T> T selectOne(String sql, final Object[] params, Class<T> tClass) {
		List<T> resultList = select(sql, params, tClass);
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public int insertOrUpdateOrDelete(String sql, final Object[] params) {
		int num = 0;
		if (params == null || params.length == 0)
			num = getJdbcTemplate().update(sql);
		else
			num = getJdbcTemplate().update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					for (int i = 0; i < params.length; i++)
						ps.setObject(i + 1, params[i]);
				}
			});
		return num;
	}
}
