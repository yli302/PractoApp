<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	This is Patient Sign up
	<br>
	<%
		String errorMassage = (String) request.getAttribute("error");
	if (errorMassage != null) {
	%>
	<p style="color: red"> <%=errorMassage%> <p><br>
	<%
		}
	%>
	<form action="/PractoApp/signup/patient" method="post">
		Username: <input type="text" name="username" required><br>
		Password: <input type="password" name="password" required><br> 
		Phone Number: <input type="number" name="phone"><br>
		Email: <input type="email" name="email"><br>
		<input type="submit" value="Sign up">
	</form>
</body>
</html>