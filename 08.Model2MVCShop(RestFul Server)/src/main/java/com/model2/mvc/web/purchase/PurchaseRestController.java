package com.model2.mvc.web.purchase;

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
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public PurchaseRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping("json/listReview")
	public Map<String,Object> listReview( @RequestBody Search search , HttpServletRequest request) throws Exception{ 
		
		System.out.println("/json/listReview");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		
		search.setPageSize(8);
		
		// Business logic 수행
		Map<String,Object> map=purchaseService.getReviewList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 5, 8);
		System.out.println(resultPage);

		return map;
	
	}
	
	@RequestMapping( value="json/getPurchase/{tranNo}", method=RequestMethod.GET )
	public Map<String , Object> getPurchase( @PathVariable int tranNo ) throws Exception {
		
		System.out.println("/json/getPurchase");
		
		System.out.println(tranNo+" : tranNo");
		
		Map<String , Object> result = purchaseService.findPurchase(tranNo);

		return result;
	}
	
	@RequestMapping(value="json/addPurchase", method=RequestMethod.POST)
	public Purchase addPurchase(@RequestBody Purchase purchase, HttpServletRequest request) throws Exception {

		System.out.println("json/addPurchase");
		System.out.println(purchase+" : purchase!!");

		purchaseService.insertPurchase(purchase);
					
		return purchase;
	}
	@RequestMapping( value="json/addPurchaseView/{prodNo}/{userId}", method=RequestMethod.GET )
	public Purchase addPurchaseView(@PathVariable int prodNo, @PathVariable String userId) throws Exception {

		System.out.println("json/addPurchaseView");
		
		System.out.println("prodno : "+prodNo+"  userId : "+userId);
		
		Product product = productService.findProduct(prodNo);
		User user = userService.getUser(userId);
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		System.out.println("purchase : "+purchase);

		return purchase;
	}

	}
