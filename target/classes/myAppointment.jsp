<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.java.dto.Appointment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<% List<Appointment> list = (List<Appointment>)request.getAttribute("list");%>
	
	<table border="1">
		<tr>
			<td width="50px">Id</td>
			<td width="100px">patient</td>
			<td width="150px">doctor</td>
			<td width="100px">time</td>
			<td width="100px">rate</td>
			<td>Rate this appointment</td>
		</tr>
		<% if (list == null || list.size() == 0) {%>
			<tr>
				<td colspan="5"><h3>No Appointment Matches Your Request</h3></td>
			<tr>
		<%} else {
			for (Appointment appointment : list) {%>
				<tr>
					<td><%=appointment.getId()%></td>
					<td><%=appointment.getPatient().getUsername()%></td>
					<td><%=appointment.getDoctor().getUsername()%></td>
					<td><%=appointment.getTime()%></td>
					<form action="/PractoApp/search/rate" method="post">
					<td><input type="number" name="rate" value=<%=appointment.getRate()%>></td>
					<input type="hidden" name="id" value=<%=appointment.getId()%>>
					<td><input type="submit" value="Rate"></td>
					</form>
				</tr>
		<%	}
    	  }
		%>
	</table>
</body>
</html>