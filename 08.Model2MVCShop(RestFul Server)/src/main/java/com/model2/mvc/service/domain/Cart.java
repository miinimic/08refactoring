package com.model2.mvc.service.domain;

public class Cart {
	
	private int cartNo;
	private int prodNo;
	private String userId;
	private Product cartProd;

	public Cart(){
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Product getCartProd() {
		return cartProd;
	}
	public void setCartProd(Product cartProd) {
		this.cartProd = cartProd;
	}

	@Override
	public String toString() {
		return "CartVO : [cartNo]" + cartNo
				+ "[prodNo]" + prodNo+ "[userId]" + userId + "[CartProd]" + cartProd;
	}	
}