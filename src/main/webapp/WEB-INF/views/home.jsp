<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.java.dto.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Patient p = (Patient) session.getAttribute("patient");
	if (p != null) { %>
		Patient is <%=p.getUsername() %> <br>
	<%} %>
<%
	Doctor d = (Doctor) session.getAttribute("doctor");
	if (d != null) { %>
		Doctor is <%=d.getUsername() %> <br>
	<%} %>
<a href = "login/patient">Patient Login</a> |
<a href = "login/doctor">Doctor Login</a> |
<a href = "search">Search Doctor</a> |
<a href = "search/myAppointment">my Appointment</a>


<form action="signup/patient">
<input type="text" name="username">
<input type="submit">
</form>

</body>
</html>