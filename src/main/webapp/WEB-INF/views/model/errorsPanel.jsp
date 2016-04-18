<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errorMessages != null  && fn:length(errorMessages) > 0}">
	<c:forEach items="${errorMessages}" var="e">
		<span class="errorInfo"><fmt:message key="${e.key }"></fmt:message></span><br>
	</c:forEach>
</c:if>