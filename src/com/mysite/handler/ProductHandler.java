package com.mysite.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
			//req.getSession(false).setAttribute("orders", orders);
			
			return orders;
		}
		
}
