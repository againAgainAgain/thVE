<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resource'/>/base1.css" media="all">
	<title>基于电影影评的垂直搜索引擎</title>
</head>
<body>
	<div class="login" style="float:right;border:0px;">
		<form action="login_view.action" method="post">
			<input type="submit" value="登陆" style="background-color:white;color:#808080"/>
		</form>
		<!--<a href="/login_view">登陆</a>-->
	</div>
	<div class="name">
		<p>基于电影影评的垂直搜索引擎</p>
	</div>
	
	<div class="search" style="width:50%;margin:0 auto">
		<form action="search.action" method="post">
			<!-- <img src="<c:url value='/resource'/>/search.png" alt="" > -->
			
    		<input type="text" name = "keyword" placeholder="请输入..." style="width:85%">
    		<input type="submit" value="搜索" style="width:15%"> 
    		<input type="hidden" value="登陆" name="uname">
		</form>
	</div>
</body>
</html>