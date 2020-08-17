<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addTimeSlot" method="post">
	Time Version: yyyy-MM-dd HH:mm<br>
	Start time: <input type="text" name=startTime required> 
	End time: <input type="text" name=endTime required><br>
	<input type="submit" value="add New Time Slot">
</form>
<table border="1">
		<tr>
			<td width="100px">Start Time</td>
			<td width="100px">End Time</td>
			<td width="60px">Delete</td>
		</tr>
	</table>
</body>
</html>