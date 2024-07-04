package com.walmart.test.apps.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DB2 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DB2.class);
	
	@Autowired
	@Qualifier("jdbcTemplateDb2")
	private JdbcTemplate jdbcTemplateDB1;
	
	@Autowired
	private Db2AnSql db2AnSql;
	
	@Transactional
	public void test() throws Exception{
		
		LOGGER.info("+++++++++++++++++++++ PASO 1 ----- INICIO LECTURA DB2: " + LocalTime.now().toString().substring(0, 8));
		

//		String findByItems = "select count(*) from R2ITEM.ITEM A, R2PRICMG.STORE_ITEM_RETAIL B "
//							+ "where A.ITEM_NBR = B.ITEM_NBR and B.STORE_NBR = 0 and A.ITEM_NBR in "
//							+ "(select ITEM_NBR FROM R2PRICMG.ITEM_FUTURE_CHANGE where PRICING_EVENT_CD = 4 and CHANGE_EXP_DATE > current date - 4 MONTH)";
//		
//		List<String> cuenta = jdbcTemplateDB1.queryForList(findByItems, String.class);
//		LOGGER.info("******************************* EJECUTE QUERY: " + cuenta.get(0) + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		String findByItemsAll = "SELECT A.ITEM_NBR , A.DEPT_NBR, A.UPC_NBR , A.ITEM1_DESC , A.CUST_BASE_RTL_AMT , B.UNIT_COST_AMT, "
								+ "C.RULE_ID, C.CHANGE_EFF_DATE, C.CHANGE_EXP_DATE, C.ITEM_RETAIL_AMT, C.CUST_RETAIL_AMT "
								+ "FROM R2ITEM.ITEM A, R2PRICMG.STORE_ITEM_RETAIL B, R2PRICMG.ITEM_FUTURE_CHANGE C "
								+ "WHERE C.ITEM_NBR = A.ITEM_NBR AND A.ITEM_NBR = B.ITEM_NBR and B.STORE_NBR = 0 "
								+ "and A.ITEM_NBR in "
								+ "(SELECT ITEM_NBR "
								+ "FROM R2PRICMG.ITEM_FUTURE_CHANGE "
								+ "WHERE  PRICING_EVENT_CD = 4 and CHANGE_EXP_DATE > current date - 4 MONTHS order by ITEM_NBR) "
								+ "ORDER BY A.ITEM_NBR ASC";
		
		Statement stmt = jdbcTemplateDB1.getDataSource().getConnection().createStatement();
		
		ResultSet resultSet = stmt.executeQuery(findByItemsAll);
		
		db2AnSql.itemToRetail(resultSet);
	}
}

