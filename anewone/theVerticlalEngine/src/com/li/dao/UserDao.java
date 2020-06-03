package com.li.dao;

import java.util.List;

import com.li.pojo.UserModel;

public interface UserDao {
	public List<UserModel> queryByName(String uname,String upassword);	//通过名字查找
	public boolean addUser(String uname,String upassword);		//新用户注册

}
