package jm.basic.bean;

import java.util.Date;

/**
 * 产品库存信息表
 * 
 * @author wxy
 *
 */
public class WarehouseProduct {

	private Integer id;// 用户ID
	private Integer warehouseInfoId;// 仓库ID
	private Integer productBaseInfoId;// 产品基本信息表id
	private Integer num;// 库存数量
	private Integer remark;// 备注
	private String units;// 存储单位，计量单位
	private Integer operatorUserInfoId;// 操作用户Id
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWarehouseInfoId() {
		return warehouseInfoId;
	}
	public void setWarehouseInfoId(Integer warehouseInfoId) {
		this.warehouseInfoId = warehouseInfoId;
	}
	public Integer getProductBaseInfoId() {
		return productBaseInfoId;
	}
	public void setProductBaseInfoId(Integer productBaseInfoId) {
		this.productBaseInfoId = productBaseInfoId;
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
	public Integer getOperatorUserInfoId() {
		return operatorUserInfoId;
	}
	public void setOperatorUserInfoId(Integer operatorUserInfoId) {
		this.operatorUserInfoId = operatorUserInfoId;
	}

	
	
	
	
	
	
	
	
}
