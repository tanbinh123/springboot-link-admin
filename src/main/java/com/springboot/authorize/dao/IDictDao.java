package com.springboot.authorize.dao;

import java.util.List;

import com.springboot.authorize.domain.auth.Dict;
import com.springboot.core.web.mvc.Page;
/**
 * 字典接口
* @ClassName: IDictDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956 
* @date 2021年1月7日 上午9:38:16 
*
 */
public interface IDictDao {
	Page<Dict> selectPage(Dict dict);

	List<Dict> select(Dict dict);

	Dict select(Integer id);

	int insert(Dict dict);

	int update(Dict dict);

	int delete(Dict dict);

}
