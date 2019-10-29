package com.itheima.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.BaseDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	Class clazz;
	public BaseDaoImpl() {
		Class c=this.getClass();
		Type type = c.getGenericSuperclass();
		ParameterizedType pt=(ParameterizedType)type;
		Type[] types = pt.getActualTypeArguments();
		this.clazz=(Class) types[0];
	}
	@Autowired
	public void setSessionFactory2(SessionFactory sf){
		this.setSessionFactory(sf);
	}
	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);		
	}
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list =  (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		
		return (list.get(0)).intValue();
	}

	@Override
	public List<T> getList(DetachedCriteria criteria,  Integer pageSize,Integer currPage) {
		criteria.setProjection(null);
		
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(criteria, pageSize*(currPage-1),pageSize);
		System.out.println(list);
		return list;
	}
	
	@Override
	public T findById(Serializable lkm_id) {
		return (T) getHibernateTemplate().get(clazz,lkm_id);
	}
	
}
