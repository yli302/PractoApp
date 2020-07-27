<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	This is Doctor Sign up
	<br>
	<%
		String errorMassage = (String) request.getAttribute("error");
	if (errorMassage != null) {
	%>
	<p style="color: red"> <%=errorMassage%> <p><br>
	<%
		}
	%>
	<form action="/PractoApp/signup/doctor" method="post">
		Username: <input type="text" name="username" required><br> 
		Password: <input type="password" name="password" required><br> 
		Name: <input type="text" name="name" required><br> 
		Speciality: <input type="checkbox" name="speciality" value="aologist">Aologist
			<input type="checkbox" name="speciality" value="bologist">Bologist
			<input type="checkbox" name="speciality" value="cologist">Cologist
			<input type="checkbox" name="speciality" value="dologist">Dologist<br>
		Hno: <input type="number" name="hno"><br>
		Street: <input type="text" name="street"><br>
		City: <input type="text" name="city" required><br>
		<input type="submit" value="Sign up">
	</form>
</body>
</html>