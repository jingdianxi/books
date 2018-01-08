<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet">
<title>登录</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
<link href="${pageContext.request.contextPath}/assets/css/mricode.pagination.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/mricode.pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script type="text/javascript">
	
	window.onload = function() {

		var vertifycode = document.getElementById("vertifycode");
		vertifycode.onclick = function() {
			var time = new Date();
			vertifycode.src = "${pageContext.request.contextPath}/vertify?time=" + time;
		}

		login.onclick = function() {
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var vertify = document.getElementById("vertify");

			var xhr = new XMLHttpRequest();

			xhr.open("post", "admin/login", true);
			xhr.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");

			xhr.send("username=" + username.value + "&password="
					+ password.value + "&vertify=" + vertify.value);

			xhr.onreadystatechange = function() {

				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						if (xhr.responseText == "0") {
							// 登录失败，返回提示信息
							document.getElementById("msg").innerHTML = "登录失败";
						} else {
							// 登录成功，跳转至管理员主页
							window.location.href = "${pageContext.request.contextPath}/admin/admin_home";
						}
					}
				}
			};
		}
	}
</script>
</head>
<body>
	<div class="content clearfix">
		<div class="main-wrap">
			<div class="main">
				<div class="mod admin-index">
					<div class="hd">
						<h2>管理员登录</h2>
					</div>
					<div class="bd">
						<div class="mod-2 article-class">
							<div class="cont">
								<div class="form-area">
									<form method="post">
										<div class="form-item">
											<label for="txtName" class="label">帐&nbsp;&nbsp;&nbsp;号</label>
											<div class="input">
												<input type="text" id="username" name="username" class="ipt-txt-1">
											</div>
										</div>
										<div class="form-item">
											<label for="txtName" class="label">密&nbsp;&nbsp;&nbsp;码</label>
											<div class="input">
												<input type="password" id="password" name="password" class="ipt-txt-1">
											</div>
										</div>
										<div class="form-item">
											<label for="txtName" class="label">验证码</label>
											<div class="input">
												<input type="text" id="vertify" name="vertify" class="ipt-txt-1">
											</div>
											<div class="input">
												<img src="vertify" id="vertifycode">
											</div>
										</div>
										<div class="form-item">
											<div class="input">
												<input name="submit" type="button" value="提交" class="ipt-submit" id="login">&nbsp;&nbsp;
												<input type="reset" value="重置" class="ipt-submit">&nbsp;&nbsp;
												<input type="button" value="返回首页" class="ipt-submit" onclick="location.href='${pageContext.request.contextPath}/'">&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="msg"></span>
											</div>
										</div>
									</form>
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