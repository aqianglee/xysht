<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺</title>
<%@ include file="../model/js.jsp"%>
<style type="text/css">
.order {
	width: 320px;
	margin: 0px auto;
}

.orderDetails {
	margin: 5px;
}

.orderDetailsTitle {
	padding: 10px;
	font-weight: bold;
	font-size: 18px;
}

.orderDetailsItem {
	padding: 5px 20px;
}
</style>
<script type="text/javascript">
	$(function() {
		var confirmOrder = $("#confirmOrder");
		var back = $("#back");
		if ("${order.orderStatus}" != "submited") {
			confirmOrder.hide();
			back.show();
		} else {
			confirmOrder.show();
			back.hide();
		}
		confirmOrder.click(function() {
			var href = this.href;
			$.post(href, function(data) {
				if (data == "success") {
					alert("订单确认成功，请按返回回到，订单管理中心");
					confirmOrder.hide();
					back.show();
				}
			})
			return false;
		})
	})
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
				<c:if test="${requestScope.order != null }">
					<div class="order">
						<div class="orderDetails">
							<div class="orderDetailsTitle">订单信息</div>
							<div class="orderDetailsItem">订单号： ${order.number }</div>
							<div class="orderDetailsItem">商铺：${order.supermarket.name }</div>
							<div class="orderDetailsItem">下单时间：${order.submitedTime }</div>
							<div class="orderDetailsItem">订单状态：${order.orderStatus }</div>
						</div>
						<div class="orderDetails">
							<div>
								<table>
									<tr>
										<th width="60%">商品名称</th>
										<th width="20%">单价</th>
										<th width="20%">数量</th>
									</tr>
									<c:forEach items="${orderItems }" var="item">
										<tr>
											<td>${item.good.name }</td>
											<td>${item.good.price }</td>
											<td>${item.count }</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="orderDetailsItem" align="right">总计：${order.price }</div>
						</div>
						<div class="orderDetails">
							<div class="orderDetailsTitle">配送信息</div>
							<div class="orderDetailsItem">收货人姓名：${order.receiveAddress.compellation }</div>
							<div class="orderDetailsItem">收货人电话：${order.receiveAddress.phone }</div>
							<div class="orderDetailsItem">收货地址：${order.receiveAddress.address }</div>
						</div>
						<div style="padding: 10px" align="center">
							<a id="confirmOrder"
								href="${pageContext.request.contextPath }/shopKeeper/confirmOrder?orderId=${order.id }"
								class="btn btn-primary">确认订单</a> <a id="back"
								href="${pageContext.request.contextPath }/shopKeeper/listAllOrders?orderStatus=submited"
								class="btn btn-primary">返回</a>
						</div>
					</div>
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