package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{ 
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::"+getClass()+".setSqlSession() Call....");
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
		public void insertProduct(Product product) throws Exception {

			product.setManuDate(product.getManuDate().replace(String.valueOf('-'), ""));
			
			sqlSession.insert("ProductMapper.insertProduct", product);
		}
		
		public List<Product> getProductList(Search search) throws Exception {
			
			return sqlSession.selectList("ProductMapper.getProductList", search);

		}
		
		public List<Product> getSaleList(Search search) throws Exception {
			
			return sqlSession.selectList("ProductMapper.getSaleList", search);

		}
		
		public Product findProduct(int prodNo) throws Exception {
			return sqlSession.selectOne("ProductMapper.findProduct", prodNo);
		}
		
		public int getCartNo(int prodNo) throws Exception{
			return sqlSession.selectOne("ProductMapper.getCartNo", prodNo);
		}
		
		public void updateProduct(Product product) throws Exception {
			System.out.println(product.getProdNo());
			System.out.println(product.getProdDetail());
			System.out.println(product.getManuDate());
			System.out.println(product.getPrice());
			System.out.println(product.getFileName());
			System.out.println(product.getCategory());
			System.out.println(product.getProdName());
			System.out.println(product.getItem());

			//product.setManuDate(product.getManuDate().replace(String.valueOf('-'), ""));
			sqlSession.update("ProductMapper.updateProduct", product);
		}
		
		public void deleteProduct(int prodNo) throws Exception{
			sqlSession.delete("ProductMapper.deleteProduct", prodNo);
		}
		
		public void deleteCart(int prodNo) throws Exception{
			sqlSession.delete("ProductMapper.deleteCart", prodNo);
		}
		
		public void addCart(int prodNo, String userId) throws Exception{
			Map<String , Object>  map = new HashMap<String, Object>();
			
			map.put("prodNo", prodNo);
			map.put("userId", userId);
		
			System.out.println(map);
			
			sqlSession.insert("ProductMapper.insertCart", map);

		}
		

		
		// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
		public int getTotalCount(Search search) throws Exception {
			return sqlSession.selectOne("ProductMapper.getTotalCount", search);
		}
		public int getTotalCountSale(Search search) throws Exception {
			return sqlSession.selectOne("ProductMapper.getTotalCountSale", search);
		}

}
