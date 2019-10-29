package com.itheima.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		System.out.println("aaa");
		ActionSupport as=(ActionSupport)arg0.getAction();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null) {
			return arg0.invoke();
		}else {
			as.addActionError("Ã»ÓÐµÇÂ½");
			return "login";
		}
		
		
	}

}
