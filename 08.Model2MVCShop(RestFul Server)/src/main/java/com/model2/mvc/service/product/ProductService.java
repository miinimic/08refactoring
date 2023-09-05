package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public interface ProductService {
	
	public void insertProduct(Product product) throws Exception;
	
	public Product findProduct(int prodNo) throws Exception;
	
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public void addCart(int prodNo, String userId) throws Exception;
	
	public int getCartNo(int prodNo) throws Exception;
	
	public void deleteProduct(int prodNo) throws Exception;
	
	public void deleteCart(int prodNo, String userId) throws Exception;
	
}