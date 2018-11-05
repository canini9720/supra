package com.supra.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TvAsyncTask {

	@Async
	public void doTvAsyncTask() throws InterruptedException {
		Thread.sleep(15000l);
		System.out.println("do TV async task running");
	}

}
