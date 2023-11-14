package com.first.shop.dao;

import com.first.shop.dto.User;

public interface RegisterDao {
	
	public int register(User user);
	
	public int check(User user);
}
