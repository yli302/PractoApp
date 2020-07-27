<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.java.dto.Doctor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% List<Doctor> list = (List<Doctor>)request.getAttribute("list");%>
	
	<table border="1">
		<tr>
			<td width="50px">Id</td>
			<td width="100px">Name</td>
			<td width="150px">Speciality</td>
			<td width="100px">City</td>
			<td>Make An Appointment</td>
		</tr>
		<% if (list == null || list.size() == 0) {%>
			<tr>
				<td colspan="5"><h3>No Doctor Matches Your Request</h3></td>
			<tr>
		<%} else {
			for (Doctor doctor : list) {%>
				<tr>
					<td><%=doctor.getId()%></td>
					<td><%=doctor.getName()%></td>
					<td><%=doctor.getSpeciality()%></td>
					<td><%=doctor.getAddress().getCity()%></td>
					<td><a href="search/appointment/<%=doctor.getId()%>">Appointment</a></td>
				</tr>
		<%	}
    	  }
		%>
	</table>
</body>
</html>