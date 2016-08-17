package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.mysite.model.User;


@Controller
public class LogoutController {
	@Autowired
	User user ;
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String printLoginPage(ModelMap model) {

	
		model.addAttribute("title", "Login");
		model.addAttribute("user", user);
		return "login";
	}
}
