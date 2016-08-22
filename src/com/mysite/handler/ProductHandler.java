package com.mysite.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;

import com.mysite.exceptions.ItemNotFoundException;
import com.mysite.exceptions.ItemOutOfStockException;
import com.mysite.model.Order;
import com.mysite.model.Products;

public class ProductHandler {
	@Autowired
	ItemOutOfStockException itemOutOfStockException;
	@Autowired
	ItemNotFoundException itemNotFoundException;
	@Autowired
	Order order;
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
			throw  itemNotFoundException;
			
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
		
		public  Set<Order> removeDuplicateProducts(Map<Integer,Products> cartItems) {
			Set<Order> orders = new HashSet<Order>();
			Order order = null;
			
			for (Products prod: cartItems.values()) {
				
				int quantity =prod.getSelected();
				Integer price = prod.getPrice()* quantity;
				order = new Order (prod, quantity, price);
				orders.add(order);
			}
			System.out.println(orders.toString());
			
			
			return orders;
		}
		
}
