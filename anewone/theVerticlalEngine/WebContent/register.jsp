<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="" media="all">
	<title>基于电影影评的垂直搜索引擎</title>
</head>
<style>
input[type=text], select,input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<body>
	<center>
	<h3>注册</h3>
	</center>
<div style="width:30%;margin:0 auto;padding-top:40px;padding-bottom:40px;">
  <form action="login.action">
    
    <input type="text" id="name" name="name" placeholder="用户名">
   
    <input type="password" id="password" name="password" placeholder="密码">
    <input type="password" id="password1" name="password1" placeholder="再次输入密码">
  
    <input type="submit" value="注册">
	  
  </form>
</div>
	
</body>
</html>