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
			<div class="nav">
				<a href="${pageContext.request.contextPath }/shopKeeper/listAllClassfies?sid=${classfy.superMarket.id }">分类管理</a> &gt;&gt; <a href="listAllReclassfies?cid=${classfy.id }">${classfy.name }</a>
			</div>
			<div class="right_title">
				<h3><a href="${pageContext.request.contextPath }/shopKeeper/editReclassfy?cid=${classfy.id }">添加子分类</a></h3>
			</div>
			<div align="center">
				<c:if test="${requestScope.reclassfies != null && requestScope.reclassfies.size() > 0 }">
				<table border="1">
						<tr>
							<th>子分类名称</th>
							<th>编号</th>
							<th>商品管理</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					
						<c:forEach items="${requestScope.reclassfies }" var="r">
							<tr>
								<td>${r.name }</td>
								<td>${r.number }</td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/getGoodsByReclassfy?rid=${r.id }">编辑商品</a></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/editReclassfy?cid=${classfy.id }&id=${r.id }">修改</a></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/deleteReclassfy?cid=${classfy.id}&rid=${r.id}">删除</a></td>
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