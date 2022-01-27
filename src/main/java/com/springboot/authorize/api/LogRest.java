package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.logs.BLogVO;
import com.springboot.authorize.service.ILogService;
import com.springboot.core.security.permission.CheckPermission;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 日志接口
 * 
 * @ClassName: LogRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月21日 下午4:56:10
 *
 */
@RestController
@RequestMapping(value = "/rest/logs")
public class LogRest extends BaseRest {
	@Autowired
	private ILogService logService;

	@CheckPermission("blog:list")
	@RequestMapping(value = "/blog/list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody BLogVO vo) {
		return ResponseResult.success(logService.queryPage(vo));
	}
}
