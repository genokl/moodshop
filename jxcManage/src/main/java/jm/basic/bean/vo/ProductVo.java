package jm.basic.bean.vo;

import java.util.Date;

import jm.basic.bean.ProductBaseInfo;
import jm.basic.bean.WarehouseProduct;

/**
 * 自定义查询结果集
 * @author wxy
 *
 */
public class ProductVo  {

	private Integer operatorUserInfoId;// 操作人:用户id
	private Date createdTime;// 创建时间
	private Date lastUpdatedTime;// 上次更新时间
	private String productName;// 产品名称
	private Integer productClassId;// 产品分类表id
	
	private Integer num;//库存数量
	
	private Integer pageNo;		// 需要查询的页码
	private Integer pageSize;	// 每页查询的数量
	
	/**
	 * 默认根据上次更新库存时间倒序
	 *排序规则 
	 *1产品创建时间
	 *2上次更新库存时间 
	 *3库存数量
	 */
	private Integer orderType =2;
	/**
	 * 排序方式
	 * 1正序 asc
	 * 2倒序 desc
	 */
	private String orderWay ="desc";
	
	
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductClassId() {
		return productClassId;
	}
	public void setProductClassId(Integer productClassId) {
		this.productClassId = productClassId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}
	
	
	
	
	
	
	
	
	
	
	
}
