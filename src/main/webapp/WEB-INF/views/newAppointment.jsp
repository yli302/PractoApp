<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Schedule an appointment with doctor #${doctorId}:
	<form action="/PractoApp/search/appointment" method="post">
	<input type="datetime-local" name="time" required>
	<input type="hidden" name="doctorId" value=${doctorId}>
	<input type="submit" value="make an appointment">
	</form>
</body>
</html>