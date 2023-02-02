package com.intecsec.java.springboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.intecsec.java.springboot.entity.Blog;
import com.intecsec.java.springboot.service.BlogService;
import com.intecsec.java.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@RestController()
@RequestMapping("/blog")
@Slf4j
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/{id}")
	public Blog getById(@PathVariable("id") Integer id, @RequestHeader HttpHeaders headers) {
		log.info("request.getHeader header:{}", JsonUtils.toJson(request.getHeader("MyStoreTid")));
		log.info("headers.getFirst tid:{}", JsonUtils.toJson(headers.getFirst("MyStoreTid")));
		return  blogService.getById(id);
	}

	@RequestMapping("/page")
	public Object selectPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							 @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize
							 ) {
		Page p = new Page(page, pageSize);
		return blogService.blogPage(p, "1");
	}

	@RequestMapping("/pg")
	public Object pg(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							 @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize) {
		return blogService.pageList((page - 1) * pageSize, pageSize);
	}
}
