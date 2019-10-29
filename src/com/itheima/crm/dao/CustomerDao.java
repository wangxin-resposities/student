package com.itheima.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	List findAllName();

	List<Customer> findAllCustomer();

	

	

	

}
