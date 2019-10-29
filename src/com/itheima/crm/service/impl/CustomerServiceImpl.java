package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao cd;

	@Override
	public void save(Customer customer) {
		cd.save(customer);
	}

	@Override
	public PageBean<Customer> findAll(DetachedCriteria dc, Integer pageSize, Integer currPage) {
		PageBean<Customer> bean = new PageBean<>();
		bean.setCurrPage(currPage);
		bean.setPageSize(pageSize);
		Integer totalCount = cd.getTotalCount(dc);
		bean.setTotalCount(totalCount);
		bean.setList(cd.getList(dc,currPage,pageSize));
		
		
		
		Double d = Math.ceil(totalCount.doubleValue()/pageSize);
		bean.setTotalPage(d.intValue());
		
		return bean;
	}

	@Override
	public Customer findById(Long cust_id) {
		Customer c=cd.findById(cust_id);
		return c;
	}

	@Override
	public void delete(Customer c) {
		cd.delete(c);
	}

	@Override
	public void update(Customer customer) {
		cd.update(customer);		
	}

	@Override
	public List<Customer> findAllName() {
		
		return cd.findAllName();
	}

	@Override
	public List<Customer> findAllCustomer() {
		
		return cd.findAllCustomer();
	}

	
}
