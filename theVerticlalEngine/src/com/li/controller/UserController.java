package com.li.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.li.pojo.UserModel;
import com.li.service.UserService;
import com.li.service.VeService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("login_view.action")
	public String login() {
		return "login";
	}
	
	@RequestMapping("login1_view.action")
	public String login1() {
		return "login";
	}
	
	@RequestMapping("login.action")
	public String queryByName(String uname,String upassword, Model model)throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		List<UserModel> users=(List<UserModel>)userService.getName(uname, upassword);
		if(users.size()==0) {
			//�޴��û�
			model.addAttribute("message","<script>alert('�޴��û�����ע��')</script>");
		}
		else if(users.get(0).getUpassword().equals(upassword)) {
			 //�д��û�
			model.addAttribute("uname", uname);
			return "index";
		}
		else {
			 //�д��û����������벻��
			model.addAttribute("message","<script>alert('�û��������벻��ȷ')</script>");
		}
		return "login";
	}
	
	@RequestMapping("register.action")
	public String addUsers(String uname,String upassword,Model model)throws Exception{
//		if(!upassword.equals(upassword1)) {
//			model.addAttribute("message","<script>alert('2���������벻һ��')</script>");
//		}
//		else {	����ǰ�˽����ж�
			
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		boolean result=userService.add_user(uname, upassword);
		if(result)
			model.addAttribute("message","<script>alert('ע��ɹ������¼')</script>");
		else 
			model.addAttribute("message","<script>alert('ע��ʧ��')</script>");
		return "login";
	}
}
