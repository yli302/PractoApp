<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<f:form action="search" method="post" modelAttribute="doctorSearchCriteriaDto">
		<table>
			<tr>
				<td>Doctor Name</td>
				<td><input type="text" name="name" value=""></td>
			</tr>
			<tr>
				<td>Doctor City</td>
				<td><input type="text" name="city" value=""></td>
			</tr>
			<tr>
				<td>Doctor Speciality</td>
				<td><input type="text" name="speciality" value=""></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit"></td>
			</tr>
		</table>
	</f:form>
</body>
</html>