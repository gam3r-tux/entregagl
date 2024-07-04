package com.walmart.test.apps.service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.test.apps.modals.Db2.Archive2;
import com.walmart.test.apps.modals.Db2.N01;
import com.walmart.test.apps.util.Constants;

@Service
public class Archive2Service {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Archive2Service.class);

	@Autowired
	private UploadFileService uploadFileService;

	public void createTxt1(List<N01> n01List, Integer count) {

		LOGGER.info("---------------------- Paso 6 inicia createTxt1" + LocalTime.now().toString().substring(0, 8));

		try {
			
			StringBuilder builder = new StringBuilder();
			
			Long num = 0L;

			String department = "";

			DecimalFormat formatPage = new DecimalFormat("###,###");

			Long page = 0L;

			Long records = 0L;

			Integer total = 0;

			Integer fail = 0;
			
			Archive2 archive2 = new Archive2();

			for (N01 n01 : n01List) {

				DecimalFormat format1 = new DecimalFormat("###,###");

				String rec = String.format("%9s", format1.format(records));

			
				if (num == 0) {

					department = n01.getDep();
					page = 1L;

					builder.append("1" + archive2.getDateHour() + archive2.getWalmart() + archive2.getReport());
					builder.append("\n");
					builder.append(
							archive2.getDate1() + archive2.getStatus() + archive2.getPage() + "         " + page);
					builder.append("\n");
					builder.append(archive2.getDepartment() + n01.getDep() + String.format("%63s", " "));
					builder.append("\n");
					builder.append("\n");
					builder.append(
							archive2.getFecha1() + archive2.getFecha2() + archive2.getPrecio() + archive2.getPrecio1());
					builder.append("\n");
					builder.append(archive2.getUpc() + archive2.getItem() + archive2.getDescripcion()
							+ archive2.getDesde() + archive2.getHasta() + archive2.getUnid() + archive2.getNormal()
							+ archive2.getEspecial() + archive2.getMargen());
					builder.append("\n");
					builder.append("\n");



				}

				if (n01.getDep().equals("")) {

					num = 1L;
					num = num + 1L;
					department = n01.getDep();
					page = page + 1L;

					String pages = String.format("%10s", formatPage.format(page));

					builder.append("0  " + Constants.REGISTROS + rec);
					builder.append("\n");
					records = 0L;
					builder.append("1" + archive2.getDateHour() + archive2.getWalmart() + archive2.getReport());
					builder.append("\n");
					builder.append(archive2.getDate1() + archive2.getStatus() + archive2.getPage() + pages);
					builder.append("\n");
					builder.append(archive2.getDepartment() + n01.getDep() + String.format("%63s", " "));
					builder.append("\n");
					builder.append("\n");
					builder.append(
							archive2.getFecha1() + archive2.getFecha2() + archive2.getPrecio() + archive2.getPrecio1());
					builder.append("\n");
					builder.append(archive2.getUpc() + archive2.getItem() + archive2.getDescripcion()
							+ archive2.getDesde() + archive2.getHasta() + archive2.getUnid() + archive2.getNormal()
							+ archive2.getEspecial() + archive2.getMargen());
					builder.append("\n");
					builder.append("\n");

				}

				if (!n01.getDep().equals(department) && num == 47) {

					num = 0L;
					department = n01.getDep();
					page = page + 1L;

					String pages = String.format("%10s", formatPage.format(page));

					builder.append("0  " + Constants.REGISTROS + rec);
					builder.append("\n");
					records = 0L;
					builder.append("1" + archive2.getDateHour() + archive2.getWalmart() + archive2.getReport());
					builder.append("\n");
					builder.append(archive2.getDate1() + archive2.getStatus() + archive2.getPage() + pages);
					builder.append("\n");
					builder.append(archive2.getDepartment() + n01.getDep() + String.format("%63s", " "));
					builder.append("\n");
					builder.append("\n");
					builder.append(
							archive2.getFecha1() + archive2.getFecha2() + archive2.getPrecio() + archive2.getPrecio1());
					builder.append("\n");
					builder.append(archive2.getUpc() + archive2.getItem() + archive2.getDescripcion()
							+ archive2.getDesde() + archive2.getHasta() + archive2.getUnid() + archive2.getNormal()
							+ archive2.getEspecial() + archive2.getMargen());

					builder.append("\n");
					builder.append("\n");
				}

				if (num == 47) {

					num = 0L;
					department = n01.getDep();
					page = page + 1L;

					String pages = String.format("%10s", formatPage.format(page));

					builder.append("1" + archive2.getDateHour() + archive2.getWalmart() + archive2.getReport());
					builder.append("\n");
					builder.append(archive2.getDate1() + archive2.getStatus() + archive2.getPage() + pages);
					builder.append("\n");
					builder.append(archive2.getDepartment() + n01.getDep() + String.format("%63s", " "));
					builder.append("\n");
					builder.append("\n");
					builder.append(
							archive2.getFecha1() + archive2.getFecha2() + archive2.getPrecio() + archive2.getPrecio1());
					builder.append("\n");
					builder.append(archive2.getUpc() + archive2.getItem() + archive2.getDescripcion()
							+ archive2.getDesde() + archive2.getHasta() + archive2.getUnid() + archive2.getNormal()
							+ archive2.getEspecial() + archive2.getMargen());

					builder.append("\n");
					builder.append("\n");
				}

				if (!n01.getDep().equals(department)) {

					num = 0L;
					department = n01.getDep();
					page = page + 1L;

					String pages = String.format("%10s", formatPage.format(page));

					builder.append("0  " + Constants.REGISTROS + rec);
					records = 0L;
					builder.append("\n");
					builder.append("1" + archive2.getDateHour() + archive2.getWalmart() + archive2.getReport());
					builder.append("\n");
					builder.append(archive2.getDate1() + archive2.getStatus() + archive2.getPage() + pages);
					builder.append("\n");
					builder.append(archive2.getDepartment() + n01.getDep() + String.format("%63s", " "));
					builder.append("\n");
					builder.append("\n");
					builder.append(
							archive2.getFecha1() + archive2.getFecha2() + archive2.getPrecio() + archive2.getPrecio1());
					builder.append("\n");
					builder.append(archive2.getUpc() + archive2.getItem() + archive2.getDescripcion()
							+ archive2.getDesde() + archive2.getHasta() + archive2.getUnid() + archive2.getNormal()
							+ archive2.getEspecial() + archive2.getMargen());

					builder.append("\n");
					builder.append("\n");
				}


				String ups = "";
				String item = "";
				String desc1 = "";
				String effSs = "";
				String cancelSs = "";
				String itemR = "";
				String retail = "";
				String especial = "";
				String percent = n01.getPercent();

				ups = String.format("%16s", n01.getUpc());

				item = String.format("%15s", n01.getItem());
				desc1 = String.format("%-25s", n01.getDesc1().trim());
				effSs = n01.getEffSs();
				cancelSs = String.format("%12s", n01.getCancelSs());

				DecimalFormat format = new DecimalFormat("###,###.00");

				String itemR1 = format.format(Double.valueOf(n01.getItemR1())).toString();
				itemR = String.format("%14s", itemR1);

				String retail1 = format.format(Double.valueOf(n01.getRetail1())).toString();
				retail = String.format("%10s", retail1);

				String especial1 = format.format(Double.valueOf(n01.getEspecial1())).toString();
				especial = String.format("%13s", especial1);

				total = total + 1;
				
				builder.append(ups + item + "      " + desc1 + effSs + cancelSs + itemR + retail + especial + "    "
						+ percent);
				builder.append("\n");

				num = num + 1L;
				records = records + 1L;

				proceso1(count, total, records, fail, builder);

			}

			LOGGER.info("---------------------- Paso 6 fin createTxt1" + LocalTime.now().toString().substring(0, 8));
			
			n01List = null;

			uploadFileService.tex(builder.toString());
			
		} catch (Exception e) {
			
			LOGGER.error("context", e);
		}

	}
	
	
	private void proceso1 (Integer count, Integer total, Long records, Integer fail, StringBuilder builder) {
		
		LOGGER.info("---------------------- Paso 6 fin proceso1" + LocalTime.now().toString().substring(0, 8));
		
		if (count.equals(total)) {

			DecimalFormat format2 = new DecimalFormat("#,###");

			String rec = String.format("%9s", format2.format(records));

			builder.append("0  " + Constants.REGISTROS + rec);
			builder.append("\n");

			String subtract = String.valueOf(total - fail);

			if (subtract.length() >= 4) {

				int res = subtract.length() - 3;

				String point = subtract.substring(0, res) + "," + subtract.substring(res, subtract.length());

				String regis = String.format("%9s", point);
				
				builder.append("   TOTAL   DE  REGISTROS      =>" + regis);
				builder.append("\n");

			} else {

				String regis = String.format("%9s", format2.format(total - (long)fail));
					
				builder.append("   TOTAL   DE  REGISTROS      =>" + regis);
			}
			
			
		}
		
	}

}