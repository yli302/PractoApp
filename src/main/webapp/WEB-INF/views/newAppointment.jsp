<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Map" %>
 <%@ page import="com.java.dto.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%Doctor doctor = (Doctor) request.getAttribute("doctor");
	Map<String, Boolean> map =  doctor.getSchedule();%>
	Schedule an appointment with doctor #${doctor.id} ${doctor.username}:
	<form action="/PractoApp/search/appointment" method="post">
	<%for (Map.Entry<String, Boolean> entry : map.entrySet()) {%>
		<%if (entry.getValue() == false) {%>
			<input type="checkbox" name="time" value="<%=entry.getKey() %>"><%=entry.getKey() %><br>
		<%} %>
	<%} %>
	<%-- <%if (map.get("9:00am->12:00am") == null || map.get("9:00am->12:00am").booleanValue() == false) {%>
	<input type="checkbox" name="time" value="9:00am->12:00am">9:00am->12:00am <%=doctor %> <%} %>
	<%if (map.get("1:00pm->4:00pm") == null || map.get("1:00pm->4:00pm").booleanValue() == false) {%>
	<input type="checkbox" name="time" value="1:00pm->4:00pm">1:00pm->4:00pm <%} %>
	<%if (map.get("5:00pm->8:00pm") == null || map.get("5:00pm->8:00pm").booleanValue() == false) {%>
	<input type="checkbox" name="time" value="5:00pm->8:00pm">5:00pm->8:00pm <%} %> --%>
	<input type="hidden" name="doctorId" value=${doctor.id}>
	<input type="submit" value="make an appointment">
	</form>
</body>
</html>