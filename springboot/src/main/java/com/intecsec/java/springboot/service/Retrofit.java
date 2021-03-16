package com.intecsec.java.springboot.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Peter.Peng
 * @date 2021/3/16
 */
@RetrofitClient(baseUrl = "${test.url}")
public interface Retrofit {

	@GET("person")
	Object getPerson(@Query("id") Long id);
}
