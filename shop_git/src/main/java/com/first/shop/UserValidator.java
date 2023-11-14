package com.first.shop;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.first.shop.dto.User;

// 매개변수로 들어온 값들에 대해 검증해준다.
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 검증하려는 객체가 유저객체인지 아닌지 확인한다.
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
