package jm.basic.bean;

import java.util.Date;

/**
 * 产品基本信息
 * 
 * @author wxy
 *
 */
public class ProductBaseInfo {

	private Integer id;// 用户ID
	private Integer barCode;// 条码
	private Integer productCode;// 产品编码/货号
	private Integer productClassId;// 产品分类表id
	private Integer merchantShopInfoId;// 店铺信息表id
	private String productName;// 产品名称
	private Integer delFlag;// 删除标识Def 0，0：正常，1：删除
	private Integer operatorUserInfoId;// 操作人:用户id
	private Date createdTime;// 创建时间
	private Date lastUpdatedTime;// 上次更新时间
	private String baseAttrName1;// 基础属性名1
	private String baseAttrVal1;//  基础属性值1
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBarCode() {
		return barCode;
	}
	public void setBarCode(Integer barCode) {
		this.barCode = barCode;
	}
	public Integer getProductCode() {
		return productCode;
	}
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
	public Integer getProductClassId() {
		return productClassId;
	}
	public void setProductClassId(Integer productClassId) {
		this.productClassId = productClassId;
	}
	public Integer getMerchantShopInfoId() {
		return merchantShopInfoId;
	}
	public void setMerchantShopInfoId(Integer merchantShopInfoId) {
		this.merchantShopInfoId = merchantShopInfoId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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
	
	
	
	
	
}
