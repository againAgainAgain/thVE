<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- saved from url=(0047)http://list.jd.com/list.html?cat=1315,1343,1355 -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="utf-8" http-equiv="charset">
<meta name="referrer" content="no-referrer">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/base.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/plist20131112.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/list-page-20141009.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/pop_compare.css" media="all">
<link rel="shortcut icon" type="image/ico"
	href="http://list.jd.com/favicon.ico">
<script type="text/javascript"
	src="<c:url value='/resource'/>/jquery-1.2.6.pack.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}

</style>
<script type="text/javascript">

	function query() {
		//执行关键词查询时清空过滤条件
		document.getElementById("sort_fname").value="";
		document.getElementById("score").value="";
		document.getElementById("page").value="";
		//执行查询
		queryList();
	}

	function queryList() {
		//提交表单
		document.getElementById("actionForm").submit();
	}
	function filter(key, value) {
		document.getElementById(key).value=value;//注意这里是<form>里的<input type="hidden" name="score" id="score" value="${score }"/> 
		//执行查询
		queryList();
	}
	function sort(){
		var temp=document.getElementById("sort_fname").value;
		if(temp=="1")
			temp="0";
		else temp="1";
		document.getElementById("sort_fname").value=temp;
		queryList();
	}
	//function page(value){
		
	//}
</script>
</head>
<body>
	<div>
		<form id="actionForm" action="search.action" method="POST">
    		<input type="text" name = "keyword" value="${keyword} ">
    		<input type="button" value="搜索" class="button" onclick="query()">
    		
    		<input type="hidden" name="score" id="score" value="${score }"/>
    		<input type="hidden" name="sort_fname" id="sort_fname" value="${sort_fname }"/>
    		<input type="hidden" name="page" id="page" value="${page }"/> 
		</form>
		
	</div>
	
	<div>
		<div>评分：</div>
		<div>
			<ul>
				<li><a href="javascript:filter('score','9-9.5')">9-9.5</a></li>
				<li><a href="javascript:filter('score','9.5-10')">9.5-10</a></li>
			</ul>
		</div>
	</div>
	<div>
		<a href="javascript:sort()">相同电影的影评排在在一起</a>
	</div>
	
	<span>共查询到  ${count} 条数据</span>
	<!--电影列表开始-->
	<div id="plist" class="m plist-n7 plist-n8 prebuy">
		<ul class="list-h">
			<c:forEach var="item" items="${filmModels }">
			<li id="${item.id }">
				<div class="lh-wrap">
					<div class="p-img">
						<a target="_blank" href="#">
							<img width="220" height="282" class="err-product" src="<c:url value='${item.src}'/>">
						</a>
					</div>
					<div class="p-name">
						电影名：<a target="_blank" href="<c:url value='${item.href}'/>">${item.fname }</a>
					</div>
					<div class="p-price">
						<strong>评分<fmt:formatNumber value='${item.score}' maxFractionDigits="2"/></strong><span id="p1269191543"></span>
					</div>
					
					<div class="p-price">
						评论：<a target="_blank" href="<c:url value='${item.c_src}'/>">${item.comment }</a>
					</div>
					<br>
					<br>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
<!--商品列表结束-->
	<div>
		<!-- 分页实现 -->
		共:${num }页
		<c:forEach var="x" begin="1" end="${num}" step="1">
			<a href="javascript:filter('page','${x }')">第 ${x}页</a>
		</c:forEach>
	</div>

</body>
</html>