package com.first.shop.service;

import com.first.shop.dto.User;

public interface RegisterService {
	int registerUser(User user );
	
	int idCheck(User user);
	
}
