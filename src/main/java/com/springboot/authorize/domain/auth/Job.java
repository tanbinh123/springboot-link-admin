package com.springboot.authorize.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
import com.springboot.core.web.mvc.PageParam;

/**
 * 岗位表
 * 
 * @ClassName: Job
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年11月21日 上午11:18:46
 *
 */
@Tables(table = "t_sys_job")
public class Job extends PageParam {

	// Fields
	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "name")
	private String name;
	@Columns(column = "state")
	private Integer state;
	@Columns(column = "sorts")
	private Integer sorts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

 
}