package com.walmart.test.apps.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.walmart.test.apps.vo.AzureBSCreds;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
public class ConfigSQLDataSources {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSQLDataSources.class);

	@Primary
	@Bean
	public DataSource dataSourcesList2(AzureBSCreds secretsBean) {

        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("apps-mf2c-nonprod-650065bb.database.windows.net"); 
        dataSource.setDatabaseName("app_mf2c_db_dev"); 
        dataSource.setUser("" + secretsBean.getUserdb()); 
        dataSource.setPassword(secretsBean.getDbString());
        dataSource.setAuthentication("ActiveDirectoryPassword");
        dataSource.setPortNumber(1433);
        dataSource.setEncrypt(false);
        dataSource.setTrustServerCertificate(true);

		return dataSource;

	}

	@Primary
	@Bean
	@DependsOn({"dataSourcesList2"})
	@Qualifier("jdbTemplateSql")
	public JdbcTemplate jdbTemplateSql(@Qualifier("dataSourcesList2") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}