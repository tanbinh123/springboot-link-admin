package com.springboot.bcode.dao;


import com.springboot.bcode.domain.video.Video;
import com.springboot.core.web.mvc.Page;

public interface IVideoDao {
	Page<Video> selectPage(Video video);
	
	Video select(Video video);

	int insert(Video video);

	int delete(Video video);

	

}
