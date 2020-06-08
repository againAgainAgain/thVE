package com.li.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.dao.UserDao;
import com.li.pojo.UserModel;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public List<UserModel> getName(String name,String password)throws Exception{
		return userDao.queryByName(name,password);
	}
	public boolean add_user(String name,String password)throws Exception{
		return userDao.addUser(name, password);
	}
}

