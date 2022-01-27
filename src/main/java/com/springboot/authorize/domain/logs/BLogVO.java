package com.springboot.authorize.domain.logs;

import com.springboot.core.web.mvc.PageParam;

public class BLogVO extends PageParam {
	private String[] dateRange;// 日期范围
	private String starttime;
	private String endtime;
	private String loginuser;
	private Integer state;

	public String[] getDateRange() {
		return dateRange;
	}

	public void setDateRange(String[] dateRange) {
		this.dateRange = dateRange;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
