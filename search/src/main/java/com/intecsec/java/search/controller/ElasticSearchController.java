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

	private static String  indexName = "posts";

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

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") String id) {
		elasticSearchService.update(id);
		return "update success.";
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
		elasticSearchService.buildIndexRequestWithString(indexName, "5");
		elasticSearchService.buildIndexRequestWithMap(indexName, "6");
		elasticSearchService.buildIndexRequestWithXContentBuilder(indexName, "7");
		elasticSearchService.buildIndexRequestWithKV(indexName, "8");
		return "Create Index Success.";
	}

	@GetMapping("/term")
	public String term() {
		elasticSearchService.term(indexName, "1", "username");
		return "Create Index Success.";
	}
}
