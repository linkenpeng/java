package com.intecsec.java.search.service;

import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/3/29
 */
public interface FieldCapabilitiesService {

	void doSearch(String field, List<String> indices);
}
