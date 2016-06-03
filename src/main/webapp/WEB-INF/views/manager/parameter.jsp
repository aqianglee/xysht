<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统参数管理</title>
<%@ include file="../model/js.jsp"%>
</head>
<body>
	<header>
		<jsp:include page="../model/header.jsp"></jsp:include>
	</header>
	<div class="context">
		<div class="menu">
			<jsp:include page="../model/menu-manager.jsp"></jsp:include>
		</div>
		<div class="right">
			<div style="padding: 5em;">
				<div align="center" class="panel panel-default"
					style="width: 37em; margin: 0em auto; padding: 3.5em;">
					<form action="${pageContext.request.contextPath }/manager/editParameters" method="post">
						<h2>系统参数设置</h2>
						<div style="width: 30em; margin: 3em auto;">
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="basic-addon1">文件存储根目录:</span>
								<input name="fileRootDir" class="form-control" value="${file_root_dir }" aria-describedby="basic-addon1" />
							</div>
							<br> <input type="submit" value="提交"
								class="submit btn btn-primary" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<footer>
		<jsp:include page="../model/footer.jsp"></jsp:include>
	</footer>
</body>
</html>