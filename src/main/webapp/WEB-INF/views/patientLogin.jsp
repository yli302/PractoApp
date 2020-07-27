<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	This is Patient Login <br>
	<%
	String massage = (String) request.getAttribute("massage");
	if (massage != null) {
	%>
		<p style="color: red"><%=massage%><p><br>
	<%}%>
	<form action="/PractoApp/login/patient" method="post">
		<input type="text" name="username" required> 
		<input type="password" name="password" required>
		<input type="submit" value="login">
	</form> <br>
	
	<a href = "/PractoApp/signup/patient">sign up</a>
</body>
</html>