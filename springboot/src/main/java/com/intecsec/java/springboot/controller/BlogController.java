package com.intecsec.java.springboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.intecsec.java.springboot.entity.Blog;
import com.intecsec.java.springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@RestController()
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping("/{id}")
	public Blog getById(@PathVariable("id") Integer id) {
		return  blogService.getById(id);
	}

	@RequestMapping("/page")
	public Object selectPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							 @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize) {
		Page p = new Page(page, pageSize);
		return blogService.blogPage(p, "1");
	}

	@RequestMapping("/pg")
	public Object pg(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
							 @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize) {
		return blogService.pageList((page - 1) * pageSize, pageSize);
	}
}
