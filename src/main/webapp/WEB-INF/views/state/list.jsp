<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺</title>
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
				<h3><a href="${pageContext.request.contextPath }/shopKeeper/editState">添加订单状态</a></h3>
			</div>
			<div align="center">
				<c:if test="${requestScope.states != null && requestScope.states.size() > 0 }">
				<table border="1">
						<tr>
							<th>订单状态名称</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					
						<c:forEach items="${requestScope.states }" var="s">
							<tr>
								<td>${s.name }</td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/editState?id=${s.id }">编辑</a></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/deleteState?id=${s.id }">删除</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<footer>
		<jsp:include page="../model/footer.jsp"></jsp:include>
	</footer>
</body>
</html>