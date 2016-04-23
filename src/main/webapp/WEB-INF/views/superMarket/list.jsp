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
	$(function() {
		$(".delete").click(function() {
			var item = this;
			var href = item.href;
			if (confirm("确定删除吗？")) {
				$.get(href, function(data) {
					if (data.length > 0) {
						if (confirm("所选项包含子项目，删除会删除所有子项目，确认删除吗？")) {
							href = href + "&noAsk=true";
							$.get(href, function(data) {
								if (data.length > 0) {
									alert(data[0].key);
								} else {
									$(item).parent().parent().remove();
								}
							});
						}
					} else {
						$(item).parent().parent().remove();
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
				<li>店铺管理</li>
			</ol>
			<div class="right_title">
				<a class="btn btn-primary" href="editSuperMarket">添加店铺</a>
			</div>
			<div style="padding: 10px;">
				<c:if
					test="${requestScope.superMarkets != null && fn:length(requestScope.superMarkets) > 0 }">
					<table class="table">
						<tr>
							<th>店铺名称</th>
							<th>店铺地址</th>
							<th width="240px">店铺简介</th>
							<th>管理店铺</th>
							<th>修改</th>
							<th>删除</th>
						</tr>

						<c:forEach items="${requestScope.superMarkets }" var="sm">
							<tr>
								<td>${sm.name }</td>
								<td>${sm.address }</td>
								<td>${sm.introduction }</td>
								<td><a
									href="${pageContext.request.contextPath }/shopKeeper/listAllClassfies?sid=${sm.id }">管理分类</a></td>
								<td><a
									href="${pageContext.request.contextPath }/shopKeeper/editSuperMarket?id=${sm.id }">修改</a></td>
								<td><a class="delete"
									href="${pageContext.request.contextPath }/shopKeeper/deleteSuperMarket?id=${sm.id }">删除</a></td>
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