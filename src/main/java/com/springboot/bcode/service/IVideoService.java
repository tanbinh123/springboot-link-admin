package com.springboot.bcode.service;


import org.springframework.web.multipart.MultipartFile;

import com.springboot.bcode.domain.video.Video;
import com.springboot.core.web.mvc.Page;

public interface IVideoService {

	Page<Video> queryPage(Video video);

    void upload(MultipartFile [] multipartFile);
    
    void view(String path);

	boolean delete(int id);


}
