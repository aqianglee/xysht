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
			<div class="right_title">
				<h3>添加商品</h3>
			</div>
			<div align="center">
				<form:form action="${pageContext.request.contextPath }/shopKeeper/saveGood" 
				method="post" modelAttribute="good" enctype="multipart/form-data">
					<jsp:include page="../model/errorsPanel.jsp"/>
					<table>
						<form:hidden path="id"/>
						<form:hidden path="classfy.id" value="${classfy.id }"/>
						<form:hidden path="supermarket.id" value="${supermarket.id }"/>
						<tr>
							<td align="right"><label>商品名称：</label></td>
							<td>
								<form:input path="name" cssClass="textFiled"/>
							</td>
						</tr>
						<tr>
							<td align="right"><label>商品类型：</label></td>
							<td>
								<c:out value="${classfy.name }"></c:out>
							</td>
						</tr>
						<tr>
							<td align="right"><label>商品编号：</label></td>
							<td>
								<form:input path="number" cssClass="textFiled"/>
							</td>
						</tr>
						
						<tr>
							<td align="right"><label>商品价格：</label></td>
							<td>
								<form:input path="price" cssClass="textFiled"/>
							</td>
						</tr>
						
						<tr>
							<td align="right"><label>商品规格：</label></td>
							<td>
								<form:input path="specification" cssClass="textFiled"/>
							</td>
						</tr>
						
						<tr>
							<td align="right"><label>商品库存：</label></td>
							<td>
								<form:input path="stockNumber" cssClass="textFiled"/>
							</td>
						</tr>
						<tr>
							<td align="right"><label>标签：</label></td>
							<td>
								<c:forEach items="${requestScope.tags }" var="t">
									<input type="checkbox" name="tagsId" value="${t.id }"> ${t.name }  
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td align="right"><label>图片：</label></td>
							<td>
								<input type="file" name="p">
							</td>
						</tr>
						<tr><td></td></tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" class="submit"></td>
						</tr>
					</table>	
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