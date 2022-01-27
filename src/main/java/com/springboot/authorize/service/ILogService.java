package com.springboot.authorize.service;

import com.springboot.authorize.domain.logs.BLog;
import com.springboot.authorize.domain.logs.BLogVO;
import com.springboot.core.web.mvc.Page;

/**
 * 日志业务层接口
* @ClassName: ILogService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:46:33 
*
 */

public interface ILogService {

	Page<BLog> queryPage(BLogVO vo);
}
