package com.itheima.crm.service;

import java.util.List;

import com.itheima.crm.domain.User;

public interface UserService {

	void save(User user);

	User login(User user);

	List<User> findAll();

}
