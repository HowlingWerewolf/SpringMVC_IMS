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
		<fmt:message key="pages.priceincrease" />
	</h1>
	
	<form:form method="POST" commandName="priceincrease">
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td align="right" width="20%">Increase (%):</td>
				<td width="20%"><form:input path="percentage" /></td>
				<td width="60%"><form:errors path="percentage" cssClass="error" />
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="Increase">
	</form:form>

	<%@ include file="/WEB-INF/pages/import/js.jsp"%>
</body>
</html>