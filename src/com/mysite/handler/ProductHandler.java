package com.mysite.handler;

import java.util.List;

import com.mysite.exceptions.ItemNotFoundException;
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
			if (quantity == 0) {
				//throw new ItemOutOfStockException();
			}
		}
		
}
