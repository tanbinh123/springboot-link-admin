package com.springboot.authorize.service;

import java.util.List;

import com.springboot.authorize.domain.auth.Dict;
import com.springboot.core.web.mvc.Page;

/**
 * 字典业务层接口
 * 
 * @ClassName: IDictService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午9:47:15
 *
 */
public interface IDictService {

	Page<Dict> queryPage(Dict dict);

	List<Dict> queryByType(String type);

	boolean add(Dict dict);

	boolean update(Dict dict);

	boolean delete(Integer id);

}
