package com.walmart.test.apps.vo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
@Data
@ToString
public class AzureBSCreds {

	public static final String SECRET_ROOT_PATH = "/etc/secrets/";

	private String connectionString;

	private String userdb2;

	private String db2String;

	private String userdb;

	private String dbString;

	private static final Logger LOGGER = LoggerFactory.getLogger(AzureBSCreds.class);

	@PostConstruct
	private void loadVaultConfigs() {
		this.connectionString = loadPropertyFromFile(SECRET_ROOT_PATH + "blobstorage.txt");
		
		this.userdb2 = loadPropertyFromFile(SECRET_ROOT_PATH + "azure-db2-user-creds.txt");
		this.db2String = loadPropertyFromFile(SECRET_ROOT_PATH + "azure-db2-creds.txt");
		
		this.userdb = loadPropertyFromFile(SECRET_ROOT_PATH + "sql-db-user-creds.txt");
		this.dbString = loadPropertyFromFile(SECRET_ROOT_PATH + "sql-db-creds.txt");
	}

	public AzureBSCreds() {

	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getUserdb2() {
		return userdb2;
	}

	public void setUserdb2(String userdb2) {
		this.userdb2 = userdb2;
	}

	public String getDb2String() {
		return db2String;
	}

	public void setDb2String(String db2String) {
		this.db2String = db2String;
	}

	public String getUserdb() {
		return userdb;
	}

	public void setUserdb(String userdb) {
		this.userdb = userdb;
	}

	public String getDbString() {
		return dbString;
	}

	public void setDbString(String dbString) {
		this.dbString = dbString;
	}

	public String loadPropertyFromFile(String path) {
		 String value = StringUtils.EMPTY;
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
              path))) {
          value  = value.concat(bufferedReader.readLine());
      } catch (IOException e) {
          System.out.println("CAMS - Error occurred while reading vault file: {}"+ path);
          LOGGER.error(e.getMessage());
          throw new RuntimeException("CAMS - Error occurred while reading vault file "+path, e);
      }
      return value;
	}

}
