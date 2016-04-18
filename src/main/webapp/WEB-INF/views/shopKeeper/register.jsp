
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pageItem.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/frame.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="../model/header.jsp"></jsp:include>
	</header>
	<div class="context">
		<div style="padding: 5em;">
			<div align="center" class="panel panel-default" style="width: 37em; margin: 0em auto; padding:3.5em;">
				<form:form action="${pageContext.request.contextPath }/nonLogin/shopKeeper_save"
					method="post" modelAttribute="shopKeeper">
					<h2>店长注册</h2>
					<div style="width: 30em; margin: 3em auto;">
						<c:if test="${errorMessages != null  && fn:length(errorMessages) > 0}">
							<c:forEach items="${errorMessages}" var="e">
								<span class="errorInfo"><fmt:message key="${e.key }"></fmt:message></span><br>
							</c:forEach>
						</c:if>
						<div class="input-group input-group-lg">
						  	<span class="input-group-addon" id="basic-addon1">&nbsp;用&nbsp;户&nbsp;名:</span>
						  	<form:input path="username" cssClass="form-control" id="username" placeholder="例：aqiang" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group input-group-lg">
						  	<span class="input-group-addon" id="basic-addon1">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span>
						  	<form:password path="password" cssClass="form-control" id="password" placeholder="6-16位数字或字母" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group input-group-lg">
						  	<span class="input-group-addon" id="basic-addon1">确认密码:</span>
						  	<form:password path="repassword" cssClass="form-control" id="repassword" placeholder="6-16位数字或字母" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group input-group-lg">
						  	<span class="input-group-addon" id="basic-addon1">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</span>
						  	<form:input path="email" cssClass="form-control" id="email" placeholder="例：xxx@163.com" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group input-group-lg">
						  	<span class="input-group-addon" id="basic-addon1">电话号码:</span>
						  	<form:input path="phone" cssClass="form-control" id="phone" placeholder="例：138000000000" aria-describedby="basic-addon1"/>
						</div>
						<br><br>
						<input type="submit" id="register" value="确认注册" class="submit btn btn-primary" />
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