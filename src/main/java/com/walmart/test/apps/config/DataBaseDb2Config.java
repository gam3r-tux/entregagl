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
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.walmart.test.apps.vo.AzureBSCreds;

@Configuration
public class DataBaseDb2Config {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseDb2Config.class);

	@Primary
	@Bean
	public DataSource dataSourcesList(AzureBSCreds secretsBean) {

		LOGGER.info("******************* ConfigDB1DataSources ++++DB2++++ ********************");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		dataSource.setUrl("jdbc:db2://DSNTDRDA.wal-mart.com:446/DSNT");
		dataSource.setUsername(secretsBean.getUserdb2());
		dataSource.setPassword(secretsBean.getDb2String());

		return dataSource;

	}

	@Primary
	@Bean
	@DependsOn({"dataSourcesList"})
	@Qualifier("jdbcTemplateDb2")
	public JdbcTemplate jdbcTemplate1(@Qualifier("dataSourcesList") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
