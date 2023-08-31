package com.model2.mvc.service.purchase.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
		///Field
		@Autowired
		@Qualifier("sqlSessionTemplate")
		
		private SqlSession sqlSession;
		
		public void setSqlSession(SqlSession sqlSession) {
			System.out.println("::"+getClass()+".setSqlSession() Call....");
			this.sqlSession = sqlSession;
		}
		
		///Constructor
		public PurchaseDaoImpl() {
			System.out.println(this.getClass());
		}
	
		///Method
		public void insertPurchase(Purchase purchase) throws Exception{
			System.out.println("purchasedaoImpl 임 : "+purchase);
			sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
			
		}
		
		public Map<String, Object> getCartList(Search search,String userId ) throws Exception{
			System.out.println("purchasedaoImpl로 들어온 : "+userId);
			Map<String , Object>  map = new HashMap<String, Object>();
			Product product = new Product();
			List<Product> listProduct = null;
			
				map.put("search", search);
				map.put("userId", userId);
			
				System.out.println(map);
				List<Cart> list = sqlSession.selectList("PurchaseMapper.getCartList", map); 
		
				for (int i = 0; i < list.size(); i++) {
					System.out.println("cartNo : "+list.get(i).getCartNo());
					System.out.println("prodNo : "+list.get(i).getProdNo());
					list.get(i).setCartProd((Product)sqlSession.selectOne("ProductMapper.findProduct", list.get(i).getProdNo()));
					
				}
				map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCountCart", userId));
				
				map.put("list", list);
				
			return map;
		}
		
		public Map<String, Object> getPurchaseList(Search search,String buyerId ) throws Exception{
			System.out.println("purchasedaoImpl로 들어온 : "+buyerId);
			Map<String , Object>  map = new HashMap<String, Object>();
			
				map.put("search", search);
				map.put("buyerId", buyerId);
			
				System.out.println(map);
				List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map); 
				
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
					System.out.println("UserMapper에서 받은 user : "+list.get(i).getBuyer().getUserId());
					list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.findProduct", list.get(i).getPurchaseProd().getProdNo()));
					System.out.println("productmapper에서 받은 product : "+list.get(i).getPurchaseProd().getProdNo());
				}
				
				map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId));
		
				map.put("list", list);

			return map;
		}
		
		public Map<String, Object> getReviewList(Search search) throws Exception{
			Map<String , Object>  map = new HashMap<String, Object>();
				List<Purchase> list = sqlSession.selectList("PurchaseMapper.getReviewList", search); 
				
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
					System.out.println("UserMapper에서 받은 user : "+list.get(i).getBuyer().getUserId());
					list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.findProduct", list.get(i).getPurchaseProd().getProdNo()));
					System.out.println("productmapper에서 받은 product : "+list.get(i).getPurchaseProd().getProdNo());
				}
				
				map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCountReview"));
		
				map.put("list", list);

			return map;
		}
		
		public Map<String , Object> findPurchase(int tranNo) throws Exception{
			Map<String , Object>  map = new HashMap<String, Object>();
			
			Purchase purchase = sqlSession.selectOne("PurchaseMapper.findPurchase", tranNo);
			
			User user = sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId());
			Product product = sqlSession.selectOne("ProductMapper.findProduct", purchase.getPurchaseProd().getProdNo());
			
			map.put("product", product);
			map.put("user", user);
			map.put("purchase", purchase);
			
			return map;
		}

		public void updatePurchase(Purchase purchase, int prodNo) throws Exception{

			int proItem = sqlSession.selectOne("PurchaseMapper.findProductItem", purchase.getTranNo());			
			int tranItem = sqlSession.selectOne("PurchaseMapper.findPurchaseItem", purchase.getTranNo());	 

			int item = (proItem + tranItem) - purchase.getItem();		
			
			sqlSession.update("PurchaseMapper.updatePurchase", purchase);
			
			updateProductItem(item, prodNo);

		}
		
		public void updateTranCode(Purchase purchase) throws Exception{
			sqlSession.update("PurchaseMapper.updateTranCode", purchase);
		}
		
		public int findCartCount(int ProdNo) throws Exception{
			return sqlSession.selectOne("PurchaseMapper.findCartCount", ProdNo);
		}
		
		public void deleteCart(int ProdNo) throws Exception{
			sqlSession.delete("PurchaseMapper.deleteCart", ProdNo);
		}
		
		public void deleteReview(int tranNo) throws Exception{
			sqlSession.update("PurchaseMapper.deleteReview", tranNo);
		}
		
		public void addReview(int tranNo, String review) throws Exception{
			Map<String , Object>  map = new HashMap<String, Object>();
			
			map.put("tranNo", tranNo);
			map.put("review", review);
			
			sqlSession.update("PurchaseMapper.addReview", map);
		}
		
		public void updateReview(int tranNo, String review) throws Exception{
			
			Map<String , Object>  map = new HashMap<String, Object>();
			
			map.put("tranNo", tranNo);
			map.put("review", review);

			sqlSession.update("PurchaseMapper.updateReview", map);
		}
		
		public void updateItem(int ProdNo, int Proitem, int purItem) throws Exception{
			
			int item = Proitem-purItem;
			
			Map<String , Object>  map = new HashMap<String, Object>();
			
			map.put("prodNo", ProdNo);
			map.put("item", item);
			
			sqlSession.update("PurchaseMapper.updateItem", map);
				
		}
	
		//public Map<String,Object> getReviewList(Search search, String userId) throws Exception;
		
		public void updateProductItem(int item, int prodNo) throws Exception{
			
			Map<String , Object>  map = new HashMap<String, Object>();
			
			map.put("item", item);
			map.put("prodNo", prodNo);
			
			sqlSession.update("PurchaseMapper.updateProductItem", map);	
			
		}
		
		public int findPurchaseItem(int tranNo) throws Exception{
			return sqlSession.selectOne("PurchaseMapper.findPurchaseItem", tranNo);
		}
		
		public int findProductItem(int tranNo) throws Exception{
			return sqlSession.selectOne("PurchaseMapper.findProductItem", tranNo);
		}	
		
		//public Map<String,Object> getSaleList(Search search) throws Exception;
		
		// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
		public int getTotalCount(String buyerId) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId);
		}
		
		public int getTotalCountCart(String userId) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getTotalCountCart", userId);
		}
		
		public int getTotalCountReview() throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getTotalCountReview");
		}

}
