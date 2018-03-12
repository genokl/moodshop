package jm.basic.bean.queryCustomer;

import java.util.Date;

import jm.basic.bean.WarehouseProduct;

/**
 * 自定义查询结果集
 * @author wxy
 *
 */
public class productWarehouseCustomer  {

	private Integer productBaseInfoId;//主要属性值
	private String productName;//产品名称
	private Integer operatorUserInfoId;//操作人id
	private Date createdTime;//创建时间
	private Date lastUpdateTime;//创建时间
	private String baseAttrName1;//主要属性名
	private String baseAttrVal1;//主要属性值
	
	private Integer warehouseInfoId;//主要属性值
	private Integer num;//主要属性值
	private Integer remark;//备注
	private String units;//计量单位
	
	
	public Integer getProductBaseInfoId() {
		return productBaseInfoId;
	}
	public void setProductBaseInfoId(Integer productBaseInfoId) {
		this.productBaseInfoId = productBaseInfoId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getOperatorUserInfoId() {
		return operatorUserInfoId;
	}
	public void setOperatorUserInfoId(Integer operatorUserInfoId) {
		this.operatorUserInfoId = operatorUserInfoId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getBaseAttrName1() {
		return baseAttrName1;
	}
	public void setBaseAttrName1(String baseAttrName1) {
		this.baseAttrName1 = baseAttrName1;
	}
	public String getBaseAttrVal1() {
		return baseAttrVal1;
	}
	public void setBaseAttrVal1(String baseAttrVal1) {
		this.baseAttrVal1 = baseAttrVal1;
	}
	public Integer getWarehouseInfoId() {
		return warehouseInfoId;
	}
	public void setWarehouseInfoId(Integer warehouseInfoId) {
		this.warehouseInfoId = warehouseInfoId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getRemark() {
		return remark;
	}
	public void setRemark(Integer remark) {
		this.remark = remark;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	
	
	
	
	
	
	
	
	
}
