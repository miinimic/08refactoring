package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	//@RequestMapping("/addProduct")
	@RequestMapping( value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, HttpServletRequest request) throws Exception {

		System.out.println("/addProduct");
		
		productService.insertProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/addCart")
	public String addCart(@RequestParam("prodNo") int prodNo, @RequestParam("userId") String userId) throws Exception {

		System.out.println("/addCart");
		productService.addCart(prodNo, userId);

		return "redirect:/product/listProduct?menu=search";
	}
	
	//@RequestMapping("/getProduct")
	@RequestMapping( value="getProduct", method=RequestMethod.GET )
	public String getProduct(@ModelAttribute("search") Search search, @RequestParam("prodNo") int prodNo , Model model ) throws Exception {
		
		System.out.println("/getProduct");
		//Business Logic
		Product product = productService.findProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		model.addAttribute("search", search);
		
		return "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/updateProductView")
	@RequestMapping( value="updateProductView", method=RequestMethod.GET )
	public String updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateProductView");
		//Business Logic
		Product product = productService.findProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
	
		return "forward:/product/updateProductView.jsp";
	}
	
	//@RequestMapping("/updateProduct")
	@RequestMapping( value="updateProduct", method=RequestMethod.POST )
	public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/updateProduct");
		//Business Logic

		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=manage";
	}
	
	@RequestMapping("/listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 

		search.setPageSize(pageSize);
		
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);	

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct( @RequestParam("prodNo") int prodNo , @ModelAttribute("search") Search search, Model model , HttpSession session) throws Exception{

		System.out.println("/deleteProduct");
		//Business Logic
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		
		productService.deleteProduct(prodNo); 

		return "redirect:/product/listProduct?currentPage="+search.getCurrentPage()+"&menu="+search.getMenu();
	}
	
	@RequestMapping("/deleteCart")
	public String deleteCart( @RequestParam("prodNo") int prodNo , @ModelAttribute("search") Search search, Model model , HttpSession session) throws Exception{

		System.out.println("/deleteCart");
		//Business Logic
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		
		productService.deleteCart(prodNo); 

		return "redirect:/purchase/listCart?currentPage="+search.getCurrentPage();
	
	}
	
}