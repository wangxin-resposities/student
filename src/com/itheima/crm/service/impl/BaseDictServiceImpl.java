package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictDao bd;

	@Override
	public List<BaseDict> findByTypeCode(String s) {
		
		return bd.findByTypeCode(s);
	}
}
