package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.Job;
import com.springboot.bcode.service.IJobService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 岗位接口
 * 
 * @ClassName: JobRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年11月21日 上午11:21:51
 *
 */
@RestController
@RequestMapping(value = "/rest/job")
public class JobRest extends BaseRest {
	@Autowired
	private IJobService jobService;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Job job) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(jobService.queryPage(job));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	
	@RequestMapping(value = "all")
	public ResponseResult all() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(jobService.queryAll());
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}
	
	@OpertionBLog(title = "添加岗位")
	@Requestauthorize("job:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Job job) {
		ResponseResult rep = new ResponseResult();
		try {
			jobService.add(job);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改岗位")
	@Requestauthorize("job:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Job job) {
		ResponseResult rep = new ResponseResult();
		try {
			jobService.update(job);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	
	@OpertionBLog(title = "删除岗位")
	@Requestauthorize("job:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			jobService.delete(id);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}
	
	@OpertionBLog(title = "更新岗位状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody Job job) {
		ResponseResult rep = new ResponseResult();
		try {
			jobService.updateState(job);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}
}
