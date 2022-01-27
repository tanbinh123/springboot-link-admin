package com.springboot.authorize.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
import com.springboot.core.web.mvc.PageParam;

/**
 * 数据字典表
 * 
 * @ClassName: Dict
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月23日 下午2:39:35
 *
 */
@Tables(table = "t_sys_dict")
public class Dict extends PageParam {

	@Columns(column = "id", primaryKey = true)
	private Integer id; // 字典id

	@Columns(column = "data_type")
	private String data_type;// 字典类型

	@Columns(column = "data_key")
	private String data_key;// 字典key

	@Columns(column = "data_value")
	private String data_value;// 字典值

	@Columns(column = "sorts")
	private Integer sorts;// 排序

	@Columns(column = "description")
	private String description;// 描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getData_key() {
		return data_key;
	}

	public void setData_key(String data_key) {
		this.data_key = data_key;
	}

	public String getData_value() {
		return data_value;
	}

	public void setData_value(String data_value) {
		this.data_value = data_value;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
