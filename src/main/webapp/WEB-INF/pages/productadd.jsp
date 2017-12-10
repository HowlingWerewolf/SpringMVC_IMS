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
		<fmt:message key="productadd.heading" />
	</h1>
	<form:form method="POST" commandName="productadd">
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td align="right" width="20%">Description (name of product):</td>
				<td width="20%"><form:input path="description" /></td>
				<td width="20%"><form:errors path="description"
						cssClass="error" /></td>
				<td align="right" width="20%">Price:</td>
				<td width="20%"><form:input path="price" /></td>
				<td width="20%"><form:errors path="price" cssClass="error" /></td>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="Execute">
	</form:form>
	<%@ include file="/WEB-INF/pages/import/js.jsp"%>
</body>
</html>