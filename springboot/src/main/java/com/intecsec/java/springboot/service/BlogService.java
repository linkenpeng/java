package com.intecsec.java.springboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.intecsec.java.springboot.entity.Blog;
import com.intecsec.java.springboot.mapper.BlogMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@Service
public class BlogService {

	@Autowired
	private BlogMapper blogMapper;

	public Blog getById(long id) {
		return blogMapper.selectById(id);
	}

	public Page<Blog> blogPage(Page<Blog> page, String state) {
		return page.setRecords(blogMapper.selectByPageList(page, state));
	}

	public List<Blog> pageList(int page, int pageSize) {
		RowBounds rowBounds = new RowBounds(page, pageSize);
		return blogMapper.selectPage(rowBounds, null);
	}
}
