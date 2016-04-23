<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺</title>
<%@ include file="../model/js.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#navs").append("<li><a id='levelOne' href='listAllClassfies?sid=${sid }'>分类管理</a></li>");
		if(${parent != null}) {
			if(${parent.level == "levelTwo"}) {
				$(".editChildClassfy").hide();
				$("#navs").append("<li><a id='levelTwo' href='listAllClassfies?sid=${parent.supermarket.id }&parentId=${parent.parent.id}'>${parent.parent.name}</a></li>");
				$("#navs").append("<li><a id='levelThree' href='listAllClassfies?sid=${parent.supermarket.id }&parentId=${parent.id}'>${parent.name}</a></li>");
			} else {
				$("#navs").append("<li><a id='levelTow' href='listAllClassfies?sid=${parent.supermarket.id }&parentId=${parent.id}'>${parent.name}</a></li>");
			}
		};
		
		$(".delete").click(function(){
			var href = this.href;
			if(confirm("确定删除吗？")){
				$.get(href, function(data) {
					if(data.success) {
						$(this).parent().parent().remove();
					} else {
						alert();
					}
				}); 
			}
			return false;
		});
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
			<div class="right_title">
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/shopKeeper/editClassfy?supermarketId=${sid }&parentId=${parent.id}">添加分类</a>
			</div>
			<div style="padding: 10px;">
				<c:if test="${requestScope.classfies != null && fn:length(requestScope.classfies) > 0 }">
					<table class="table">
						<tr>
							<th>分类名称</th>
							<th>分类等级</th>
							<th>编号</th>
							<th>管理商品</th>
							<th>子分类管理</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
						<c:forEach items="${requestScope.classfies }" var="c">
							<tr>
								<td>${c.name }</td>
								<td><fmt:message key="${c.level }"/></td>
								<td>${c.number }</td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/getGoodsByClassfy?classfyId=${c.id}">管理商品</a></td>
								<td><a class="editChildClassfy" href="${pageContext.request.contextPath }/shopKeeper/listAllClassfies?sid=${c.supermarket.id }&parentId=${c.id}">编辑子分类</a></td>
								<td><a href="${pageContext.request.contextPath }/shopKeeper/editClassfy?id=${c.id }&supermarketId=${c.supermarket.id }&parentId=${parent.id}">修改</a></td>
								<td><a class="delete" href="${pageContext.request.contextPath }/shopKeeper/deleteClassfy?id=${c.id }&sid=${c.supermarket.id }">删除</a></td>
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