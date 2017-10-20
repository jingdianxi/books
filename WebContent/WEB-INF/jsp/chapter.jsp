<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css?v=1.0.5">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/assets/css/novel.css?v=1.0.27">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js?v=1.0.5" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/novel.js?v=1.0.5" type="text/javascript"></script>
<title>${chapter.title} - ${novel.name} - ${novel.author} - 耳聪心慧文端巧</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
</head>
<body>
	<div class="main container">
		<ol class="breadcrumb" style="margin-top: 20px;">
			<li><a href="${pageContext.request.contextPath}/">小说列表</a></li>
			<li><a href="${pageContext.request.contextPath}/novel/${novel.id}">${novel.name}</a></li>
			<li class="active">${chapter.title}</li>
		</ol>
		<hr />
		<nav>
		<ul class="pager">
			<li class="previous"><c:if test="${prev != 0}">
					<a href="${pageContext.request.contextPath}/chapter/${prev}">上一章</a>
				</c:if></li>
			<li class="next"><c:if test="${next != 0}">
					<a href="${pageContext.request.contextPath}/chapter/${next}">下一章</a>
				</c:if></li>
		</ul>
		</nav>
		<div class="btn-group">
			<div id="font-minus" type="button" class="btn btn-default btn-sm">字体-</div>
			<div id="font-plus" type="button" class="btn btn-default btn-sm">字体+</div>
		</div>
		<label class="radio-inline"> <input type="radio" name="mode" value="day">白天模式
		</label> <label class="radio-inline"> <input type="radio" name="mode" value="night">黑夜模式
		</label>
		<p id="content">${chapter.title}<br />
			<br /> ${chapter.content}
		</p>
		<nav>
		<ul class="pager">
			<li class="previous"><c:if test="${prev != 0}">
					<a href="${pageContext.request.contextPath}/chapter/${prev}">上一章</a>
				</c:if></li>
			<li class="next"><c:if test="${next != 0}">
					<a href="${pageContext.request.contextPath}/chapter/${next}">下一章</a>
				</c:if></li>
		</ul>
		</nav>
	</div>
</body>
</html>