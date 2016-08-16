package com.mysite.handler;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mysite.model.User;

public class LoginValidator  implements Validator{

	@Override
	public boolean supports(Class<?> temp) {
		return User.class.isAssignableFrom(temp);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("class : " + obj.getClass());
		//LoginBean login= (LoginBean) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username","error.username", "enter username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","error.password", "enter password");
	}

}
