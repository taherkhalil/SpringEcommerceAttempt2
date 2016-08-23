package com.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.handler.ProductHandler;

@Controller
public class ItemController {
	@Autowired
	ProductHandler productHandler;
	Map<String, Object> myModel = new HashMap<String, Object>();

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		myModel.put("user", request.getSession().getAttribute("user"));
		
		myModel.put("products", this.productHandler.getProducts());
		myModel.put("cartSize", "" + request.getSession().getAttribute("cartSize"));
		return new ModelAndView("dashboard", "model", myModel);
	}
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public ModelAndView singleProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id) {
		Integer cartSize =(Integer) request.getSession().getAttribute("cartSize");
		myModel.put("product", this.productHandler.findProduct(id));
		myModel.put("cartSize", cartSize);
		return new ModelAndView( "moreInfo", "model", myModel);
	}
	
}
