package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.handler.UserHandler;
import com.mysite.model.User;

@Controller
public class LoginController {
	
	@Autowired
	UserHandler user;
	@RequestMapping(value={"/","/welcome"},method=RequestMethod.GET)
	public ModelAndView welcome(Model model){
		ModelAndView mav=new ModelAndView("welcome");
	
		return  mav;
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model,@ModelAttribute("User")User user){
		
		return "login";
	}

}
