package com.mysite.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mysite.exceptions.ItemNotFoundException;
import com.mysite.model.Order;
import com.mysite.model.Products;

public class ProductHandler {

		private List<Products> products;
		

		public List<Products> getProducts() {	
			return products;
		}
		
		public void setProducts(List<Products> products) {
		      this.products = products;
		}
		
	
		public Products findProduct(int pid){
			for (Products product : products) {
				if (product.getId() == pid) {
					return product;
				}
			}
			throw new ItemNotFoundException();
			
		}

	
		public void reduceQuantity(Products tempProduct) {
			Integer quantity = tempProduct.getQuantity(); 
			
			for (int i=0; i < products.size(); i++) {	
				if (products.get(i).getId() == tempProduct.getId() ) {
					products.get(i).setQuantity(--quantity);
				}
			}
		
		}
		public void increaseQuantity(Products tempProduct) {
			Integer quantity = tempProduct.getQuantity(); 
			
			for (int i=0; i < products.size(); i++) {	
				if (products.get(i).getId() == tempProduct.getId() ) {
					products.get(i).setQuantity(++quantity);
				}
			}
		}
		
		public static Set<Order> removeDuplicateProducts(Map<Integer,Products> cartItems) {
			Set<Order> orders = new HashSet<Order>();
			Order order = null;
			
			for (Products prod: cartItems.values()) {
				
				int quantity = Collections.frequency(cartItems.values(), prod);
				double price = prod.getPrice()*quantity;
				order = new Order (prod, quantity, price);
				orders.add(order);
			}
			System.out.println("the quantity of a product is:" + order.getQuantity());
			return orders;
		}
		
}
