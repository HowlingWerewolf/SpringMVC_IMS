<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/pages/import/css.jsp"%>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/pages/import/navbar.jsp"%>

	<h1 class="mb-4">
		<fmt:message key="pages.productdelete" />
	</h1>

	<table class="table table-striped">
		<c:forEach items="${model.products}" var="prod">
			<form:form method="POST" commandName="productdelete">
				<form:hidden path="id" value="${prod.id}" />
				<tr>
					<td align="right" width="20%">Description (name of product):</td>
					<td width="20%"><c:out value="${prod.description}" /></td>
					<td align="right" width="20%">Price:</td>
					<td width="20%">$<c:out value="${prod.price}" /></td>
					<td width="20%"><input type="submit" value="Delete"></td>
				</tr>
			</form:form>
		</c:forEach>
	</table>

	<%@ include file="/WEB-INF/pages/import/js.jsp"%>
</body>
</html>