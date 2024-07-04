package com.walmart.test.apps.modals.Db2;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class ItemStoreItemRetail {
	
	private String deptNbr;
	
	private String upcNbr;
	
	private String item1Desc;

	private String custBaseRtlAmt;	

	private String unitCostAmt;
	
	private Integer itemNbr;
	
	private Integer ruleId;
	
	private String changeEffDate;
	
	private String changeExpDate;
	
	private String itemRetailAmt;
	
	private String custRetailAmt;

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getChangeEffDate() {
		return changeEffDate;
	}

	public void setChangeEffDate(String changeEffDate) {
		this.changeEffDate = changeEffDate;
	}

	public String getChangeExpDate() {
		return changeExpDate;
	}

	public void setChangeExpDate(String changeExpDate) {
		this.changeExpDate = changeExpDate;
	}

	public String getItemRetailAmt() {
		return itemRetailAmt;
	}

	public void setItemRetailAmt(String itemRetailAmt) {
		this.itemRetailAmt = itemRetailAmt;
	}

	public String getCustRetailAmt() {
		return custRetailAmt;
	}

	public void setCustRetailAmt(String custRetailAmt) {
		this.custRetailAmt = custRetailAmt;
	}

	public String getDeptNbr() {
		return deptNbr;
	}

	public void setDeptNbr(String deptNbr) {
		this.deptNbr = deptNbr;
	}

	public String getItem1Desc() {
		return item1Desc;
	}

	public void setItem1Desc(String item1Desc) {
		this.item1Desc = item1Desc;
	}

	public String getCustBaseRtlAmt() {
		return custBaseRtlAmt;
	}

	public void setCustBaseRtlAmt(String custBaseRtlAmt) {
		this.custBaseRtlAmt = custBaseRtlAmt;
	}

	public String getUnitCostAmt() {
		return unitCostAmt;
	}

	public void setUnitCostAmt(String unitCostAmt) {
		this.unitCostAmt = unitCostAmt;
	}

	public Integer getItemNbr() {
		return itemNbr;
	}

	public void setItemNbr(Integer itemNbr) {
		this.itemNbr = itemNbr;
	}

	public String getUpcNbr() {
		return upcNbr;
	}

	public void setUpcNbr(String upcNbr) {
		this.upcNbr = upcNbr;
	}

	@Override
	public String toString() {
		return "ItemStoreItemRetail [deptNbr=" + deptNbr + ", upcNBR=" + upcNbr + ", item1Desc=" + item1Desc
				+ ", custBaseRtlAmt=" + custBaseRtlAmt + ", unitCostAmt=" + unitCostAmt + ", itemNbr=" + itemNbr
				+ ", ruleId=" + ruleId + ", changeEffDate=" + changeEffDate + ", changeExpDate=" + changeExpDate
				+ ", itemRetailAmt=" + itemRetailAmt + ", custRetailAmt=" + custRetailAmt + "]";
	}

}

