package com.itheima.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;

public interface BaseDao<T> {
	void delete(T t);
	void save(T t);
	void update(T t);
	
	Integer getTotalCount(DetachedCriteria dc);

	List<T> getList(DetachedCriteria dc, Integer currPage, Integer pageSize);

	T findById(Serializable cust_id);

	

	
}
