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

	//使用String封装的一个类JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	/**
	   * spring提供的类
	   * 
	   * @param jdbcTemplate
	   *      返回值类型： void
	   */
	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	  }
	  
	  //判断是否为数字
	  public boolean isNumic(String s) {
		 if(s==null||s.length()==0)
			 return false;
		 return s.chars().allMatch(Character::isDigit);
	  }
	  
	  public List<UserModel> queryByName(String uname,String upassword) {
		  System.out.println("用户名"+uname+" "+upassword);
		  
//		  if(isNumic(uname))
//			  sql="select uid,uname,upassword from user where uid="+Integer.parseInt(uname)+" or uname='"+uname+"'";
//		  else
//			  sql="select uid,uname,upassword from user where uname='"+uname+"'";
		  String sql="select uid,uname,upassword from user where uname='"+uname+"' and upassword='"+upassword+"'";
		  System.out.println("sql"+sql);
		  return jdbcTemplate.query(sql, new UserMapper());
	  }
	  public boolean addUser(String uname,String upassword) {
		  System.out.println("用户名密码"+uname+upassword);
		  String sql="insert into user(uname,upassword) values(?,?)";
		  return jdbcTemplate.update(sql,
				  new Object[] {uname,upassword},
				  new int[] {Types.VARCHAR,Types.VARCHAR})==1;
	  }
	  
	  /**
	   * 
	   * UserMapper数据库映射
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
			  System.out.println("有没有东西啊"+rs.getInt(1));
	 
			  return student;
		  } 
	  }
}

