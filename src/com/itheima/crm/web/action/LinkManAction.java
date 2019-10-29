package com.itheima.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
@Controller
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>,Preparable {
	private LinkMan linkMan=new LinkMan();;
	private Integer currPage= 1;	
	private Integer pageSize= 3;
	@Override
	public LinkMan getModel() {
		
		return linkMan;
	}
	private Long lkm_id;
	
	
	
	public Long getLkm_id() {
		return lkm_id;
	}
	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		System.out.println(1111);
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	public String saveUI() {
		List<Customer> list=cs.findAllName();
		
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	public String save() {
		
		ls.save(linkMan);
		System.out.println(linkMan);
		return "save";
	}
	@Autowired
	private LinkManService ls;
	@Autowired
	private CustomerService cs;
	public String findAll() {
		
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkm_name()!=null&&!linkMan.getLkm_name().equals("")) {
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null&&!linkMan.getLkm_gender().equals("")) {
			criteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pageBean=ls.findAll(criteria,pageSize,currPage);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String edit() {
		
		List<Customer> list=cs.findAllName();
		ActionContext.getContext().getValueStack().set("list", list);
		return "edit";
	}
	public void prepareEdit(){
		System.out.println(lkm_id);
		linkMan=ls.findById(lkm_id);
	}

	public String update() {
		ls.update(linkMan);
		return "update";
	}
	public String delete() {
		ls.delete(lkm_id);
		return "delete";
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
