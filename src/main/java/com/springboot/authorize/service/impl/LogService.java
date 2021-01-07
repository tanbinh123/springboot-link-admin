package com.springboot.authorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.authorize.dao.ILogDao;
import com.springboot.authorize.domain.logs.BLog;
import com.springboot.authorize.domain.logs.BLogVO;
import com.springboot.authorize.service.ILogService;
import com.springboot.common.exception.AuthException;
import com.springboot.common.utils.DateUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.web.mvc.JqGridPage;

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
	public JqGridPage<BLog> queryPage(BLogVO vo) {
		if (vo == null) {
			throw new AuthException("参数不能为空");
		}
		if (StringUtils.isBlank(vo.getStarttime())
				|| StringUtils.isBlank(vo.getEndtime())) {
			throw new AuthException("请选择时间范围");
		}
		if (DateUtils.differentDays(
				DateUtils.formatDateTime(vo.getStarttime()),
				DateUtils.formatDateTime(vo.getEndtime())) > 1
				|| DateUtils.differentDays(
						DateUtils.formatDateTime(vo.getStarttime()),
						DateUtils.formatDateTime(vo.getEndtime())) < 0) {
			throw new AuthException("时间范围不能大于一天");
		}
		return logDao.selectPage(vo);
	}

}
