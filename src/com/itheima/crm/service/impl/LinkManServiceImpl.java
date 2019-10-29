package com.itheima.crm.service.impl;

import java.lang.invoke.MethodHandle;

import java.lang.invoke.MethodType;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;


@Transactional
@Service
public class LinkManServiceImpl implements LinkManService {
	@Autowired
	private LinkManDao dao;

	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria criteria,  Integer pageSize,Integer currPage) {
		PageBean<LinkMan> bean = new PageBean<>();
		bean.setCurrPage(currPage);
		bean.setPageSize(pageSize);
		Integer totalCount = dao.getTotalCount(criteria);
		System.out.println(totalCount);
		bean.setTotalCount(totalCount);
		bean.setList(dao.getList(criteria,pageSize,currPage));
		
		
		
		Double d = Math.ceil(totalCount.doubleValue()/pageSize);
		bean.setTotalPage(d.intValue());
		System.out.println();
		return bean;
	}

	@Override
	public void save(LinkMan linkMan) {
		dao.save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
			
		return dao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		dao.update(linkMan);
	}

	@Override
	public void delete(Long lkm_id) {
		LinkMan linkMan= new LinkMan();
		linkMan.setLkm_id(lkm_id);
		dao.delete(linkMan);
	}
	

}
