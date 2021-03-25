package com.intecsec.java.search.controller;

import com.intecsec.java.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Peter.Peng
 * @date 2021/3/24
 */
@RestController
@RequestMapping("/es")
public class ElasticSearchController {

	@Autowired
	private ElasticSearchService elasticSearchService;

	@GetMapping("/add")
	public String add() {
		elasticSearchService.add();
		return "single add success.";
	}

	@GetMapping("/addBatch")
	public String addBatch() {
		elasticSearchService.addBatch();
		return "batch add success.";
	}

	@GetMapping("/get/{id}")
	public String get(@PathVariable("id") String id) {
		return elasticSearchService.get(id);
	}

	@GetMapping("/search")
	public Object search(@RequestParam("q") String q) {
		return elasticSearchService.search(q);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		elasticSearchService.delete(id);
		return "delete success.";
	}

	@GetMapping("/parseEsResponse")
	public String search() {
		elasticSearchService.parseSearchResponse();
		return "Parse ElasticSearch Response Is Over.";
	}

	@GetMapping("/createIndex")
	public String createIndex() {
		// elasticSearchService.buildIndexRequestWithString("posts", "5");
		elasticSearchService.buildIndexRequestWithMap("posts", "6");
		// elasticSearchService.buildIndexRequestWithXContentBuilder("posts", "7");
		elasticSearchService.buildIndexRequestWithKV("posts", "8");
		return "Create Index Success.";
	}
}
