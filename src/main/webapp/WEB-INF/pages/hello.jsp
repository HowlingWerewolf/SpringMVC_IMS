<%@ include file="/WEB-INF/pages/include.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/pages/import/css.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/pages/import/navbar.jsp"%>

	<h1 class="mb-4">
		<fmt:message key="pages.products" />
	</h1>

	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Product Name</th>
				<th scope="col">Price</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${model.products}" var="prod">
				<tr>
					<td><c:out value="${prod.description}" /></td>
					<td>$<c:out value="${prod.price}" />
					</td>
			</c:forEach>
		</tbody>
	</table>

	<%@ include file="/WEB-INF/pages/import/js.jsp"%>

</body>
</html>