package com.model2.mvc.service.product;

import java.util.Map;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	//admin이 판매중인 상품 delete
	public void deleteProduct(int ProdNo) throws Exception;
	
	public void deleteCart(int ProdNo, String userId) throws Exception;

	//admin이 상품 등록
	public void insertProduct(Product product) throws Exception;
	
	//user가 장바구니에 상품 insert
	public void addCart(int prodNo, String userId) throws Exception;
	
	//cart_no select
	public int getCartNo(int prodNo) throws Exception;
		
	//getProductList
	public List<Product> getProductList(Search search) throws Exception;
	
	public List<Product> getSaleList(Search search) throws Exception;


	//product, transaction의 tran_status_code 조회
	public Product findProduct(int prodNo) throws Exception;

	//상품 수정(admin)
	public void updateProduct(Product product) throws Exception;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception;
	
	public int getTotalCountSale(Search search) throws Exception;
	
}