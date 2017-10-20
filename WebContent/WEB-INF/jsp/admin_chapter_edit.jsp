<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" rel="stylesheet">
<title>章节管理 - 管理员 - 耳聪心慧文端巧</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/icon.png">
<script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/assets/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/assets/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/assets/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/assets/ueditor/third-party/jquery-1.10.2.min.js"></script>
</head>
<body>
	<div class="content clearfix">
		<div class="main-wrap">
			<div class="main">
				<div class="mod admin-article-post">
					<div class="hd">
						<h2>章节管理</h2>
					</div>
					<div class="bd">
						<div class="mod-2 article-post">
							<div class="cont">
								<form method="post" action="${pageContext.request.contextPath}/admin/submit_chapter">
									<input type="hidden" id="id" name="id" value="${chapter.id}">
									<input type="hidden" id="novel" name="novel" value="${novel.id}">
									<div class="form-area">
										<div class="form-item">
											<label for="txtName" class="label">书名</label>
											<div class="input">
												<input type="text" id="name" name="name"
													class="ipt-txt-2" value="${novel.name}" readonly>
											</div>
										</div>
										<div class="form-item">
											<label for="txtTitle" class="label">标题</label>
											<div class="input">
												<input type="text" id="title" name="title"
													class="ipt-txt-2" value="${chapter.title}">
											</div>
										</div>
										<div class="form-item">
											<label for="editor" class="label">内容</label>
											<div class="input">
												<script id="editor" type="text/plain" style="width:680px;height:300px;"></script>
											</div>
										</div>
										<div class="form-btn">
											<input type="submit" value="提交">
										</div>
										<div class="form-area">
											<a href="${pageContext.request.contextPath}/admin/edit_chapter/${novel.id}">返回章节信息页</a>
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
	<script>
		window.onload = function() {
			var editor = UE.getEditor("editor");
			editor.addListener("ready", function() {
				// 发起ajax请求，从服务器取得章节内容，调用editor.setContent();
				var xhr = new XMLHttpRequest();
				xhr.open("get", "${pageContext.request.contextPath}/admin/edit_content?chapter=${chapter.id}", true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState === 4) {
						if (xhr.status === 200) {
							editor.setContent(xhr.responseText);
						}
					}
				}
			});
		}
	</script>
</body>
</html>