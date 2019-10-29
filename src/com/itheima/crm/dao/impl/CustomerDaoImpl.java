package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	
	@Override
	public List<Customer> findAllName() {
		List<Customer> list = (List<Customer>) getHibernateTemplate().find("from Customer ");
		return list;
	}

	@Override
	public List<Customer> findAllCustomer() {
		
		return (List<Customer>) getHibernateTemplate().find("from Customer");
	}
}
