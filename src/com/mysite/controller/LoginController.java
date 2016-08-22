package com.mysite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.handler.LoginHandler;
import com.mysite.handler.LoginValidator;
import com.mysite.handler.UserHandler;
import com.mysite.model.User;

@Controller
public class LoginController {
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	UserHandler userHandler;
	@Autowired
	LoginHandler loginHandler;
	@Autowired
	User user;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome(Model model) {
		ModelAndView mav = new ModelAndView("welcome");

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String printLoginPage(ModelMap model) {

		model.addAttribute("title", "Login");
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("user") User user, HttpSession session,
			HttpServletResponse response, HttpServletRequest request, BindingResult result) {
		String toPage = "login";
		toPage = loginHandler.validateInput(model, user, result, toPage, request, response);
		return toPage;
	}

}
