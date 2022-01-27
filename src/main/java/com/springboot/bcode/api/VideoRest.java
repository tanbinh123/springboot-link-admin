package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.bcode.domain.video.Video;
import com.springboot.bcode.service.IVideoService;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 具体业务代码...........................................
 * 
 * @ClassName: VideoRest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 252956
 * @date 2021年1月7日 上午10:16:28
 *
 */
@RestController
@RequestMapping(value = "/rest/video")
public class VideoRest extends BaseRest {

	@Autowired
	private IVideoService videoService;

	@RequestMapping(value = "/tiktok/list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Video vo) {
		return ResponseResult.success(videoService.queryPage(vo));

	}

	@RequestMapping(value = "/tiktok/upload", method = RequestMethod.POST)
	public ResponseResult upload(
			@RequestParam("file") MultipartFile[] multipartFile) {
		videoService.upload(multipartFile);
		return ResponseResult.success();
	}

	@RequestMapping(value = "/tiktok/view")
	public void video(@RequestParam("path") String path) {
		videoService.view(path);
	}

	@RequestMapping(value = "/tiktok/delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		videoService.delete(id);
		return ResponseResult.success();
	}

}
