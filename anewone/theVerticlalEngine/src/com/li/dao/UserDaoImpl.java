package com.li.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.li.pojo.UserModel;

//@Repository
public class UserDaoImpl implements UserDao{

	//ʹ��String��װ��һ����JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	/**
	   * spring�ṩ����
	   * 
	   * @param jdbcTemplate
	   *      ����ֵ���ͣ� void
	   */
	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	  }
	  
	  //�ж��Ƿ�Ϊ����
	  public boolean isNumic(String s) {
		 if(s==null||s.length()==0)
			 return false;
		 return s.chars().allMatch(Character::isDigit);
	  }
	  
	  public List<UserModel> queryByName(String uname,String upassword) {
		  System.out.println("�û���"+uname+" "+upassword);
		  
//		  if(isNumic(uname))
//			  sql="select uid,uname,upassword from user where uid="+Integer.parseInt(uname)+" or uname='"+uname+"'";
//		  else
//			  sql="select uid,uname,upassword from user where uname='"+uname+"'";
		  String sql="select uid,uname,upassword from user where uname='"+uname+"' and upassword='"+upassword+"'";
		  System.out.println("sql"+sql);
		  return jdbcTemplate.query(sql, new UserMapper());
	  }
	  public boolean addUser(String uname,String upassword) {
		  System.out.println("�û�������"+uname+upassword);
		  String sql="insert into user(uname,upassword) values(?,?)";
		  return jdbcTemplate.update(sql,
				  new Object[] {uname,upassword},
				  new int[] {Types.VARCHAR,Types.VARCHAR})==1;
	  }
	  
	  /**
	   * 
	   * UserMapper���ݿ�ӳ��
	   *
	   */
	 
	  class UserMapper implements RowMapper<UserModel> {
	 
		  @Override
		  public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			  // TODO Auto-generated method stub
			  UserModel student = new UserModel();
			  student.setUid(rs.getInt(1));
			  student.setUname(rs.getString(2));
			  student.setUpassword(rs.getString(3));
			  System.out.println("��û�ж�����"+rs.getInt(1));
	 
			  return student;
		  } 
	  }
}

