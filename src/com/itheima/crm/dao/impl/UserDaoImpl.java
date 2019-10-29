/**
 * 
 */
package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;

/**
 * @author 86133
 *
 */
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Autowired
	public void setSessionFactory1(SessionFactory sf) {
		this.setSessionFactory(sf);
	}

	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
		
	}

	@Override
	public User findByUsernameAndPassword(User user) {
		List<User> list = (List<User>) getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		System.out.println(list);
		if(list.size()==0) {
			return null;
		}else {
			return list.get(0);
		}
		
		
	}

	@Override
	public List<User> findAll() {
		
		return (List<User>) getHibernateTemplate().find("from User");
	}
	
}
