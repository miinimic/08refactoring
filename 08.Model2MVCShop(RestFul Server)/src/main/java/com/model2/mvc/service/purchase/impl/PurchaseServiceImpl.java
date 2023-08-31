package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	///Field
	@Autowired
	@Qualifier("purchaseDaoImpl")	
	private PurchaseDao purchaseDao;
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		System.out.println("::"+getClass()+".setUserDao Call...");
		this.purchaseDao = purchaseDao;
	}
	
	///Constructor
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	public void insertPurchase(Purchase purchase) throws Exception {
		System.out.println("purchaseserviceImpl 임 : "+purchase);
		purchaseDao.insertPurchase(purchase); 
		
		purchaseDao.updateItem(purchase.getPurchaseProd().getProdNo(), purchase.getPurchaseProd().getItem(), purchase.getItem());
			
		int count = purchaseDao.findCartCount(purchase.getPurchaseProd().getProdNo());	
		
		if(count > 0) {
			purchaseDao.deleteCart(purchase.getPurchaseProd().getProdNo());	
		}

	}
	
	public Map<String , Object > getPurchaseList(Search search, String buyerId) throws Exception {
		System.out.println("purchaseserviceImpl 로 들어온 buyerId : "+buyerId);
		Map<String , Object > list= purchaseDao.getPurchaseList(search, buyerId); 
		int totalCount = purchaseDao.getTotalCount(buyerId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String,Object> getCartList(Search search, String userId) throws Exception {
		Map<String , Object > list= purchaseDao.getCartList(search, userId); 
		int totalCount = purchaseDao.getTotalCountCart(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public Map<String , Object> findPurchase(int tranNo) throws Exception {
		System.out.println("findPurchase 서비스 임플 실행!");
		return purchaseDao.findPurchase(tranNo);
	}

	public void updateProductItem(int item, int prodNo) throws Exception{
		purchaseDao.updateProductItem(item, prodNo);
	}
	
	public void updatePurchase(Purchase purchase, int prodNo) throws Exception {
		purchaseDao.updatePurchase(purchase, prodNo);

	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase); 
	}
	
	public int findPurchaseItem(int tranNo) throws Exception{
		int purItem = purchaseDao.findPurchaseItem(tranNo); 
		return purItem;
	}
	
	public int findProductItem(int tranNo) throws Exception{
		int proItem = purchaseDao.findProductItem(tranNo); 
		return proItem;
	}
	
	public int findCartCount(int ProdNo) throws Exception{
		int cartCount = purchaseDao.findCartCount(ProdNo); 
		return cartCount;
	}
	
	public void updateItem(int ProdNo, int Proitem, int purItem) throws Exception{
		purchaseDao.updateItem(ProdNo, Proitem, purItem);
	}
	
	public Map<String,Object> getReviewList(Search search) throws Exception {
		Map<String,Object> list= purchaseDao.getReviewList(search);
		int totalCount = purchaseDao.getTotalCountReview();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void updateReview(int tranNo, String review) throws Exception {
		purchaseDao.updateReview(tranNo, review);
	}
	public void deleteCart(int prodNo) throws Exception {
		purchaseDao.deleteCart(prodNo);
	}
	
	public void addReview(int tranNo, String review) throws Exception {
		purchaseDao.addReview(tranNo, review);
	}
	
	public void deleteReview(int tranNo) throws Exception {
		purchaseDao.deleteReview(tranNo);
	}

}