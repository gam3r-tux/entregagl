package com.walmart.test.apps.modals.Db2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Archive2 {
	
	String dateHour = String.format("%33s","RUN ON " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy")) + " AT " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));		
	String walmart = String.format("%57s","WAL-MART DE MEXICO, S.A. DE C.V.");
	String report = String.format("%40s","REPORT NO. FM807001MX");
	
	
	
	String date1 = String.format("%34s","FOR  PERIOD ENDING " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy")));	
	String status = String.format("%49s","I T E M S     W I T H   S T A T U S   M D");	
	String page = String.format("%35s","PAGE-NO.");
	
	
	
	String department = String.format("%18s","DEPARTAMENTO   ");
	
	
	
	String fecha1 = String.format("%68s","FECHA");
	String fecha2 = String.format("%12s","FECHA");
	String precio = String.format("%17s","PRECIO");
	String precio1 = String.format("%10s","PRECIO");
	
	
	
	String upc = String.format("%11s","UPC");
	String item = String.format("%17s","ITEM");
	String descripcion = String.format("%22s","DESCRIPCION");
	String desde = String.format("%18s","DESDE");
	String hasta = String.format("%12s","HASTA");
	String unid = String.format("%15s","UNID");
	String normal = String.format("%12s","NORMAL");
	String especial = String.format("%13s","ESPECIAL");
	String margen = String.format("%10s","%MARGEN");
	public String getDateHour() {
		return dateHour;
	}
	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}
	public String getWalmart() {
		return walmart;
	}
	public void setWalmart(String walmart) {
		this.walmart = walmart;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFecha1() {
		return fecha1;
	}
	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}
	public String getFecha2() {
		return fecha2;
	}
	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getPrecio1() {
		return precio1;
	}
	public void setPrecio1(String precio1) {
		this.precio1 = precio1;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	public String getEspecial() {
		return especial;
	}
	public void setEspecial(String especial) {
		this.especial = especial;
	}
	public String getMargen() {
		return margen;
	}
	public void setMargen(String margen) {
		this.margen = margen;
	}
	


}