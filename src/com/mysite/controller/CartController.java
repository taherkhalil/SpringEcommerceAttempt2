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
	@Autowired
	ItemOutOfStockException itemOutOfStockException;

	CartManager cartManager;
	Map<String, Object> myModel = new HashMap<String, Object>();
	Set<Order> orders = new HashSet<Order>();


	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addItem(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer id) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Products product = productHandler.findProduct(id);
		if (product.getQuantity() <= 0) {
			myModel.put("products", this.productHandler.findProduct(id));
			System.out.println("out of stock");
			return new ModelAndView("outofstock", "model", myModel);
		}
		int quant = cartManager.addItemToCart(id, product);
		Integer cartSize =(Integer) req.getSession().getAttribute("cartSize");
		
		
		product.setSelected(quant);
		req.getSession(false).setAttribute("cartSize", ++cartSize);
		System.out.println( "cart size ="+req.getSession().getAttribute("cartSize"));
		productHandler.reduceQuantity(product);

		req.getSession(false).setAttribute("cartList", cartManager);

		System.out.println("current quantity" + quant);
		myModel.put("cartSize", cartSize);
		myModel.put("quantity", quant);
		myModel.put("products", this.productHandler.getProducts());
		return new ModelAndView("dashboard", "model", myModel);
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public ModelAndView cartView(HttpServletRequest req, HttpServletResponse res) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");

		orders = productHandler.removeDuplicateProducts(cartManager.getCartProducts());
		Integer cartSize =(Integer) req.getSession().getAttribute("cartSize");
		myModel.put("cartSize", cartSize);
		myModel.put("orders", orders);
		myModel.put("products", cartManager.getCartProducts());
		return new ModelAndView("viewCart", "model", myModel);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView removeItem(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam Integer id) {
		
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
		Integer cartSize =(Integer) req.getSession().getAttribute("cartSize");
		req.getSession(false).setAttribute("cartSize", --cartSize);
		Products product2 = cartManager.getCartProducts().get(id);
		int quant = cartManager.removeItemFromCart(id);
		Products product = productHandler.findProduct(product2.getId());
		product.setSelected(quant);
		productHandler.increaseQuantity(product);
		req.getSession(false).setAttribute("quantity", quant);
		req.getSession(false).setAttribute("cartList", cartManager);

		orders = productHandler.removeDuplicateProducts(cartManager.getCartProducts());
		myModel.put("orders", orders);
		System.out.println(req.getSession(false).getAttribute("cartList"));
		myModel.put("products", cartManager.getCartProducts());
		myModel.put("cartSize", cartSize);

	
		return new ModelAndView("viewCart", "model", myModel);
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("order") Order order) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");

		System.out.println("checkout called");
		System.out.println(req.getSession(false).getAttribute("cartList"));
		orders = productHandler.removeDuplicateProducts(cartManager.getCartProducts());
		System.out.println(orders);
		Double price = cartManager.calculatePrice(orders);
		myModel.put("products", cartManager.getCartProducts());
		myModel.put("orders", orders);
		myModel.put("total", price);
		return new ModelAndView("checkout", "model", myModel);
	}

	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public String placeOrder(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");

		req.getSession(false).setAttribute("cartList", new CartManager(new HashMap<Integer, Products>()));
		req.getSession(false).setAttribute("cartSize", 0);
		return "place";
	}

}
