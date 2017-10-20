<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="full-screen" content="yes">
<meta name="screen-orientation" content="portrait">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="applicable-device" content="pc,mobile">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="stylesheet" media="screen"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css?v=1.0.5">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/assets/css/novel.css?v=1.0.27">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js?v=1.0.5" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/novel.js?v=1.0.5" type="text/javascript"></script>
<title>首页 - 耳聪心慧文端巧</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
<link href="${pageContext.request.contextPath}/assets/css/mricode.pagination.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/mricode.pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script type="text/javascript">

	function load(category, condition) {
		// 分页组件初始化
		var json_parameter = {
			pageIndex : 0,
			pageSize : 3,
			showPageSizes : false,
			showJump : true,
			showInfo : false,
			// 发起ajax请求，将json字符串发送至分页组件
			remote : {
				url : "${pageContext.request.contextPath}/paged?category="+category+"&condition="+condition,
				success : function(data) {
					var html = template("list", data);
					document.getElementById("content").innerHTML = html;
				}
			}
		};
		$("#page").pagination(json_parameter);
	}
	
	window.onload = function() {

		var category = 0;
		var condition = "";
		load(category, condition);

		// 监听分类链接点击事件
		$(".novel-nav li a").on("click", function() {
			category = $(this).attr("value");
			$("#page").pagination("destroy");
			load(category, condition);
		});
		
		// 监听搜索按钮点击事件
		$("#btn").click(function () {
			condition = $("#keyword").val();
			$("#page").pagination("destroy");
			load(category, condition);
		});
		

		$(document).keydown(function(event) {
			switch (event.keyCode) {
				case 13: return false;
			}
		});
	}
</script>
<script type="text/html" id="list">
<ul class="bookshelf">
{{each list as novel}}
	<li><a href="${pageContext.request.contextPath}/novel/{{novel.id}}"> <img class="cover" src="${pageContext.request.contextPath}/assets/{{novel.cover}}" alt="{{novel.name}}" />
		<div>
			<h4>{{novel.name}}</h4>
			<p class="popularity">人气: {{novel.hits}}</p>
			<p class="author">{{novel.author}}
			</div>
	</a>
	</li>
{{/each}}
</ul>
</script>
</head>
<body>
	<div class="main container">
		<form class="input-group">
			<input name="keyword" type="text" class="form-control" id="keyword"
				placeholder="搜索书名或作者.."> <span class="input-group-btn">
				<input type="button" value="搜索" class="btn btn-default" id="btn">
				<input type="button" value="重置" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/'">
			</span>
		</form>
		<c:if test="${!empty maps && maps != ''}">
		<div id="recent-books">
			<a href="${pageContext.request.contextPath}/delCookie" style="float:right;">清空记录</a>
			<h3>我最近摸过的书</h3>
			<hr>
			<ul class="bookshelf">
				<c:forEach items="${maps}" var="map">
					<li>
						<a href="${pageContext.request.contextPath}/chapter/${map['id']}">
							<img class="cover" src="${pageContext.request.contextPath}/assets/${map['cover']}">
							<div>
								<h4>${map['name']}</h4>
								<p class="author">${map['title']}</p>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		</c:if>
		<div class="clearfix"></div>
		<h3>小说分类</h3>
		<hr>
		<ul class="novel-nav">
			<li><div>
					<a href="####" value="0">全部</a>
				</div></li>
			<c:forEach items="${categories}" var="category">
				<li><div>
						<a href="####" value="${category.id}">${category.name}</a>
					</div></li>
			</c:forEach>
		</ul>
		<div class="clearfix"></div>
		<div id="content" style="overflow: hidden;"></div>
		<div id="page" class="m-pagination"></div>
		<div class="clearfix"></div>
		<hr>
		<div class="input" style="text-align: center;">
			<a href="${pageContext.request.contextPath}/login_page">管理员登录</a>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>