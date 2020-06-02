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
<title>基于电影影评的垂直搜索引擎</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
		document.getElementById("name_sort").class="active";
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
	<div class="container">
		<header class="blog-header py-3">
			<div class="row flex-nowrap justify-content-between align-items-center">
          		<div class="col-4 d-flex justify-content-end align-items-center" style="text-align:right">      
            		<a class="btn btn-sm btn-outline-secondary" href="login.html">Sign up</a>
          		</div>
          	<div class="col-8">
				<form class="bs-example bs-example-form" role="form" id="actionForm" action="search.action" method="POST">
    				<div class="row">
    					<div class="col-lg-10">
                  			<div class="input-group">
                    			<input type="text" class="form-control" name = "keyword" value="${keyword}">
                    			<span class="input-group-btn">
                      				<button class="btn btn-default" type="button" onclick="query()">Go!</button>
                    			</span>
                  			</div><!-- /input-group -->
                		</div><!-- /.col-lg-6 -->
    				</div>
    				<input type="hidden" name="score" id="score" value="${score}"/>
    				<input type="hidden" name="sort_fname" id="sort_fname" value="${sort_fname}"/>
    				<input type="hidden" name="page" id="page" value="${page}"/> 
				</form>
			</div>
		</div>
	
	<div style="height:100px;overflow:visible;">
          <ul class="nav nav-tabs" >
            <li id="name_sort"><a href="javascript:sort()">按名字排序</a></li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                评分区间 <span class="caret"></span>
              </a>
              <ul class="dropdown-menu">
                <li><a href="javascript:filter('score','9-9.5')">9-9.5</a></li>
                <li><a href="javascript:filter('score','9.5-10')">9.5-10</a></li>
                <li><a href="javascript:filter('score','0-9')">9分以下</a></li>
              </ul>
            </li>
          </ul>
          <span style="color:#CCC;font-size:10">共查询到  ${count} 条数据</span>
        </div>
	
	
	
	</header>
	<!--电影列表开始-->
	<div id="plist" class="m plist-n7 plist-n8 prebuy">
		<ul class="list-h" style="list-style:none;margin-left:0;padding-left:0;">
<c:forEach var="item" items="${filmModels }">
  <li id="${item.id}">
  <div class="media" style="margin-top:10px;">
    <div class="media-left">
      <img src="<c:url value='${item.src}'/>" class="media-object" style="width:60px">
    </div>
    <div class="media-body">
      <h4 class="media-heading">电影名：<a target="_blank" href="<c:url value='${item.href}'/>">${item.fname}</a></h4>
      <strong>评分<fmt:formatNumber value='${item.score}' maxFractionDigits="2"/></strong><span id="p1269191543"></span>
      <p>评论：<a target="_blank" href="<c:url value='${item.c_src}'/>">${item.comment}</a></p>
    </div>
  </div>
  <hr>
  </li>
</c:forEach>
</ul>
	</div>
<!--商品列表结束-->
	<div>
		<!-- 分页实现 -->
		共${num }页
		<c:forEach var="x" begin="1" end="${num}" step="1">
			<a href="javascript:filter('page','${x}')">第 ${x}页</a>
		</c:forEach>
	</div>
	</div>
</body>
</html>