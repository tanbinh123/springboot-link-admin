package com.springboot.authorize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.authorize.domain.auth.Dict;
import com.springboot.authorize.service.IDictService;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.permission.CheckPermission;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 数据字典
 * 
 * @ClassName: DictRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2019年10月23日 下午2:42:26
 *
 */
@RestController
@RequestMapping(value = "/rest/dict")
public class DictRest extends BaseRest {

	@Autowired
	private IDictService dictService;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Dict dict) {
		return ResponseResult.success(dictService.queryPage(dict));

	}

	@RequestMapping(value = "info")
	public ResponseResult info(@RequestParam("type") String type) {
		return ResponseResult.success(dictService.queryByType(type));

	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@OpertionBLog(title = "新增字典")
	@CheckPermission("dict:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Dict dict) {
		dictService.add(dict);
		return ResponseResult.success();
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@OpertionBLog(title = "修改字典")
	@CheckPermission("dict:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Dict dict) {
		dictService.update(dict);
		return ResponseResult.success();
	}

	/**
	 * 移除角色
	 * 
	 * @param id
	 * @return
	 */
	@OpertionBLog(title = "删除字典")
	@CheckPermission("dict:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		dictService.delete(id);
		return ResponseResult.success();
	}

}
