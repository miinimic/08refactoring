package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseDao {

	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public Map<String , Object> findPurchase(int tranNo) throws Exception;

	public void updatePurchase(Purchase purchase, int prodNo) throws Exception;
	
	public void updateTranCode(int tranNo, String tranCode) throws Exception;	
	
	//장바구니 조회
	public Map<String,Object> getCartList(Search search, String userId) throws Exception;
	
	public int findCartCount(int ProdNo) throws Exception;
	
	public void deleteCart(int ProdNo) throws Exception;
	
	public void deleteReview(int tranNo) throws Exception;
	
	public void addReview(int tranNo, String review) throws Exception;
	
	public void updateReview(int tranNo, String review) throws Exception;
	
	public void updateItem(int ProdNo, int Proitem, int purItem) throws Exception;
	
	public Map<String,Object> getReviewList(Search search) throws Exception;
	
	public void updateProductItem(int item, int prodNo) throws Exception;
	
	public int findPurchaseItem(int tranNo) throws Exception;
	
	public int findProductItem(int tranNo) throws Exception;
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(String buyerId) throws Exception ;
	
	public int getTotalCountCart(String userId) throws Exception ;
	
	public int getTotalCountReview() throws Exception ;

}