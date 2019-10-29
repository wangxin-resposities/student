package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
@Repository
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao{
	@Autowired
	public void setSessionFactory2(SessionFactory sf){
		this.setSessionFactory(sf);
	}

	@Override
	public List<BaseDict> findByTypeCode(String s) {
		
		return (List<BaseDict>) getHibernateTemplate().find("from BaseDict where dict_type_code= ?", s);
	}
}
