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
	<h1>
		<fmt:message key="productdelete.heading" />
	</h1>
	<c:forEach items="${model.products}" var="prod">
		<form:form method="POST" commandName="productdelete">
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
				cellpadding="5">
				<form:hidden path="id" value="${prod.id}" />
				<tr>

					<td align="right" width="20%">Description (name of product):</td>
					<td width="20%"><form:input path="description"
							value="${prod.description}" /></td>
					<td align="right" width="20%">Price:</td>
					<td width="20%"><form:input path="price" value="${prod.price}" />
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" align="center" value="Delete">
		</form:form>
	</c:forEach>

	<%@ include file="/WEB-INF/pages/import/js.jsp"%>
</body>
</html>