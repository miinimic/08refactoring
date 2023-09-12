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
	@RequestMapping("json/listPurchase")
	public Map<String , Object> listPurchase( @RequestBody Search search ,  @PathVariable String userId, HttpServletRequest request) throws Exception{
		
		System.out.println("/json/listPurchase");
		
		/*HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
		String buyerId = user.getUserId();	*/
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		String buyerId = userId;

		search.setPageSize(8);
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, buyerId);	
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 5, 8);
		System.out.println(resultPage);
	
		return map;
		
	}
	@RequestMapping( value="json/addReviewView/{tranCode}/{prodNo}/{tranNo}", method=RequestMethod.GET )
	public Map<String, Object> addReviewView( @PathVariable String tranCode,  @PathVariable int prodNo,  @PathVariable int tranNo) throws Exception {

		
		System.out.println("json/addReviewView");
		System.out.println("tranCode"+tranCode+"prodNo"+prodNo+"tranNo"+tranNo);		
		
		Map<String, Object> purchase=purchaseService.findPurchase(tranNo);
		Product product = productService.findProduct(prodNo);
		
		return purchase;

	}
	
	@RequestMapping( value="json/addReview/{tranNo}/{review}", method=RequestMethod.GET)
	public Map<String, Object> addReview(@PathVariable int tranNo, @PathVariable String review) throws Exception {

		System.out.println(tranNo + " : tranNo");
		
		System.out.println("json/addReview");
		
	/*	String review = request.getParameter("review");*/
		//String review = "후기!";
		System.out.println("review : "+review);
		
		purchaseService.addReview(tranNo, review);
		
		String tranCode = "6";
		purchaseService.updateTranCode(tranNo, tranCode);
		
		Map<String, Object> result=purchaseService.findPurchase(tranNo);
		
		return result;
		
	}
	
	@RequestMapping("json/listCart")
	public Map<String , Object> listCart( @RequestBody Search search , HttpServletRequest request) throws Exception{
		
		/*HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		String userId = user.getUserId();*/
		String userId="user01";
		System.out.println("json/listCart");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		
		search.setPageSize(8);
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getCartList(search, userId);	
		
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
	
	@RequestMapping( value="json/updateReviewView/{tranNo}", method=RequestMethod.GET )
	public Map<String , Object> updateReviewView( @PathVariable int tranNo ) throws Exception{

		System.out.println("json/updateReviewView");
		//Business Logic
		Map<String , Object> purchase = purchaseService.findPurchase(tranNo);
		// Model 과 View 연결
	
		return purchase;
	}
	
	@RequestMapping("json/deleteReview/{tranNo}")
	public String deleteReview( @PathVariable int tranNo , @RequestBody Search search) throws Exception{

		System.out.println("json/deleteReview");
		//Business Logic
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		
		purchaseService.deleteReview(tranNo);
		
		String tranCode = "5";
		purchaseService.updateTranCode(tranNo, tranCode);

		return "삭제완료";
	
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

	@RequestMapping("json/updateTranCode/{tranNo}/{tranCode}")
	//public String updateTranCode( @RequestBody Search search, @PathVariable int tranNo, @PathVariable String tranCode) throws Exception{
	public String updateTranCode( @PathVariable int tranNo, @PathVariable String tranCode) throws Exception{
		System.out.println("json/updateTranCode");
		//Business Logic

		System.out.println("tranCode : "+tranCode);
		System.out.println("tranNo : "+tranNo);
		
		Search search = new Search();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 

		purchaseService.updateTranCode(tranNo, tranCode);

		/*	if( search.getMenu().equals("manage")) {
				return "forward:/product/listProduct?page="+search.getCurrentPage();
			} else {
				return "forward:/purchase/listPurchase?page="+search.getCurrentPage();
			}*/
		return "update 완료";
	}

	}
