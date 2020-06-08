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
			//无此用户
			model.addAttribute("message","<script>alert('无此用户，请注册')</script>");
		}
		else if(users.get(0).getUpassword().equals(upassword)) {
			 //有此用户
			model.addAttribute("uname", uname);
			return "index";
		}
		else {
			 //有此用户，但是密码不对
			model.addAttribute("message","<script>alert('用户名或密码不正确')</script>");
		}
		return "login";
	}
	
	@RequestMapping("register.action")
	public String addUsers(String uname,String upassword,Model model)throws Exception{
//		if(!upassword.equals(upassword1)) {
//			model.addAttribute("message","<script>alert('2次密码输入不一致')</script>");
//		}
//		else {	改在前端进行判断
			
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		boolean result=userService.add_user(uname, upassword);
		if(result)
			model.addAttribute("message","<script>alert('注册成功，请登录')</script>");
		else 
			model.addAttribute("message","<script>alert('注册失败')</script>");
		return "login";
	}
}
