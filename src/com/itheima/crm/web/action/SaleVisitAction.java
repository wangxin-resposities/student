package com.itheima.crm.web.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	private SaleVisit sv;
	private Integer currPage=1;
	private Integer pageSize=3;
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String findAll() {
		System.out.println(sv.getVisit_time()+"  "+visit_end_time);
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		if(sv.getVisit_time()!=null) {
			criteria.add(Restrictions.ge("visit_time", sv.getVisit_time()));
		}
		if(visit_end_time!=null) {
			criteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = svs.findAll(criteria,currPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	@Autowired
	private SaleVisitService svs;
	@Override
	public SaleVisit getModel() {
		sv=new SaleVisit();
		return sv;
	}
	public String saveUI() {
		return "saveUI";
	}
	public String save() {
		svs.save(sv);
		return "save";
	}
	
}
