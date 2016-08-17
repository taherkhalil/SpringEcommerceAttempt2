package com.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.handler.CartManager;

@Controller
public class CheckOutController {
//	
//	CartManager cartManager;
//	@RequestMapping (value = "/checkout", method = RequestMethod.GET)
//	public ModelAndView checkout (ModelMap model, 
//			HttpServletRequest req, HttpServletResponse res) {
//		cartManager = (CartManager) req.getSession(false).getAttribute("cartList");
//		Integer price = cartManager.calculatePrice();
//		Map<String, Object> myModel = new HashMap<String, Object>();
//		myModel.put("products", cartManager.getCartProducts());
//		myModel.put("total", price);
//		
//		return new ModelAndView( "checkout", "model", myModel);
//	}

}
