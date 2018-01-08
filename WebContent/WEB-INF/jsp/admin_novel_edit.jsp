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
<script>
function imgPreview(fileDom){
	var reader = new FileReader();
	// 获取文件
	var file = fileDom.files[0];
	// 读取完成
	reader.onload = function(e) {
		// 获取图片文件dom
		var img = document.getElementById("labHeadUrl");
		// 图片路径设置为读取图片
		img.src = e.target.result;
	};
	reader.readAsDataURL(file);
}
</script>
</head>
<body>
	<div class="content clearfix">
		<div class="main-wrap">
			<div class="main">
				<div class="mod admin-article-post">
					<div class="hd">
						<h2>小说管理</h2>
					</div>
					<div class="bd">
						<div class="mod-2 article-post">
							<div class="cont">
								<form method="post" enctype="multipart/form-data"
									action="${pageContext.request.contextPath}/admin/submit_novel">
									<input type="hidden" id="id" name="id"
										value="${novel.id}">
									<div class="form-area">
										<div class="form-item">
											<label for="txtName" class="label">书名</label>
											<div class="input">
												<input type="text" id="name" name="name"
													class="ipt-txt-2" value="${novel.name}">
											</div>
										</div>
										<div class="form-item">
											<label for="txtCategory" class="label">分类</label>
											<div class="operate">
												<select id="category" name="category" class="select-2">
													<c:forEach items="${categories}" var="cate">
														<c:if test="${cate.id == novel.category}">
															<option value="${cate.id}" selected>${cate.name}</option>
														</c:if>
														<c:if test="${cate.id != novel.category}">
															<option value="${cate.id}">${cate.name}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-item">
											<label for="txtAuthor" class="label">作者</label>
											<div class="input">
												<input type="text" id="author" name="author"
													class="ipt-txt-2" value="${novel.author}">
											</div>
										</div>
										<div class="form-item">
											<label for="txtInfo" class="label">简介</label>
											<textarea id="info" name="info" class="textarea-2"
												cols="30" rows="5">${novel.info}</textarea>
										</div>
										<div class="form-item">
											<label for="txtAbstracts" class="label">摘要</label>
											<textarea id="abstracts" name="abstracts"
												class="textarea-2" cols="30" rows="5">${novel.abstracts}</textarea>
										</div>
										<div class="form-item">
											<label for="txtCover" class="label">封面</label>
											<div class="operate">
												<input id="file" name="file" type="file" class="ipt-file"
													accept="image/*" onchange="imgPreview(this)">
											</div>
										</div>
										<div class="form-item">
											<div class="user-img" style="padding-left: 200px">
												<img class="user-img" src="${pageContext.request.contextPath}/assets/${novel.cover}" id="labHeadUrl"
													width="240px">
											</div>
										</div>
										<div class="form-btn">
											<input type="submit" value="提交">
										</div>
										<div class="form-area">
											<a href="${pageContext.request.contextPath}/admin/edit_novel">返回小说管理页</a>
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
</body>
</html>