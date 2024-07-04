package com.walmart.test.apps.service;

import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.walmart.test.apps.modals.Db2.ItemStoreItemRetail;


@Service
public class Sql {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Sql.class);
	
	@Autowired
	private Proceso1 proceso1;

	@Autowired
	@Qualifier("jdbTemplateSql")
	private JdbcTemplate jdbcTemplateSql;

	public void sqlRecovery() throws Exception {
		
		LOGGER.info("******************** PASO 4 inicia de recuperacion Sql " + LocalTime.now().toString().substring(0, 8));

		String findByItems = "SELECT ITEM_NBR, DEPT_NBR, UPC_NBR, ITEM1_DESC, CUST_BASE_RTL_AMT, UNIT_COST_AMT, RULE_ID, CHANGE_EFF_DATE, CHANGE_EXP_DATE, "
							+ "ITEM_RETAIL_AMT, CUST_RETAIL_AMT "
							+ "FROM dbo.ITEM_TO_STORE_ITEM_RETAIL_NEW "
							+ "ORDER BY ITEM_NBR ASC";
		
		List<ItemStoreItemRetail> items = jdbcTemplateSql.query(findByItems, new BeanPropertyRowMapper<ItemStoreItemRetail>(ItemStoreItemRetail.class));
		
		proceso1.call(items);
	}
}
