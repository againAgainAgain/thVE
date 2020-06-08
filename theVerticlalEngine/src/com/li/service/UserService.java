package com.li.service;

import java.util.List;

import com.li.pojo.UserModel;

public interface UserService {

	public List<UserModel> getName(String name,String password)throws Exception;
	public boolean add_user(String name,String password)throws Exception;
}
