<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet">
<title>小说管理 - 管理员</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
<link href="${pageContext.request.contextPath}/assets/css/mricode.pagination.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/mricode.pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script type="text/javascript">
	window.onload = function() {
		// 分页组件初始化
		var json_parameter = {
			pageIndex : 0,
			pageSize : 3,
			showPageSizes : false,
			showJump : true,
			showInfo : false,
			// 发起ajax请求，将json字符串发送至分页组件
			remote : {
				url : "${pageContext.request.contextPath}/paged",
				success : function(data) {
					var html = template("list", data);
					document.getElementById("labArticleClass").innerHTML = html;
				}
			}
		};
		$("#page").pagination(json_parameter);
	}
</script>
<script type="text/html" id="list">
{{each list as novel}}
<tr>
<td class="tit">{{novel.name}}</td>
<td>[ <a href="${pageContext.request.contextPath}/admin/edit_chapter/{{novel.id}}">章节信息</a>
	| <a href="${pageContext.request.contextPath}/admin/update_novel/{{novel.id}}">小说信息</a>
	| <a href="${pageContext.request.contextPath}/admin/del_novel/{{novel.id}}">删除小说</a> ]
</td>
</tr>
{{/each}}
</script>
</head>
<body>
	<div class="content clearfix">
		<div class="main-wrap">
			<div class="main">
				<div class="mod admin-article-class">
					<div class="hd">
						<h2>小说管理</h2>
					</div>
					<div class="bd">
						<div class="mod-2 article-class">
							<div class="cont">
								<div class="form-area">
									<a href="${pageContext.request.contextPath}/admin/add_novel">增加小说</a>
								</div>
								<div class="list-cont">
									<table class="list-table">
										<tbody>
											<tr>
												<th class="tit">书名</th>
												<th class="operate">操作</th>
											</tr>
										</tbody>
										<tbody id="labArticleClass">
											<%-- <c:forEach items="${novels}" var="novel" varStatus="status">
												<tr>
													<td class="tit">${status.index+1}: ${novel.name}</td>
													<td>[ <a href="${pageContext.request.contextPath}/admin/edit_chapter/${novel.id}">章节信息</a>
														| <a href="${pageContext.request.contextPath}/admin/update_novel/${novel.id}">小说信息</a>
														| <a href="${pageContext.request.contextPath}/admin/del_novel/${novel.id}">删除小说</a> ]
													</td>
												</tr>
											</c:forEach> --%>
										</tbody>
									</table>
									<div id="page" class="m-pagination"></div>
								</div>
								<div class="form-area">
									<a href="${pageContext.request.contextPath}/admin/admin_home">返回管理员首页</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>