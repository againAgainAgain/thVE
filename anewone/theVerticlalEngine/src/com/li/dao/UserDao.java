package com.li.dao;

import java.util.List;

import com.li.pojo.UserModel;

public interface UserDao {
	public List<UserModel> queryByName(String uname,String upassword);	//ͨ�����ֲ���
	public boolean addUser(String uname,String upassword);		//���û�ע��

}
