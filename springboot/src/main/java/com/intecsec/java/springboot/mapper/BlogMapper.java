package com.intecsec.java.springboot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.intecsec.java.springboot.entity.Blog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
public interface BlogMapper extends BaseMapper<Blog> {

	@Select("selectByPageList")
	List<Blog> selectByPageList(Pagination page, String state);
}
