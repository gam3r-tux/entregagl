package com.walmart.test.apps.service.impl;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import com.walmart.test.apps.service.BlobStorageService;
import com.walmart.test.apps.service.Db2AnSql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import com.walmart.test.apps.vo.AzureBSCreds;

@Service
public class BlobStorageServiceImpl implements BlobStorageService {
	

    private static final Logger LOGGER = LoggerFactory.getLogger(BlobStorageServiceImpl.class);
    @Override
    public void saveTestFile() {
        BlobContainerClient blobContainerClient = createContainerWithConnection();
        uploadStreamFiles(blobContainerClient);
        getListItemsInContainer(blobContainerClient);
    }
    
	private static BlobContainerClient createContainerWithConnection() {
        String connectStr = loadPropertyFromFile("/etc/secrets/blobstorage.txt");
        return new BlobContainerClientBuilder()
                .connectionString(connectStr)
                .containerName("markupdown-out")
                .buildClient();
    }

    private static void uploadStreamFiles(BlobContainerClient blobContainerClient){
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
        String formatted = timeStamp.format(formatter);
    	String fileName = "testBlob" + formatted;
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        String data = createDummyData();
        try(ByteArrayInputStream stream = new ByteArrayInputStream(data.getBytes())){
            blobClient.upload(stream, data.length());
        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }
    }

    private static void getListItemsInContainer(BlobContainerClient blobContainerClient) {
    	LOGGER.info("\nListing blobs...");
        Iterable<BlobItem> blobItems = blobContainerClient.listBlobs();
        LOGGER.info("\nDone getting blobs...");

    }

    private static String createDummyData(){
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 20; i++){
            builder.append(i).append(",").append(i+1).append("\n");
        }

        return builder.toString();
    }

    private static String loadPropertyFromFile(String path) {
        String value = StringUtils.EMPTY;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                path))) {
            value  = value.concat(bufferedReader.readLine());
        } catch (IOException e) {
        	LOGGER.info("CAMS - Error occurred while reading vault file: {}"+ path);
            LOGGER.error(e.getMessage());
            throw new RuntimeException("CAMS - Error occurred while reading vault file "+path, e);
        }
        return value;
    }
}