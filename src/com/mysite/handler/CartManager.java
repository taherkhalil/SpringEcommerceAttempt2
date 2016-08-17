package com.mysite.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysite.model.Products;



public class CartManager {
	private Map<Integer, Products> cartProducts;
	public CartManager(Map<Integer,Products> cartProducts){
		this.cartProducts = cartProducts;
	}

	 public CartManager(HashMap<Integer, Products> hashMap) {
	 this.cartProducts = hashMap;
	 }
	public Map<Integer, Products> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(Map<Integer, Products> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	public int addItemToCart(int pid, Products product){
		cartProducts.put(cartProducts.size() + 1,product);
		
		return cartProducts.size();
		
	}
	public int removeItemFromCart(int pid){
		Products product = cartProducts.get(pid);
		if (product != null) {
			cartProducts.remove(pid);
		}
		return cartProducts.size();
		
	}
	public int getCartSize() {
		
		return cartProducts.size();
	}
	public Integer calculatePrice(){
		Integer total = 0;
		for (int i = 1; i < (cartProducts.size() + 1); i++) {
			System.out.println();
			total += cartProducts.get(i).getPrice();
		}
		return total;
	}
	
}
