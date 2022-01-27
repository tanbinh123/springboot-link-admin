package com.springboot.core.jdbc.sql;

import java.util.List;

/**
 * 
 * @ClassName: SqlObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2022年1月20日 下午4:09:20
 *
 */
public class SqlObject {

	private String sql;
	private List<Object> params;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

}
