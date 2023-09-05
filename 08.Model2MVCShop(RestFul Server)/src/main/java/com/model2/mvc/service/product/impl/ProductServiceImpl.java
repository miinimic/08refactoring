package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
	
	///Field
	@Autowired
	@Qualifier("productDaoImpl")	
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		System.out.println("::"+getClass()+".setProductDao Call...");
		this.productDao = productDao;
	}
	
	///Constructor
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void insertProduct(Product product) throws Exception {
		productDao.insertProduct(product);
	}
	
	public Map<String,Object> getProductList(Search search) throws Exception {
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;

	}
	
	public Map<String,Object> getSaleList(Search search) throws Exception {
		List<Product> list= productDao.getSaleList(search);
		int totalCount = productDao.getTotalCountSale(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;

	}
	
	public Product findProduct(int prodNo) throws Exception {
		return productDao.findProduct(prodNo);
	}
	
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
	
	public void addCart(int prodNo, String userId) throws Exception {
		productDao.addCart(prodNo, userId);
	}

	public int getCartNo(int prodNo) throws Exception{
		return productDao.getCartNo(prodNo);
	} 
	public void deleteProduct(int prodNo) throws Exception{
		productDao.deleteProduct(prodNo);
	}
	public void deleteCart(int prodNo, String userId) throws Exception{
		productDao.deleteCart(prodNo, userId);
	}


}