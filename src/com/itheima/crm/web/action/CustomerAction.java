package com.itheima.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer;
	private Integer currPage = 1;
	private File file1;
	
	private String file1FileName;
	
	private String file1ContentType;
	
	
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public String getFile1FileName() {
		return file1FileName;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public String getFile1ContentType() {
		return file1ContentType;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// 使用set方法接受每页显示记录数
	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	@Autowired
	private CustomerService cs;
	@Override
	public Customer getModel() {
		customer=new Customer();
		return customer;
	}
	public String saveUI() {
		return "saveUI";
	}
	public String edit() {
		Customer c = cs.findById(customer.getCust_id());
		customer.setCust_id(c.getCust_id());
		customer.setCust_image(c.getCust_image());
		customer.setCust_industry(c.getCust_industry());
		customer.setCust_level(c.getCust_level());
		customer.setCust_mobile(c.getCust_mobile());
		customer.setCust_source(c.getCust_source());
		customer.setCust_name(c.getCust_name());
		customer.setCust_phone(c.getCust_phone());
		return "edit";
	}
	public String save() throws IOException {
		if(file1!=null) {
			String path = "C:/upload";
			String name = UploadUtils.getUuidFileName(file1FileName);
			path= path+UploadUtils.getPath(name)+name;
			FileUtils.copyFile(file1,new File(path) );
			customer.setCust_image(path);
			cs.save(customer);
		}
		
		return "list";
	}
	public String findAll() {
		
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		if(customer.getCust_name()!=null) {
			dc.add(Restrictions.like("cust_name","%"+ customer.getCust_name()+"%"));
		}
		if(customer.getCust_level()!=null&&customer.getCust_level().getDict_id()!=null&&!customer.getCust_level().getDict_id().equals("")) {
			System.out.println(customer.getCust_level().getDict_id());
			dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		if(customer.getCust_source()!=null&&customer.getCust_source().getDict_id()!=null&&!customer.getCust_source().getDict_id().equals("")) {
			dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		if(customer.getCust_industry()!=null&&customer.getCust_industry().getDict_id()!=null&&!customer.getCust_industry().getDict_id().equals("")) {
			dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		PageBean<Customer> customers=cs.findAll(dc,currPage,pageSize);
		
		System.out.println(customers);
		ActionContext.getContext().getValueStack().push(customers);
		return "findAll";
	}
	public String delete() {
		Customer c=cs.findById(customer.getCust_id());
		File file = new File(c.getCust_image());
		file.delete();
		cs.delete(c);
		return "delete";
	}
	public String update() throws IOException {
		if(file1!=null) {
			File file=new File(customer.getCust_image());
			file.delete();
			String path = UploadUtils.getPath(file1FileName);
			String name=UploadUtils.getUuidFileName(file1FileName);
			path="c:/upload"+path+name;
			FileUtils.copyFile(file1, new File(path));
			customer.setCust_image(path);
		}
		cs.update(customer);
		return "update";
	}
	public String findAllUser() throws IOException{
		List<Customer> list=cs.findAllCustomer();
		JsonConfig conf=new JsonConfig();
		conf.setExcludes(new String[]{"cust_mobile","cust_industry","cust_phone"
				,"cust_source","cust_level","cust_image","linkMans"});
		JSONArray ja=JSONArray.fromObject(list, conf);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(ja.toString());
		return "none";
	}
}
