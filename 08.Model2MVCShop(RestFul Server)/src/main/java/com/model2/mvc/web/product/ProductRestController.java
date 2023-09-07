package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping("json/listProduct")
	public Map<String , Object> listProduct( @RequestBody Search search  , HttpServletRequest request) throws Exception{
		
		System.out.println("/json/listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 

		search.setPageSize(8);
		
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);	

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 5, 8);
		System.out.println(resultPage);
		
		return map;
	}
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public Product addProduct(@RequestBody Product product, HttpServletRequest request) throws Exception {

		System.out.println("json/addProduct");

		productService.insertProduct(product);
				
		return product;
	}
	
	@RequestMapping("json/deleteProduct")
	//public String deleteProduct(@RequestBody Product product , @RequestBody Search search, HttpSession session) throws Exception{
	public String deleteProduct(@RequestBody Product product , HttpSession session) throws Exception{
		System.out.println("/json/deleteProduct");
		//Business Logic
		
		int prodNo = product.getProdNo();
		
		/*if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}*/ 
		
		productService.deleteProduct(prodNo);

		return "삭제 완료";
	}
	
	@RequestMapping("json/getProduct/{prodNo}")
	public Product getProduct(@PathVariable int prodNo ) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		//Business Logic
		return productService.findProduct(prodNo);
	}
	
	@RequestMapping( value="json/addCart", method=RequestMethod.POST )
	public int addCart(@RequestBody Purchase purchase) throws Exception {

		System.out.println("/json/addCart");
		
		int prodNo = purchase.getPurchaseProd().getProdNo();
		String userId = purchase.getBuyer().getUserId();
		
		System.out.println(prodNo+": prodNo");
		System.out.println(userId+" : userId");
		
		productService.addCart(prodNo, userId);
	
		return productService.getCartNo(prodNo);
	}
	
	@RequestMapping("json/deleteCart")
	public String deleteCart( @RequestBody Purchase purchase, HttpSession session) throws Exception{
	//	public String deleteCart( @PathVariable int prodNo, @RequestBody Search search, Model model , HttpSession session) throws Exception{
		System.out.println("json/deleteCart");
		//Business Logic
		
		/*if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} */
		int prodNo = purchase.getPurchaseProd().getProdNo();
		String userId = purchase.getBuyer().getUserId();
		
		System.out.println("prodNo : "+prodNo);
		System.out.println("userId : "+userId);
		
		productService.deleteCart(prodNo, userId);

		return "삭제 완료";
	
	}
	
	@RequestMapping( value="json/updateProduct/{prodNo}", method=RequestMethod.GET )
	public Product updateProduct( @PathVariable int prodNo  ) throws Exception{

		System.out.println("/json/updateProduct : GET");
		//Business Logic
		Product product = productService.findProduct(prodNo);

		return product;
	}
	
	@RequestMapping( value="json/updateProduct", method=RequestMethod.POST )
	public Product updateProduct( @RequestBody Product product, HttpSession session) throws Exception{

		System.out.println("/json/updateProduct : POST");
		//Business Logic

		productService.updateProduct(product);
		Product result = productService.findProduct(product.getProdNo());
		
		//return "redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=manage";
		return result;
	}

}