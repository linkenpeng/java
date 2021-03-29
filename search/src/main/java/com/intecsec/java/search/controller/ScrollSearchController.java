package com.intecsec.java.search.controller;

import com.intecsec.java.search.service.ScrollSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@RestController
@RequestMapping("/es/scrollsearch")
public class ScrollSearchController {

	@Autowired
	private ScrollSearchService scrollSearchService;

	@GetMapping("/sr")
	public String executeSearchRequest(@RequestParam("keyword") String keyword) {
		scrollSearchService.scrollSearchRequest("posts", keyword);
		return "Execute SearchScrollRequest Success.";
	}

}
