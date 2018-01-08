<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet">
<title>管理员</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
</head>
<body>
	<div class="content clearfix">
		<div class="main-wrap">
			<div class="main">
				<div class="mod admin-index">
					<div class="hd">
						<h2>管理员首页</h2>
					</div>
					<div class="bd">
						<div class="notice-cont clearfix">
							<div class="mod-2 notice">
								<div class="cont">
									<ul id="labBulletinList">
										<li><a href="${pageContext.request.contextPath}/admin/edit_novel">小说管理</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/edit_cate">分类管理</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/logout">退出登录</a></li>
									</ul>
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