package com.itheima.crm.dao;

import java.util.List;

import com.itheima.crm.domain.User;

public interface UserDao {

	void save(User user);

	User findByUsernameAndPassword(User user);

	List<User> findAll();

}
