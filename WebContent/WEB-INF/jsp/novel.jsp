<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<title>${novel.name} - ${novel.author}</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
</head>
<body>
<div class="main container">
    <ol class="breadcrumb" style="margin-top: 20px;">
        <li><a href="${pageContext.request.contextPath}/">小说列表</a></li>
        <li class="active">${novel.name}</li>
    </ol>
    <div id="article-description">
        <h3>封面</h3>
        <img class="big-cover" src="${pageContext.request.contextPath}/assets/${novel.cover}" alt="${novel.name}">
        <h3>内容简介</h3>
        <div id="description" class="well">
            ${novel.abstracts}
        </div>
        <div class="clearfix"></div>
        <h3>作者</h3>
        <div id="author-resume" class="well">${novel.author}<br/>${novel.info}</div>
        <h3>章节</h3>
        <table class="table table-bordered" style="margin-top: 10px;">
            <!--thead>
                <tr><th>章节</th></tr>
            </thead-->
            <tbody>
			<c:forEach items = "${chapters}" var = "chapter">
            <tr>
                <td><a href="${pageContext.request.contextPath}/chapter/${chapter.id}">${chapter.title}</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>