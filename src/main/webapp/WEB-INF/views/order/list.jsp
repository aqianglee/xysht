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
		var id = "${activeId }";
		var activeItem = $("#" + id);
		activeItem.addClass("active");
		
		$(".forword").click(function(){
			$.post(this.href, function(data) {
				if (data == "success") {
					alert("派发成功");
					window.location.href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=forword";
				}
			})
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
			<div class="right_title"></div>
			<div style="padding: 10px;">
				<ul class="nav nav-tabs">
					<li role="presentation" id="all"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=all">全部</a></li>
					<li role="presentation" id="submited"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=submited">未确认</a></li>
					<li role="presentation" id="checked"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=checked">已确认</a></li>
					<li role="presentation" id="forword"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=forword">已发货</a></li>
					<li role="presentation" id="received"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=received">已收货</a></li>
					<li role="presentation" id="refund"><a
						href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=refund">申请退款</a></li>
				</ul>
				<c:if
					test="${requestScope.orders != null && fn:length(requestScope.orders) > 0 }">
					<table class="table">
						<tr>
							<th>用户姓名</th>
							<th>联系电话</th>
							<th>送货地址</th>
							<th>商家</th>
							<th>提交日期</th>
							<th>价格</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${requestScope.orders }" var="c">
							<tr>
								<td>${c.receiveAddress.compellation }</td>
								<td>${c.receiveAddress.phone }</td>
								<td>${c.receiveAddress.address }</td>
								<td>${c.supermarket.name }</td>
								<td>${c.submitedTime }</td>
								<td>${c.price }</td>
								<td>${c.orderStatus }</td>
								
								<td>
									<a class="btn btn-primary" href="${pageContext.request.contextPath }/shopKeeper/showOrderDetails?orderId=${c.id }">查看</a>
									<c:if test="${c.orderStatus == 'checked'}">
										<a class="btn btn-primary forword" href="${pageContext.request.contextPath }/shopKeeper/forwordOrders?orderId=${c.id }">派送</a>
									</c:if>
									
								</td>
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