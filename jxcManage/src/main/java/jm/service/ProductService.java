package jm.service;

import java.util.List;

import jm.basic.bean.queryCustomer.productWarehouseCustomer;
import jm.basic.bean.vo.ProductVo;

public interface ProductService {

	/**
	 * 查询产品库存信息列表
	 * @param productVo
	 * @return
	 */
	List<productWarehouseCustomer> getInstanceByID(ProductVo productVo);


}
