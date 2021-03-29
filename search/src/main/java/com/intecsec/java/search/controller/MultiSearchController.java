package com.intecsec.java.search.controller;

import com.google.common.base.Splitter;
import com.intecsec.java.search.service.MultiSearchService;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
@RestController
@RequestMapping("/es/multisearch")
public class MultiSearchController {

	@Autowired
	private MultiSearchService multiSearchService;

	@GetMapping("/sr")
	public String executeSearchRequest(@RequestParam("keyword") String keyword) {
		if(Strings.isNullOrEmpty(keyword)) {
			return "Parameters are wrong.";
		}
		List<String> keyWordList = Splitter.on(",").splitToList(keyword);
		multiSearchService.multiSearch(keyWordList);
		return "Execute SearchScrollRequest Success.";
	}
}
