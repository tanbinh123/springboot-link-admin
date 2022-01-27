package com.springboot.authorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.authorize.dao.ILogDao;
import com.springboot.authorize.domain.logs.BLog;
import com.springboot.authorize.domain.logs.BLogVO;
import com.springboot.authorize.service.ILogService;
import com.springboot.common.utils.DateUtils;
import com.springboot.core.exception.AuthException;
import com.springboot.core.web.mvc.Page;

/**
 * 日志业务层实现类
 * 
 * @ClassName: LogService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:52:38
 *
 */
@Service
public class LogService implements ILogService {
	@Autowired
	private ILogDao logDao;

	@Override
	public Page<BLog> queryPage(BLogVO vo) {
		if (vo == null) {
			throw new AuthException("参数不能为空");
		}
		if (vo.getDateRange() == null || vo.getDateRange().length < 2) {
			vo.setStarttime(DateUtils.getDate());
			vo.setEndtime(DateUtils.getDate());
		} else {
			vo.setStarttime(vo.getDateRange()[0]);
			vo.setEndtime(vo.getDateRange()[1]);
		}
		if (DateUtils.differentDays(DateUtils.formatDate(vo.getStarttime()),
				DateUtils.formatDate(vo.getEndtime())) > 1
				|| DateUtils.differentDays(
						DateUtils.formatDate(vo.getStarttime()),
						DateUtils.formatDate(vo.getEndtime())) < 0) {
			throw new AuthException("时间范围不能大于一天");
		}
		return logDao.selectPage(vo);
	}

}
