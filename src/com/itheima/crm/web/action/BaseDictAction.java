package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
@Scope("prototype")
@Controller
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{
	private BaseDict bd;
	@Autowired
	private BaseDictService bs;
	public String findByTypeCode() throws IOException {
		List<BaseDict> list= bs.findByTypeCode(bd.getDict_type_code());
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray ja=JSONArray.fromObject(list, jc);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(ja);
		return "none";
	}
	@Override
	public BaseDict getModel() {
		bd=new BaseDict();
		return bd;
	}
}
