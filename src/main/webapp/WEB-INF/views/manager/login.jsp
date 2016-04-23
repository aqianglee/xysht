<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../model/taglib.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../model/js.jsp"%>	
</head>
<body>
	<header>
		<jsp:include page="../model/header.jsp"></jsp:include>
	</header>
	<div class="context">
		<div style="padding: 5em;">
			<div align="center" class="panel panel-default"
				style="width: 37em; margin: 0em auto; padding: 3.5em;">
				<form:form
					action="${pageContext.request.contextPath }/nonLogin/manager_doLogin"
					method="post" modelAttribute="manager">
					<h2>管理员登陆</h2>
					<div style="width: 30em; margin: 3em auto;">
						<c:if
							test="${errorMessages != null  && fn:length(errorMessages) > 0}">
							<c:forEach items="${errorMessages}" var="e">
								<span class="errorInfo"><fmt:message key="${e.key }"></fmt:message></span>
								<br>
							</c:forEach>
						</c:if>
						<div class="input-group input-group-lg">
							<span class="input-group-addon" id="basic-addon1">用户名:</span>
							<form:input path="username" cssClass="form-control" id="username"
								placeholder="用户名/手机号/邮箱" aria-describedby="basic-addon1" />
						</div>
						<br>
						<div class="input-group input-group-lg">
							<span class="input-group-addon" id="basic-addon1">密&nbsp;&nbsp;&nbsp;码:</span>
							<form:password path="password" cssClass="form-control"
								id="password" placeholder="6-16位数字或字母"
								aria-describedby="basic-addon1" />
						</div>
						<br> <input type="submit" value="登陆"
							class="submit btn btn-primary" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<footer>
		<jsp:include page="../model/footer.jsp"></jsp:include>
	</footer>
</body>
</html>