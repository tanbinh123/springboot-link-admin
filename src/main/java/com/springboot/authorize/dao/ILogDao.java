package com.springboot.authorize.dao;

import com.springboot.authorize.domain.logs.BLog;
import com.springboot.authorize.domain.logs.BLogVO;
import com.springboot.core.web.mvc.JqGridPage;
/**
 * 日志接口
* @ClassName: ILogDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:37:29 
*
 */
public interface ILogDao {

	JqGridPage<BLog> selectPage(BLogVO  log);

	int insert(BLog log);

}
