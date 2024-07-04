package com.walmart.test.apps.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.walmart.test.apps.util.Constants;

@Service
public class UploadFileService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileService.class);
	
	@Transactional
	public void tex(String archive) throws UnsupportedEncodingException {

		BlobContainerClient blobContainerClient = createContainerWithConnection();
		uploadFiles(blobContainerClient, archive);
		
	}

	private static BlobContainerClient createContainerWithConnection() throws UnsupportedEncodingException {
		String dateTime = LocalTime.now().toString().substring(0, 8);
		LOGGER.info("    -     Paso 6 ----- creando blobContainerClient {}" ,  dateTime);

		String connectStr = loadPropertyFromFile("/etc/secrets/blobstorage.txt");
		return new BlobContainerClientBuilder().connectionString(connectStr).containerName("test-apps-out")
				.buildClient();
	}


	private static void uploadFiles(BlobContainerClient blobContainerClient, String archive) throws UnsupportedEncodingException {
		
		LOGGER.info("**************************** Paso 6 lista y borra blob");

		for (BlobItem blobItem : blobContainerClient.listBlobs()) {

			if (blobItem.getName().equals(Constants.BLOB)) {

				BlobClient blobClient = blobContainerClient.getBlobClient(Constants.BLOB);

				blobClient.delete();

			}
		}

		LOGGER.info("**************************** Paso 6 inicia carga de archivo al blob");
		
		// Create a local file in the ./data/ directory for uploading and downloading
		String fileName = Constants.BLOB;

		// Get a reference to a blob
		BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

		// Upload the blob
		byte[] bytes = archive.getBytes(StandardCharsets.UTF_8);
		archive = null;
		String cadenaIso88591 = new String(bytes, StandardCharsets.ISO_8859_1);
		try (ByteArrayInputStream stream = new ByteArrayInputStream(cadenaIso88591.getBytes())) {
			blobClient.upload(stream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		
		cadenaIso88591 = null;
		
		
		String dateTime = LocalTime.now().toString().substring(0, 8);
		
		LOGGER.info("**************************** Paso 6 terminó carga de archivo blob", dateTime);

	}

	private static String loadPropertyFromFile(String path) throws UnsupportedEncodingException {
        String value = StringUtils.EMPTY;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                path))) {
            value  = value.concat(bufferedReader.readLine());
        } catch (IOException e) {
        	LOGGER.info("CAMS - Error occurred while reading vault file: {}",  path);
            LOGGER.error(e.getMessage());
            throw new UnsupportedEncodingException("CAMS - Error occurred while reading vault file " + path);
        }
        return value;
    }

}