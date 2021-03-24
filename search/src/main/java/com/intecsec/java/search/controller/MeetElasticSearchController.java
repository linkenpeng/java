package com.intecsec.java.search.controller;

import com.intecsec.java.search.service.MeetElasticSearchService;
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
@RequestMapping("/springboot/es")
public class MeetElasticSearchController {

	@Autowired
	private MeetElasticSearchService meetElasticSearchService;

	@GetMapping("/add")
	public String add() {
		meetElasticSearchService.add();
		return "single add success.";
	}

	@GetMapping("/addBatch")
	public String addBatch() {
		meetElasticSearchService.addBatch();
		return "batch add success.";
	}

	@GetMapping("/get/{id}")
	public String get(@PathVariable("id") String id) {
		return meetElasticSearchService.get(id);
	}

	@GetMapping("/search")
	public Object search(@RequestParam("q") String q) {
		return meetElasticSearchService.search(q);
	}
}
