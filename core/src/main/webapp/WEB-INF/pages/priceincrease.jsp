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
		<fmt:message key="pages.priceincrease" />
	</h1>

	<form:form method="POST" commandName="priceincrease">
		<table class="table table-striped">
			<tr>
				<td align="right" width="20%">Increase (%):</td>
				<td width="20%"><form:input path="percentage" /></td>
				<td width="40%"><form:errors path="percentage" cssClass="error" />
				</td>
				<td width="20%"><input type="submit" value="Increase"></td>
			</tr>
		</table>
	</form:form>

	<%@ include file="/WEB-INF/pages/import/js.jsp"%>
</body>
</html>