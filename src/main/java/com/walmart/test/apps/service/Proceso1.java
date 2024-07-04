package com.walmart.test.apps.service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.test.apps.modals.Db2.ItemStoreItemRetail;
import com.walmart.test.apps.modals.Db2.N01;
import com.walmart.test.apps.util.Constants;

@Service
public class Proceso1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Proceso1.class);

	@Autowired
	private Archive2Service archive2Service;

	public void call(List<ItemStoreItemRetail> itemStoreList) throws Exception {

		String res;

		Integer count = itemStoreList.size();

		List<N01> list = new ArrayList<>();
		
		LOGGER.info("******************** PASO 5 inicia call() " + LocalTime.now().toString().substring(0, 8));
		
		for (ItemStoreItemRetail itemStore : itemStoreList) {
			if (itemStore.getItemNbr() > 0.05) {
				String[] partsItem = itemStore.getItemRetailAmt().split("\\.");
				String partItem1 = partsItem[0];
				String partItem2 = partsItem[1];
				if (partItem2.length() > 2) {
					itemStore.setItemRetailAmt(partItem1 + "." + partItem2.substring(0, 2));
				}
				String[] partsUnit = itemStore.getUnitCostAmt().split("\\.");
				String partUnit1 = partsUnit[0];
				String partUnit2 = partsUnit[1];
				if (partUnit2.length() > 2) {
					String Unit = partUnit1 + "." + partUnit2.substring(0, 2);
					itemStore.setUnitCostAmt(Unit);
				}
				if (itemStore.getItemRetailAmt().equals("0.01") && itemStore.getCustRetailAmt().equals("0.01")) {
					res = Constants.NUMBER;
				} else {
					Double firstRes = Double.parseDouble(new DecimalFormat(Constants.FORMAT).format(
							(Double.valueOf(itemStore.getItemRetailAmt()) - Double.valueOf(itemStore.getUnitCostAmt()))));
					Double rest = (firstRes / Double.valueOf(itemStore.getItemRetailAmt())) * 100;
					res = rest.toString();
					res = proceso1(firstRes, res, itemStore.getItemRetailAmt());				
					res = proceso3(rest, res);
				}
				N01 n01 = proceso7(itemStore, res);
				list.add(n01);
			}
		}
		itemStoreList = null;
		archive2Service.createTxt1(list, count);
	}
	
	private String proceso1(Double firstRes, String res, String itemRetailAmt) {
		
		LOGGER.info("******************** PASO 5 inicia proceso1() " + LocalTime.now().toString().substring(0, 8));
		
		String[] resnum = res.split("\\.");
		String resnum2 = resnum[1];
		
		if (resnum2.length() >= 13) {
			 
			String num = resnum2.substring(0, 12);

			if (num.equals("999999999999")) {

				Double rest = Double.parseDouble(new DecimalFormat(Constants.FORMAT)
						.format((firstRes / Double.valueOf(itemRetailAmt)) * 100));

				return rest.toString();
			} else {

				num = resnum2.substring(2, 12);
				
				return proceso2(num, firstRes, res, itemRetailAmt);


			}
		}
		
		return res;
	}
	

	
	private String proceso2(String num, Double firstRes, String res, String itemRetailAmt) {
		
		LOGGER.info("******************** PASO 5 inicia proceso2() " + LocalTime.now().toString().substring(0, 8));
		
		if (num.equals("9999999999")) {

			Double rest = Double.parseDouble(new DecimalFormat(Constants.FORMAT)
					.format((firstRes / Double.valueOf(itemRetailAmt)) * 100));

			return rest.toString();
		}
		return res;
	}
	
	private String proceso3(Double rest, String res) {
		
		LOGGER.info("******************** PASO 5 inicia proceso3() " + LocalTime.now().toString().substring(0, 8));
		
		if (rest > 0) {

			return proceso5(res);

		} else {

			return proceso6(res);

			}
		}

	
	private String proceso5 (String res) {
		
		LOGGER.info("******************** PASO 5 inicia proceso5() " + LocalTime.now().toString().substring(0, 8));
		
		String[] parts = res.split("\\.");
		String part1 = parts[0];
		String part2 = parts[1];

		if (part2.length() == 1) {
			part2 = part2 + "0";
		} else {
			part2 = part2.substring(0, 2);
		}

		int part = Integer.parseInt(StringUtils.right(part1, 2));

		if (part <= 9 && part >= 1) {
			return  " " + part + "." + part2;
		} else if (part == 0) {
			return  "  " + "." + part2;
		} else {

			String number = part + "." + part2;

			return number;

		}
		
	}
	
	private String proceso6 (String res) {
		
		LOGGER.info("******************** PASO 5 inicia proceso6() " + LocalTime.now().toString().substring(0, 8));
		
		String charsToRemove = "-";
		String str = res.replace(String.valueOf(charsToRemove), "");

		str = str.toString();
		str = str.replace("-", "");

		String[] parts = str.split("\\.");
		String part1 = parts[0];
		String part2 = parts[1];

		if (part2.length() == 1) {

			part2 = part2 + "0";

		} else {
			part2 = part2.substring(0, 2);
		}

		int part = Integer.parseInt(StringUtils.right(part1, 2));

		if (part <= 9 && part >= 1) {
			res = " " + part + "." + part2 + "-";
			if (res.equals(Constants.NUMBERNEG)) {
				return  Constants.NUMBER;
			}
		} else if (part == 0) {
			res = "  " + "." + part2 + "-";
			if (res.equals(Constants.NUMBERNEG)) {
				return  Constants.NUMBER;
			}
		} else {

			String number = part + "." + part2;

			if (number.equals("99.99")) {
				return  Constants.NUMBER;
			} else if (number.equals(Constants.NUMBERNEG) || number.equals("100.00")) {
				return  Constants.NUMBER;
			} else {
				return  number + "-";

			}

		}
		return res;
		
	}
	
	private N01 proceso7(ItemStoreItemRetail itemStore, String res) {
		
		LOGGER.info("******************** PASO 6 inicia proceso7() " + LocalTime.now().toString().substring(0, 8));

		N01 n01 = new N01();
		
		String deptNbr = String.format("%0" + 4 + "d", Integer.valueOf(itemStore.getDeptNbr()));
		String upcNBR = String.format("%0" + 15 + "d", Long.valueOf(itemStore.getUpcNbr())).substring(0, 13);
		String itemNbr = String.format("%0" + 9 + "d", Integer.valueOf(itemStore.getItemNbr().toString()));
		String desc1 = String.format("%-30s", itemStore.getItem1Desc());
		String effSs = itemStore.getChangeEffDate().toString().substring(0, 10).replace("-", "");
		String cancelSs = itemStore.getChangeExpDate().toString().substring(0, 10).replace("-", "");
		String itemR = String.format("%0" + 7 + "d", Integer.valueOf(itemStore.getItemRetailAmt().replace(".", "")));
		String retail = String.format("%0" + 7 + "d", Integer.valueOf(itemStore.getCustRetailAmt().replace(".", "")));
		String especial = String.format("%0" + 7 + "d", Integer.valueOf(itemStore.getCustBaseRtlAmt().replace(".", "")));

		String[] partsItem = itemStore.getItemRetailAmt().split("\\.");
		String partItem1 = partsItem[0];
		String partItem2 = partsItem[1];

		if (partItem1.equals("0")) {
			itemStore.setItemRetailAmt("" + "." + partItem2);
		}

		n01.setItemR1(itemStore.getItemRetailAmt());

		String[] partsCust = itemStore.getCustRetailAmt().split("\\.");
		String partCust1 = partsCust[0];
		String partCust2 = partsCust[1];

		if (partCust1.equals("0")) {
			itemStore.setCustRetailAmt("" + "." + partCust2);
		}
		n01.setRetail1(itemStore.getCustRetailAmt());

		String[] partsRtlAmt = itemStore.getCustBaseRtlAmt().split("\\.");
		String partRtlAmt1 = partsRtlAmt[0];
		String partRtlAmt2 = partsRtlAmt[1];

		if (partRtlAmt1.equals("0")) {
			itemStore.setCustBaseRtlAmt("" + "." + partRtlAmt2);

		}

		if (res.contains("-")) {

			res = String.format("%-6s", res);

		} else {

			res = String.format("%-5s", res);

		}

		n01.setEspecial1(itemStore.getCustBaseRtlAmt());

		n01.setDep(deptNbr);
		n01.setUpc(upcNBR);
		n01.setItem(itemNbr);
		n01.setDesc1(desc1);
		n01.setEffSs(effSs);
		n01.setCancelSs(cancelSs);
		n01.setItemR(itemR);
		n01.setRetail(retail);
		n01.setEspecial(especial);
		n01.setPercent(res);
		
		itemStore = null;

		return n01;
	}
}