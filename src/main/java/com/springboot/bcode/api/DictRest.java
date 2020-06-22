package com.springboot.bcode.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.Dict;
import com.springboot.bcode.service.IDictService;
import com.springboot.common.exception.SystemException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
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
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(dictService.queryPage(dict));
		} catch (SystemException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	@RequestMapping(value = "info")
	public ResponseResult info(@RequestParam("type") String type) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(dictService.queryByType(type));
		} catch (SystemException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@OpertionBLog(title = "新增字典")
	@Requestauthorize("dict:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Dict dict) {
		ResponseResult rep = new ResponseResult();
		try {
			dictService.add(dict);
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}

		return rep;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@OpertionBLog(title = "修改字典")
	@Requestauthorize("dict:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Dict dict) {
		ResponseResult rep = new ResponseResult();
		try {
			dictService.update(dict);
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 移除角色
	 * 
	 * @param id
	 * @return
	 */
	@OpertionBLog(title = "删除字典")
	@Requestauthorize("dict:del")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			dictService.delete(id);
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

}
