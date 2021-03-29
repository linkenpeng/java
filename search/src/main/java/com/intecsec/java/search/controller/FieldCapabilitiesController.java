package com.intecsec.java.search.controller;

import com.google.common.base.Splitter;
import com.intecsec.java.search.service.FieldCapabilitiesService;
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
@RequestMapping("/es/fieldsearch")
public class FieldCapabilitiesController {

	@Autowired
	private FieldCapabilitiesService fieldCapabilitiesService;

	@GetMapping("/sr")
	public String executeSearchRequest(@RequestParam("field") String field, @RequestParam("indices") String indices) {
		if(Strings.isNullOrEmpty(indices)) {
			return "Parameters are wrong.";
		}
		List<String> indicesList = Splitter.on(",").splitToList(indices);
		fieldCapabilitiesService.doSearch(field, indicesList);
		return "Execute FieldCapabilitiesSearch Success.";
	}
}
