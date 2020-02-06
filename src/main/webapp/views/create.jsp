<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<h1 class="text-center">User Management</h1>
				<a href="new" class="btn btn-primary">Add New User</a>
				&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}"
					class="btn btn-success">List All Users</a>
				<hr>
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="Enter User Name" name="name"
						value="<c:out value='${user.name}' />" />
				</div>

				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="Enter User Email" name="email"
						value="<c:out value='${user.email}' />" />
				</div>

				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="Enter User Country" name="country"
						value="<c:out value='${user.country}' />" />
				</div>

				<input type="submit" value="Save" class="btn btn-primary" />
				</form>
			</div>
		</div>
	</div>


</body>
</html>

