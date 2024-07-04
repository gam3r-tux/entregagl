package com.walmart.test.apps.service;

import java.sql.ResultSet;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;

@Service
public class Db2AnSql{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Db2AnSql.class);
	
	@Autowired
	private Sql sql;

	@Autowired
	@Qualifier("jdbTemplateSql")
	private JdbcTemplate jdbcTemplateSql;

	public void itemToRetail(ResultSet resultDb2) throws Exception{		
		
		SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(jdbcTemplateSql.getDataSource().getConnection());
        SQLServerBulkCopyOptions copyOptions = new SQLServerBulkCopyOptions();
        copyOptions.setBulkCopyTimeout(600);
        bulkCopy.setBulkCopyOptions(copyOptions);
		
		LOGGER.info("+++++++++++++++++++++ PASO 2 ------- INICIO BORRADO ITEM_TO_STORE_ITEM_RETAIL: " + LocalTime.now().toString().substring(0, 8));
		String deleteSqlItem = "DELETE FROM dbo.ITEM_TO_STORE_ITEM_RETAIL_NEW";
		jdbcTemplateSql.update(deleteSqlItem);

		LOGGER.info("+++++++++++++++++++++ PASO 3 ------- INICIO LLENADO ITEM_TO_STORE_ITEM_RETAIL: " + LocalTime.now().toString().substring(0, 8));
		
        bulkCopy.setDestinationTableName("dbo.ITEM_TO_STORE_ITEM_RETAIL_NEW");

        bulkCopy.writeToServer(resultDb2);
	    
	    resultDb2 = null;
	    bulkCopy = null;
	    
		sql.sqlRecovery();

	}
}
