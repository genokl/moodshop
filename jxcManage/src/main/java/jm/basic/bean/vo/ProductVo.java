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

	private ProductBaseInfo productBaseInfo;
	private WarehouseProduct warehouseProduct;
	
	private Integer pageIndex;		// 需要查询的页码
	private Integer currentNum;		// 每页查询的数量
	
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
	
	public ProductBaseInfo getProductBaseInfo() {
		return productBaseInfo;
	}
	public void setProductBaseInfo(ProductBaseInfo productBaseInfo) {
		this.productBaseInfo = productBaseInfo;
	}
	public WarehouseProduct getWarehouseProduct() {
		return warehouseProduct;
	}
	public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
		this.warehouseProduct = warehouseProduct;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
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
