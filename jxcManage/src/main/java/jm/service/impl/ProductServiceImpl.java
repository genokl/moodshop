package jm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jm.basic.bean.queryCustomer.productWarehouseCustomer;
import jm.basic.bean.vo.ProductVo;
import jm.basic.mapper.customer.ProductWarehouseMapperCustomer;
import jm.service.ProductService;


@Transactional
@Service("memberService")
public class ProductServiceImpl implements ProductService{

	@Resource(name="productWarehouseMapperCustomer")
	private ProductWarehouseMapperCustomer productWarehouseMapperCustomer;
	

	@Override
	public List<productWarehouseCustomer> getInstanceByID(ProductVo productVo) {
		//  根据会员ID 查询一个会员
		return productWarehouseMapperCustomer.loadProductWarehouseListData(productVo);
	}


	
}
