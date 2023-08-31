package com.model2.mvc.service.product.test;

import java.sql.Date;
import java.util.HashMap;
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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
												"classpath:config/context-aspect.xml",
												"classpath:config/context-mybatis.xml",
												"classpath:config/context-transaction.xml" })

public class ProductServiceTest {

		//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
		@Autowired
		@Qualifier("productServiceImpl")
		private ProductService productService;

		//@Test
		public void testInsertProduct() throws Exception {
			
			Product product = new Product();
			product.setProdName("양념감자");
			product.setProdDetail("test");
			product.setManuDate("230850");
			product.setPrice(500);
			product.setFileName("file");
			product.setCategory("etc");
			product.setItem(50);
			
			productService.insertProduct(product);
			Product result = productService.findProduct(product.getProdNo());
			
			System.out.println(result);

		}
		//@Test
		public void testDeleteProduct() throws Exception {
			
			Product product = new Product();
			product.setProdNo(10019);
			
			productService.deleteProduct(product.getProdNo());
			
			System.out.println(product.getProdNo()+"이 삭제됨");

		}
		
		//@Test
		 public void testUpdateProduct() throws Exception{
			
				Product product = new Product();
				
				product.setProdNo(10013);
				product.setProdName("새우버거");
				product.setProdDetail("왕새우");
				product.setManuDate("23/08/23");
				product.setPrice(15000);
				product.setFileName("update.jpg");
				product.setCategory("seafood");
				product.setItem(999);
				
				productService.updateProduct(product);

				System.out.println(product+"update 완료");

			 }
		 
		 	//@Test
			 public void testFindProduct() throws Exception{
				
					Product product = new Product();
					
					product.setProdNo(10013);
					
					Product result = productService.findProduct(product.getProdNo());

					System.out.println(result+"조회 완료");

				 }
			 
			 //@Test
			 public void testGetProductListAll() throws Exception{
				 
				 	Search search = new Search();
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	Map<String,Object> map = productService.getProductList(search);
				 	
				 	List<Object> list = (List<Object>)map.get("list");
				 	Assert.assertEquals(3, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	Integer totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 	
				 	System.out.println("=======================================");
				 	
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	search.setSearchCondition("0");
				 	search.setSearchKeyword("");
				 	map = productService.getProductList(search);
				 	
				 	list = (List<Object>)map.get("list");
				 	Assert.assertEquals(3, list.size());
				 	
				 	System.out.println(list);
				 	
				 	totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 }
			 
			//@Test
			 public void testGetProductList() throws Exception{
				 
				 	Search search = new Search();
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	//search.setSearchCondition("0");
				 	search.setMenu("manage");
				 	search.setOrder("desc");
				 	search.setCategory("chicken");
				 	//search.setSearchKeyword("10026");
				 	Map<String,Object> map = productService.getProductList(search);
				 	
				 	List<Object> list = (List<Object>)map.get("list");
				 	//Assert.assertEquals(3, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	Integer totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 	
				 	System.out.println("=======================================");

				 }
			 
			//@Test
			 public void testGetSaleList() throws Exception{
				 
				 	Search search = new Search();
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	search.setSearchCondition("2");
				 	search.setSearchKeyword("500");
				 	Map<String,Object> map = productService.getSaleList(search);
				 	
				 	List<Object> list = (List<Object>)map.get("list");
				 	//Assert.assertEquals(3, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	Integer totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 	
				 	System.out.println("=======================================");

				 }
			 
			 
			 //@Test
			 public void testGetProductListByProdNo() throws Exception{
				 
			 	Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	search.setSearchCondition("0");
			 	search.setSearchKeyword("10012");
			 	Map<String,Object> map = productService.getProductList(search);
			 	
			 	List<Object> list = (List<Object>)map.get("list");
			 	Assert.assertEquals(1, list.size());
			 	
				//==> console 확인
			 	System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 	
			 	search.setSearchCondition("0");
			 	search.setSearchKeyword(""+System.currentTimeMillis());
			 	map = productService.getProductList(search);
			 	
			 	list = (List<Object>)map.get("list");
			 	Assert.assertEquals(0, list.size());
			 	
				//==> console 확인
			 	System.out.println(list);
			 	
			 	totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 }
			 
			 //@Test
			 public void testGetProductListByProdName() throws Exception{
				 
				 	Search search = new Search();
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	search.setSearchCondition("1");
				 	search.setSearchKeyword("소갈비");
				 	Map<String,Object> map = productService.getProductList(search);
				 	
				 	List<Object> list = (List<Object>)map.get("list");
				 	Assert.assertEquals(1, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	Integer totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 	
				 	System.out.println("=======================================");
				 	
				 	search.setSearchCondition("0");
				 	search.setSearchKeyword(""+System.currentTimeMillis());
				 	map = productService.getProductList(search);
				 	
				 	list = (List<Object>)map.get("list");
				 	Assert.assertEquals(0, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 }
			 //@Test
			 public void testGetProductListByPrice() throws Exception{
				 
				 	Search search = new Search();
				 	search.setCurrentPage(1);
				 	search.setPageSize(3);
				 	search.setSearchCondition("2");
				 	search.setSearchKeyword("5000");
				 	Map<String,Object> map = productService.getProductList(search);
				 	
				 	List<Object> list = (List<Object>)map.get("list");
				 	Assert.assertEquals(1, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	Integer totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 	
				 	System.out.println("=======================================");
				 	
				 	search.setSearchCondition("0");
				 	search.setSearchKeyword(""+System.currentTimeMillis());
				 	map = productService.getProductList(search);
				 	
				 	list = (List<Object>)map.get("list");
				 	Assert.assertEquals(0, list.size());
				 	
					//==> console 확인
				 	System.out.println(list);
				 	
				 	totalCount = (Integer)map.get("totalCount");
				 	System.out.println(totalCount);
				 }
			 
				//@Test
				public void testInsertCart() throws Exception {
					
					Product product = new Product();
					User user = new User();
					product.setProdNo(10011);
					user.setUserId("user01");				
					
					productService.addCart(product.getProdNo(), user.getUserId());
					
					System.out.println("cart insert 완료");

				}
				
				//@Test
				 public void testGetCartNo() throws Exception{
					
						Product product = new Product();
						
						product.setProdNo(10011);
						
						int cart_no = productService.getCartNo(product.getProdNo());

						System.out.println(cart_no+" : 조회 완료");

					 }
				 

}
