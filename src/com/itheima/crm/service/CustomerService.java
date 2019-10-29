package com.itheima.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;


public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findAll(DetachedCriteria dc, Integer pageSize, Integer currPage);

	Customer findById(Long cust_id);

	void delete(Customer c);

	void update(Customer customer);

	List<Customer> findAllName();

	List<Customer> findAllCustomer();

}
