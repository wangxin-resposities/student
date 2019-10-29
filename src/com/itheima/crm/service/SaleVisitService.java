package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;

public interface SaleVisitService {
	public PageBean<SaleVisit> findAll(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	public void save(SaleVisit sv);
}
