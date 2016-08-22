package com.mysite.handler;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysite.model.Products;
import com.mysite.model.User;

public class LoginHandler {
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	UserHandler userHandler;

	CartManager cartManager;
	RedirectAttributes red;

	public String validateInput(ModelMap model, User user, BindingResult result, String toPage,
			HttpServletRequest request, HttpServletResponse response) {
		loginValidator.validate(user, result);
		toPage = authenticateUser(model, user, result, toPage, request, response);
		return toPage;
	}

	private String authenticateUser(ModelMap model, User user, BindingResult result, String url,
			HttpServletRequest request, HttpServletResponse response) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			url = "login";
		} else if (this.userHandler.getUsers().contains(user)) {
			request.getSession().setAttribute("user", user);
			url = "redirect:/dashboard";
			startSession(user, request, response);
		} else {
			model.addAttribute("error", "invalidUser");
		}
		return url;
	}

	private void startSession(User user, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		session.setAttribute("username", user.getUsername());
		session.setAttribute("sessID", session.getId());
		session.setMaxInactiveInterval(1000);
		Cookie cookie = new Cookie("sessID", session.getId());
		cookie.setMaxAge(10000);
		response.addCookie(cookie);
		session.setAttribute("cartList", new CartManager(new HashMap<Integer, Products>()));
		session.setAttribute("cartSize", new Integer(0));
	}

}
