package jm.basic.mapper.customer;

import java.util.List;

import jm.basic.bean.queryCustomer.productWarehouseCustomer;
import jm.basic.bean.vo.ProductVo;

public interface ProductWarehouseMapperCustomer {
	
	/**
	 * 查询产品库存信息列表
	 * @param activityReward
	 * @return
	 * @author sxl
	 * @Time 2017年8月28日 下午1:59:45
	 */
	List<productWarehouseCustomer> loadProductWarehouseListData(ProductVo productVo);
}
