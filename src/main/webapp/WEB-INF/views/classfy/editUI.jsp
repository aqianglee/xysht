<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑分类</title>
<%@ include file="../model/js.jsp"%>
<script type="text/javascript">
	$(function(){
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
			<div class="right_title">
				<h3>添加分类</h3>
			</div>
			<div align="center">
				<form:form action="${pageContext.request.contextPath }/shopKeeper/saveClassfy" method="post" modelAttribute="classfy">
					<jsp:include page="../model/errorsPanel.jsp"/>
					<table>
						<form:hidden path="id"/>
						<form:hidden path="supermarket.id" value="${s.id }"/>
						<form:hidden path="parent.id" value="${p.id }"/>
						<tr>
							<td align="right"><label>分类名称：</label></td>
							<td>
								<form:input path="name" cssClass="form-control textFiled"/>
							</td>
						</tr>
						<tr>
							<td align="right"><label>分类编号：</label></td>
							<td>
								<form:input path="number" cssClass="form-control textFiled"/>
							</td>
						</tr>
						<tr><td></td></tr>
						<tr>
							<td></td>
							<td><input class="btn btn-primary submit" type="submit" value="提交"></td>
						</tr>
					</table>	
					<br>
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