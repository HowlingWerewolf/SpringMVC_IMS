<%@ include file="/WEB-INF/pages/include.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/pages/import/css.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/pages/import/navbar.jsp"%>

	<h1>
		<fmt:message key="heading" />
	</h1>
	<p>
		<fmt:message key="greeting" />
		<c:out value="${model.now}" />
	</p>
	<h3>Products</h3>
	<c:forEach items="${model.products}" var="prod">
		<c:out value="${prod.description}" />
		<i>$<c:out value="${prod.price}" /></i>
		<br>
		<br>
	</c:forEach>
	<%@ include file="/WEB-INF/pages/import/js.jsp"%>

</body>
</html>