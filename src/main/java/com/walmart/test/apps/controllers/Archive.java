package com.walmart.test.apps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.test.apps.service.DB2;
import com.walmart.test.apps.service.Sql;
import com.walmart.test.apps.service.UploadFileService;
import com.walmart.test.apps.service.BlobStorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("archive")
public class Archive {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Archive.class);
	
	@Autowired
	private UploadFileService uploadFileService;
		
	@Autowired
	private DB2 db2;
	
	@Autowired
	private Sql sql;
	
	@Autowired
	BlobStorageService blobStorageService;
	
	@GetMapping(value="/articulos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDb2() throws Exception{
		LOGGER.info("-------------------- REQUEST /articulos -----------------");	
		db2.test();
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
//	@GetMapping(value="/sql", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> getSql() throws Exception{
//		
//		LOGGER.info("-------------------- REQUEST Deshabilitado /sql -----------------");	
////		sql.testSql();
//		return new ResponseEntity<String>("ok", HttpStatus.OK);
//	}
	
	@GetMapping(value = "/blobTest", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBlobTest(){

		LOGGER.info("****************** End point /blobTest GET called ***************************");

		blobStorageService.saveTestFile();

		return new ResponseEntity<>("Blob Test!!!", HttpStatus.OK);
	}
	
}