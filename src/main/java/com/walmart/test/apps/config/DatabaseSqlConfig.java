/**
 * 
 */
package com.walmart.test.apps.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.walmart.test.apps.vo.AzureBSCreds;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseSqlConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSqlConfig.class);

	@Primary
	@Bean
	public DataSource sqlDbDataSource(AzureBSCreds secretsBean) {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		hikariConfig.setJdbcUrl("jdbc:sqlserver://apps-mf2c-nonprod-650065bb.database.windows.net;databaseName=app_mf2c_db_dev;encrypt=false;trustServerCertificate=true");
		hikariConfig.setUsername("" + secretsBean.getUserdb());
		hikariConfig.setPassword(secretsBean.getDbString());
		hikariConfig.addDataSourceProperty("authentication", "ActiveDirectoryPassword");
//		hikariConfig.setMaximumPoolSize(12);
//		hikariConfig.setConnectionTimeout(40 * 60 * 1000);
//		hikariConfig.setMaxLifetime(40 * 60 * 1000);
//		hikariConfig.addDataSourceProperty("socketTimeout", 40 * 60 * 1000);
//		hikariConfig.addDataSourceProperty("connectTimeout", 40 * 60 * 1000);
		hikariConfig.addDataSourceProperty("rewriteBatchedStatements", true);
		hikariConfig.addDataSourceProperty("useConfigs", "maxPerformance");
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		hikariConfig.addDataSourceProperty("hibernate.order_inserts", true);
		hikariConfig.addDataSourceProperty("trustServerCertificate", "true");
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setPoolName("testAppsConnectionPool");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	@Primary
	@Bean
	@DependsOn({"sqlDbDataSource"})
	@Qualifier("jdbcTemplateSQLHikari")
	public JdbcTemplate jdbcTemplateSQLHikari(@Qualifier("sqlDbDataSource") DataSource dataSource) {
		JdbcTemplate templateAzure = new JdbcTemplate();
		templateAzure.setDataSource(dataSource);
		return templateAzure;
	}
}
