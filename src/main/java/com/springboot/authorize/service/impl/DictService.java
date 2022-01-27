package com.springboot.authorize.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.authorize.dao.IDictDao;
import com.springboot.authorize.domain.auth.Dict;
import com.springboot.authorize.service.IDictService;
import com.springboot.common.utils.BeanUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.exception.SystemException;
import com.springboot.core.web.mvc.Page;

/**
 * 字典业务层实现类
 * 
 * @ClassName: DictService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:53:47
 *
 */
@Service
public class DictService implements IDictService {

	@Autowired
	private IDictDao dictDao;

	@Override
	public Page<Dict> queryPage(Dict dict) {
		return dictDao.selectPage(dict);
	}

	@Override
	public List<Dict> queryByType(String type) {
		if (StringUtils.isBlank(type)) {
			return null;
		}
		Dict dict = new Dict();
		dict.setData_type(type);
		return dictDao.select(dict);
	}

	@Override
	public boolean add(Dict dict) {
		if (dict == null) {
			throw new SystemException("保存数据不能为空");
		}
		if (StringUtils.isBlank(dict.getData_type())) {
			throw new SystemException("Type不能为空");
		}
		if (StringUtils.isBlank(dict.getData_key())) {
			throw new SystemException("Key不能为空");
		}
		if (StringUtils.isBlank(dict.getData_value())) {
			throw new SystemException("Value不能为空");
		}
		int result = dictDao.insert(dict);
		if (result <= 0) {
			throw new SystemException("执行失败");
		}
		return true;
	}

	@Override
	public boolean update(Dict dict) {
		if (dict == null) {
			throw new SystemException("保存数据不能为空");
		}
		if (dict.getId() == null) {
			throw new SystemException("保存数据不能为空");
		}
		if (StringUtils.isBlank(dict.getData_type())) {
			throw new SystemException("Type不能为空");
		}
		if (StringUtils.isBlank(dict.getData_key())) {
			throw new SystemException("Key不能为空");
		}
		if (StringUtils.isBlank(dict.getData_value())) {
			throw new SystemException("Value不能为空");
		}

		Dict dictInfo = dictDao.select(dict.getId());
		if (dictInfo == null) {
			throw new SystemException("未查询到字典信息");
		}
		BeanUtils.copyObject(dictInfo, dict);
		int result = dictDao.update(dictInfo);
		if (result < 0) {
			throw new SystemException("执行失败");
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) throws SystemException {
		if (id == null) {
			throw new SystemException("删除条件不能为空");
		}
		Dict dict = new Dict();
		dict.setId(id);
		// 删除角色本身
		int result = dictDao.delete(dict);
		if (result < 0) {
			throw new SystemException("执行失败");
		}

		return true;
	}

}
