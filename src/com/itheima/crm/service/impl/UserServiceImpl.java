package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao ud;
	@Override
	public void save(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		
		ud.save(user);
	}
	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		System.out.println(user.getUser_password());
		User u=ud.findByUsernameAndPassword(user);
		return u;
	}
	@Override
	public List<User> findAll() {
		return ud.findAll();
	}

}
