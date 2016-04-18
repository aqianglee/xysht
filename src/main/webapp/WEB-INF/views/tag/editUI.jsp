<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑店铺</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/frame.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/pageItem.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/table.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/menu.js"></script>
</head>
<body>
	<header>
		<jsp:include page="../model/header.jsp"></jsp:include>
	</header>
	<div class="context">
		<div class="menu">
			<jsp:include page="../model/menu.jsp"></jsp:include>
		</div>
		<div class="right">
			<div class="right_title">
				<h3>添加店铺</h3>
			</div>
			<div align="center">
				<form:form action="${pageContext.request.contextPath }/shopKeeper/saveTag" method="post" modelAttribute="tag">
					<span class="errorInfo">${actionError } </span>
					<table>
						<form:hidden path="id"/>
						<tr>
							<td align="right"><label>标签名称：</label></td>
							<td>
								<form:input path="name" class="textFiled"/>
							</td>
						</tr>
					</table>	
					<br>
					<input type="submit" value="提交" class="submit">
				</form:form>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<footer>
		<jsp:include page="../model/footer.jsp"></jsp:include>
	</footer>
</body>
</html>