package com.supra.config;

import org.springframework.context.annotation.Bean;

import com.supra.appconfig.AppConfig;
import com.supra.task.TvAsyncTask;

public class SupraConfig extends AppConfig {

	@Bean
	public TvAsyncTask tvAasyncTask() {
		return new TvAsyncTask();
	}

}
