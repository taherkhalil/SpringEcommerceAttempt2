package com.mysite.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.exceptions.ItemOutOfStockException;
import com.mysite.handler.CartManager;
import com.mysite.handler.ProductHandler;
import com.mysite.model.Order;
import com.mysite.model.Products;

@Controller
public class CartController {

	@Autowired
	ProductHandler productHandler;
	@Autowired
	Order order;
	CartManager cartManager;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addItem(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer id) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Products product = productHandler.findProduct(id);
		Map<String, Object> myModel = new HashMap<String, Object>();
		if (product.getQuantity() <= 0) {
			System.out.println("out of stock");
			throw new ItemOutOfStockException();

		}

		int cartSize = cartManager.addItemToCart(id, product);
		product.setSelected(cartSize);
		req.getSession(false).setAttribute("cartSize", cartSize);
		productHandler.reduceQuantity(product);
		System.out.println("add:" + cartSize);
		String response = "" + cartSize;
		req.getSession(false).setAttribute("cartList", cartManager);
System.out.println("current quantity" + cartSize);

		// System.out.println(product + "added to cart");
		System.out.println(req.getAttribute("cartList"));
		//System.out.println(req.getSession(false).getAttribute("cartList"));
		myModel.put("quantity", cartSize);
		myModel.put("products", this.productHandler.getProducts());
		return new ModelAndView("dashboard", "model", myModel);
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public ModelAndView cartView(HttpServletRequest req, HttpServletResponse res/*,@ModelAttribute("order")Order order*/) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Map<String, Object> myModel = new HashMap<String, Object>();
		Set<Order> orders = new HashSet<Order>();
		orders=productHandler.removeDuplicateProducts(cartManager.getCartProducts());
		//System.out.println("orders = " +order);
		//System.out.println(orders.contains(order.getProduct().getName()));
		
		myModel.put("orders", orders);
		myModel.put("products", cartManager.getCartProducts());
		return new ModelAndView("viewCart", "model", myModel);

	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView removeItem(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer id) {
		//Products product = productHandler.findProduct(id);
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Products product2 = cartManager.getCartProducts().get(id);
		int cartSize = cartManager.removeItemFromCart(id);
		
		Products product = productHandler.findProduct(product2.getId());
		product.setSelected(cartSize);
	
		productHandler.increaseQuantity(product);
		
		
				req.getSession(false).setAttribute("cartSize", cartSize);
		req.getSession(false).setAttribute("cartList", cartManager);
		Map<String, Object> myModel = new HashMap<String, Object>();
		Set<Order> orders = new HashSet<Order>();
		orders=productHandler.removeDuplicateProducts(cartManager.getCartProducts());
	//System.out.println(orders.contains(order.getProduct().getName()));
		myModel.put("orders", orders);
		// System.out.println(req.getAttribute("cartList").toString());
		System.out.println(req.getSession(false).getAttribute("cartList"));

		myModel.put("products", cartManager.getCartProducts());
		myModel.put("cartSize", cartSize);

		System.out.println("remove:" + cartSize);
		return new ModelAndView("viewCart", "model", myModel);
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(ModelMap model, HttpServletRequest req, HttpServletResponse res,@ModelAttribute("order") Order order) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Set<Order> orders = new HashSet<Order>();
		System.out.println("checkout called");
		System.out.println(req.getSession(false).getAttribute("cartList"));

		orders=productHandler.removeDuplicateProducts(cartManager.getCartProducts());
		//order=(Order) req.getSession().getAttribute("orders");
		System.out.println(orders);
	
		
		
		Double price = cartManager.calculatePrice(orders);
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("products", cartManager.getCartProducts());
		myModel.put("orders", orders);
		myModel.put("total", price);

		return new ModelAndView("checkout", "model", myModel);
	}

	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String placeOrder(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");

		req.getSession(false).setAttribute("cartList", new CartManager(new HashMap<Integer, Products>()));

		return "place";
	}

}
