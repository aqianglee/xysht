<%@ include file="taglib.jsp"%>
<c:if test="${errorMessages != null  && fn:length(errorMessages) > 0}">
	<c:forEach items="${errorMessages}" var="e">
		<span class="errorInfo"><fmt:message key="${e.key }"></fmt:message></span>
		<br>
	</c:forEach>
</c:if>