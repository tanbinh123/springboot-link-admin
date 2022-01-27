package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.Job;
import com.springboot.authorize.service.IJobService;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
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
		return ResponseResult.success(jobService.queryPage(job));

	}

	@RequestMapping(value = "all")
	public ResponseResult all() {
		return ResponseResult.success(jobService.queryAll());

	}

	@OpertionBLog(title = "添加岗位")
	@CheckPermission("job:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Job job) {
		jobService.add(job);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "修改岗位")
	@CheckPermission("job:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Job job) {
		jobService.update(job);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "删除岗位")
	@CheckPermission("job:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		jobService.delete(id);
		return ResponseResult.success();
	}

	@OpertionBLog(title = "更新岗位状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody Job job) {
		jobService.updateState(job);
		return ResponseResult.success();

	}
}
