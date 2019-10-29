package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
@Service
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService{
	@Autowired
	private SaleVisitDao sd;

	@Override
	public PageBean<SaleVisit> findAll(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// ���õ�ǰҳ��:
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ��¼��:
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = sd.getTotalCount(criteria);
		pageBean.setTotalCount(totalCount);
		// ������ҳ����
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = sd.getList(criteria, pageSize, currPage);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit sv) {
		sd.save(sv);
	}
	
}
