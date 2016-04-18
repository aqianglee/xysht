<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/frame.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pageItem.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/menu.js"></script>
<script type="text/javascript">
$(function(){
	if(${classfy != null}) {
		if(${classfy.level == "levelThree"}) {
			$("#navs").append("<li><a id='levelOne' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.parent.parent.id}'>${classfy.parent.parent.name}</a></li>");
			$("#navs").append("<li><a id='levelTwo' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.parent.id}'>${classfy.parent.name}</a></li>");
			$("#navs").append("<li><a id='levelThr' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.id}'>${classfy.name}</a></li>");
		} else if(${classfy.level == "levelTwo"}){
			$("#navs").append("<li><a id='levelOne' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.parent.id}'>${classfy.parent.name}</a></li>");
			$("#navs").append("<li><a id='levelTow' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.id}'>${classfy.name}</a></li>");
		} else {
			$("#navs").append("<li><a id='levelOne' href='getGoodsByClassfy?sid=${classfy.supermarket.id }&classfyId=${classfy.id}'>${classfy.name}</a></li>");
		}
	};
});
</script>
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
			<ol class="navs breadcrumb" id="navs">
				
			</ol>
			<c:if test="${classfies != null && fn:length(requestScope.classfies) > 0}">
				<ol style="margin-left: 20px">
					<c:forEach var="s" items="${requestScope.classfies }">
						<a href="getGoodsByClassfy?sid=${s.supermarket.id }&classfyId=${s.id}">${s.name }</a>
					</c:forEach>
				</ol>
			</c:if>
			<div class="right_title">
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/shopKeeper/editGood?classfyId=${classfy.id}">添加商品</a>
			</div>
			<div align="center">
				<c:if test="${requestScope.goods != null && requestScope.goods.size() > 0 }">
				<table class="table">
						<tr>
							<th>图片</th>
							<th>商品名称</th>
							<th>编号</th>
							<th>价格</th>
							<th>分类</th>
							<th>下架</th>
							<th></th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					
						<c:forEach items="${requestScope.goods }" var="g">
							<tr>
								<td>
									<div style="width: 50px; height: 50px;">
										<img alt="" height="50" src='<c:if test="${g.picture != null }">${pageContext.request.contextPath }/showPicture?pictureId=${g.picture.id }</c:if>
										<c:if test="${g.picture == null }">${pageContext.request.contextPath }/images/no_picture.jpg</c:if>'>
									</div>
								</td>
								<td>${g.name }</td>
								<td>${g.number }</td>
								<td>${g.price }</td>
								<td>${g.classfy.name }</td>
								<td>${g.offLine }</td>
								<td></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/editGood?id=${g.id }&classfyId=${classfy.id }">修改</a></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/deleteGood?id=${g.id}&rid=${classfy.id }">删除</a></td>
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