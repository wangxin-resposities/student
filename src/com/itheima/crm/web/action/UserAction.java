package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
@Scope("prototype")
@Controller
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user;
	@Autowired
	private UserService us;
	@Override
	public User getModel() {
		user=new User();
		return user;
	}
	public String regist() {	
		us.save(user);
		return "regist";
	}
	public String login() {
		User u = us.login(user);
		System.out.println(u);
		if(u!=null) {
			ActionContext.getContext().getSession().put("existUser", u);
			return "login";
		}else {
			this.addFieldError("error", "–≈œ¢”–¥ÌŒÛ");
			return "regist";
		}
	}
	public String findAllUser() throws IOException{
		List<User> list = us.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
