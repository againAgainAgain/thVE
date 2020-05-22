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
		//document.getElementById("catalog_name").value="";
		//document.getElementById("price").value="";
		//执行查询
		queryList();
	}

	function queryList() {
		//提交表单
		document.getElementById("actionForm").submit();
	}
	function filter(key, value) {
		document.getElementById(key).value=value;
		//执行查询
		queryList();
	}
</script>
</head>
<body>
	<div>
		<form id="actionForm" action="search.action" method="POST">
    		<input type="text" name = "keyword" value="${keyword} ">
    		<input type="button" value="搜索" class="button" onclick="query()">
		</form>
	</div>
	
	<!--商品列表开始-->
	<div id="plist" class="m plist-n7 plist-n8 prebuy">
		<ul class="list-h">
			<c:forEach var="item" items="${filmModels }">
			<li id="${item.fid }">
				<div class="lh-wrap">
					<div class="p-img">
						<a target="_blank" href="#">
							<img width="220" height="282" class="err-product" src="<c:url value='/images'/>/${item.picture}">
						</a>
					</div>
					<div class="p-name">
						电影名：<a target="_blank" href="#">${item.film_name }</a>
					</div>
					<div class="p-price">
						<strong>评分<fmt:formatNumber value="${item.score}" maxFractionDigits="2"/></strong><span id="p1269191543"></span>
					</div>
					<br>
					<br>
					<div class="p-price">
						电影名：<a target="_blank" href="#">${item.comment }</a>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
</div>
<!--商品列表结束-->
	

</body>
</html>