package com.model2.mvc.service.purchase.test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
											"classpath:config/context-aspect.xml",
											"classpath:config/context-mybatis.xml",
											"classpath:config/context-transaction.xml" })
 
public class PurchaseServiceTest {

		//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
		@Autowired
		@Qualifier("purchaseServiceImpl")
		private PurchaseService purchaseService;
		
		@Autowired
		@Qualifier("productServiceImpl")
		private ProductService productService;
		
		@Autowired
		@Qualifier("userServiceImpl")
		private UserService userService;

		//@Test
		public void testInsertPurchase() throws Exception {
			
			User user = userService.getUser("user01");
			Product product = productService.findProduct(10011);
			
			System.out.println(user);
			System.out.println(product);
			
			Purchase purchase = new Purchase();
			
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			purchase.setPaymentOption("1");
			purchase.setReceiverName("testuser");
			purchase.setReceiverPhone("010-6666-9999");
			purchase.setDivyAddr("이수역");
			purchase.setDivyRequest("총알배송");
			purchase.setTranCode("2");
			purchase.setItem(50);
			purchase.setDivyDate("");
			purchase.setReviewCode(null);
			purchase.setReview("");
			purchase.setItem(2);

			purchaseService.insertPurchase(purchase);
			
			System.out.println(purchase); 


		}
		
		//@Test
		 public void testFindPurchase() throws Exception{
			
				Purchase purchase = new Purchase();
				
				purchase.setTranNo(10001);
				
				Map<String , Object> result = purchaseService.findPurchase(purchase.getTranNo());

				System.out.println(result+"조회 완료");

			 }
		 
		//@Test
		 public void testFindPurchaseItem() throws Exception{
			
				int tranNo = 10001;
				
				int result = purchaseService.findPurchaseItem(tranNo);

				System.out.println(result+"조회 완료");

			 }
		 
		//@Test
		 public void testFindCartCount() throws Exception{
			
				int prodNo = 10011;
				
				int result = purchaseService.findCartCount(prodNo);

				System.out.println(result+"조회 완료");

			 }
		 
		 
		 //@Test
		 public void testFindProductItem() throws Exception{
				
				int tranNo = 10001;
				
				int result = purchaseService.findProductItem(tranNo);

				System.out.println(result+"조회 완료");

			 }
		 
		 //@Test
		 public void testDeleteCart() throws Exception{
				
				int prodNo = 10011;
				
				purchaseService.deleteCart(prodNo);

				System.out.println("삭제 완료");

			 }
		 
		//@Test
		 public void testAddReview() throws Exception{
					
				String review = "좋아요!";
				int tranNo=10004;
				
				purchaseService.addReview(tranNo, review);
				
				Map<String , Object> result = purchaseService.findPurchase(tranNo);

				System.out.println(result+"review add 완료(update)");
			 }
		
		
		@Test
		 public void testUpdatePurchase() throws Exception{
				
				Purchase purchase = new Purchase();
				Product product = new Product();
				product.setProdNo(10012);
			
				purchase.setPaymentOption("1");
				purchase.setReceiverName("홍길동");
				purchase.setReceiverPhone("updatephone");
				purchase.setDivyAddr("역삼역");
				purchase.setDivyRequest("빠른배송부탁");
				purchase.setDivyDate("23/08/27");
				purchase.setItem(7);
				purchase.setPurchaseProd(product);
				purchase.setTranNo(10001);
								
				purchaseService.updatePurchase(purchase, purchase.getPurchaseProd().getProdNo());

				System.out.println(purchase+"update 완료");
			 }
		 
		//@Test
		 public void testUpdateReview() throws Exception{
			
				int tranNo = 10000;
				String review = "추천합니다.";
				
				purchaseService.updateReview(tranNo, review);
				
				Map<String , Object> result = purchaseService.findPurchase(tranNo);

				System.out.println(result+"update 완료");
			 }
		 
		//@Test
		 public void testDeleteReview() throws Exception{
			
				int tranNo = 10000;

				purchaseService.deleteReview(tranNo);
				
				Map<String , Object> result = purchaseService.findPurchase(tranNo);

				System.out.println(result+"(review 삭제 완료)update");
			 }
		 
		//@Test
		 public void testUpdateTranCode() throws Exception{
			
				Purchase purchase = new Purchase();
				
				purchase.setTranNo(10000);
				purchase.setReviewCode("777");
				purchase.setTranCode("888");
				
				purchaseService.updateTranCode(purchase);

				System.out.println(purchase+"update 완료");
			 }
		 
		@Test
		 public void testUpdateItem() throws Exception{
			
			 	int ProdNo = 10020;
			 	int Proitem = 50;
			 	int purItem = 2;
			 			
				purchaseService.updateItem(ProdNo, Proitem, purItem);
				
				Product result = productService.findProduct(ProdNo);

				System.out.println(result+"update 완료");
			 }
		 
		//@Test
		 public void testUpdateProductItem() throws Exception{
			
				int prodNo = 10012;
				int item = 5;
				
				purchaseService.updateProductItem(item, prodNo);
				
				Product product = productService.findProduct(prodNo);

				System.out.println(product+" : update 완료");
			 }
		 
		 //@Test
		 public void testGetPurchaseListAll() throws Exception{
			 
			 	Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	
			 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
			 	
			 	Map<String , Object> list = (Map<String, Object>) map.get("list");
			 	//Assert.assertEquals(3, list.size());
			 	
				//==> console 확인
			 	System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 }
		 
		//@Test
		 public void testGetCartListAll() throws Exception{
			 
				Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	
			 	Map<String,Object> map = purchaseService.getCartList(search, "user01");
			 	
			 	Map<String , Object> list = (Map<String, Object>) map.get("list");
			 	//Assert.assertEquals(3, list.size());
			 	
				//==> console 확인
			 	System.out.println(list);

			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 }
		 
		//@Test
		 public void testGetReviewListAll() throws Exception{
			 
				Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	
			 	Map<String,Object> map = purchaseService.getReviewList(search);
			 	
			 	Map<String , Object> list = (Map<String, Object>) map.get("list");
			 	//Assert.assertEquals(3, list.size());
			 	
				//==> console 확인
			 	System.out.println(list);

			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 }
		 
}
