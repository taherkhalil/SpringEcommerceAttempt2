package com.mysite.handler;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysite.model.Order;
import com.mysite.model.Products;



public class CartManager {
	@Autowired
	Order order;
	@Override
	public String toString() {
		return "CartManager [cartProducts=" + cartProducts + "] \n";
	}
	Map<Integer,Integer> quantsmap =new HashMap<Integer, Integer>();
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

		if(cartProducts.containsKey(pid)){
			Integer currentQuantity = quantsmap.get(pid);
			quantsmap.put(pid, ++currentQuantity);

		System.out.println("already in cart");
				}
		else {
			System.out.println(pid);
			cartProducts.put(pid,product);
			quantsmap.put(pid, new Integer(1));
		}	
		return quantsmap.get(pid);
		
	}
	public Map<Integer, Integer> getQuantsmap() {
		return quantsmap;
	}

	public void setQuantsmap(Map<Integer, Integer> quantsmap) {
		this.quantsmap = quantsmap;
	}

	public int removeItemFromCart(int pid){
		Products product = cartProducts.get(pid);
		Integer currentQuantity = quantsmap.get(pid);
		if (product != null) {
			if(quantsmap.get(pid)>1){
				
				quantsmap.put(pid, --currentQuantity);
			}
			else
			cartProducts.remove(pid);
		}
		return quantsmap.get(pid);
		
	}
	public int getCartSize() {
		
		return cartProducts.size();
	}
	public Double calculatePrice(Set<Order> orders){
		Double total = 0.0;

		
		for(Order order : orders) {
			total += order.getPrice() ;
		}
		return total;

	
	}
	
}
