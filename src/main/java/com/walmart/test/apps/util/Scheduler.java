package com.walmart.test.apps.util;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.walmart.test.apps.service.BlobStorageService;

@Component
@EnableScheduling
public class Scheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

	@Autowired 
	private BlobStorageService BlobStorageService;

	@Scheduled(cron = "0 25 23 ? * *")
	public void sch() {
		LOGGER.info("Inicia llamada proceso:::::: - " + LocalTime.now().toString().substring(0, 8));
		BlobStorageService.saveTestFile();
		LOGGER.info("Finaliza llamada proceso++++++ - " + LocalTime.now().toString().substring(0, 8));		
		}
}
