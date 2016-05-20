<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑店铺</title>
<%@ include file="../model/js.jsp"%>
<script type="text/javascript">
	$(function() {

		$(".nav-item").click(function() {
			removeAllActive()
			$(this).addClass("active");
			var href = $(this).children("a")[0].title;
			$("#" + href).show();
			return false;
		});
	})

	function removeAllActive() {
		$(".nav-item").removeClass("active");
		$("#tab").hide();
		$("#tab2").hide();
		$("#tab3").hide();
	}
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
			<div class="right_title">
				<h3>添加店铺</h3>
			</div>
			<div style="padding: 10px;" align="center">
				<form:form
					action="${pageContext.request.contextPath }/shopKeeper/saveSuperMarket"
					method="post" modelAttribute="superMarket"
					enctype="multipart/form-data">
					<ul class="nav nav-tabs">
						<li role="presentation" class="active nav-item"><a
							href="#tab" title="tab">基本信息</a></li>
						<li role="presentation" class="nav-item"><a href="#tab2"
							title="tab2">热门分类</a></li>
						<li role="presentation" class="nav-item"><a href="#tab3"
							title="tab3">广告图片</a></li>
					</ul>
					<div id="tab" class="tab-content">
						<c:if
							test="${errorMessages != null  && fn:length(errorMessages) > 0}">
							<c:forEach items="${errorMessages}" var="e">
								<span class="errorInfo"><fmt:message key="${e.key }"></fmt:message></span>
								<br>
							</c:forEach>
						</c:if>
						<table>
							<form:hidden path="id" />
							<tr>
								<td align="right"><label>图片</label></td>
								<td><input type="file" name="p" class="textFiled"></td>
							</tr>

							<tr>
								<td align="right"><label>店铺名称：</label></td>
								<td><form:input path="name" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right"><label>店铺编号：</label></td>
								<td><form:input path="number" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right"><label>店铺地址：</label></td>
								<td><form:input path="address" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right" valign="top"><label>店铺简介：</label></td>
								<td><form:input path="introduction" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right"><label>免费起送价格：</label></td>
								<td><form:input path="beginSendPrice" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right"><label>派送费：</label></td>
								<td><form:input path="despatchMoney" class="textFiled" />
									<span class="errorInfo"><form:errors
											path="despatchMoney" /> </span></td>
							</tr>

							<tr>
								<td align="right"><label>服务区域：</label></td>
								<td><form:input path="serviceArea" class="textFiled" /></td>
							</tr>

							<tr>
								<td align="right" valign="top"><label>备注：</label></td>
								<td><form:input path="description" class="textFiled" /></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="提交" class="submit"></td>
							</tr>
						</table>
					</div>
					<div id="tab2" class="tab-content" style="padding: 10px">
						热门分类：
						<c:forEach items="${hotClassfies }" var="c">
							<input type="checkbox" name="hotClass" value="${c.id }"> <fmt:message key="${c.name }"/>
						</c:forEach>
					</div>
					<div id="tab3" class="tab-content">
						宣传图片：<input type="file" name="activityPicture">
					</div>
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