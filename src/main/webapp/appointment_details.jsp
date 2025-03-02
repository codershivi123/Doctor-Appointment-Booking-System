<%@page import="java.util.List"%>
<%@page import="com.mediSlot.dao.*"%>
<%@page import="com.mediSlot.util.DBConnection"%>
<%@page import="com.mediSlot.model.*"%>
<%@page import="com.mediSlot.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f7f7f7;
}

.container {
	background-color: #ffffff;
	margin-top: 20px;
	padding: 20px;
	border-radius: 12px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #2c3e50;
	text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	border-radius: 12px;
	overflow: hidden;
	border: 2px solid #2c3e50;
}

th, td {
	padding: 20px;
	border-bottom: 1px solid #ddd;
	text-align: left;
	transition: background-color 0.3s ease;
}

th {
	background-color: #2b92ff;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #dbdbdb;
}
</style>
</head>
<body>
	<%
	try {
		DBConnection dbConnection = DBConnection.getDbConnection();
		AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
		DoctorDao doctorDao = new DoctorDao(dbConnection);

		AppointmentService appointmentService = new AppointmentService(appointmentDao);
		DoctorService doctorService = new DoctorService(doctorDao);

		String phoneNo = (String) session.getAttribute("phoneNo");
		Doctor doctor = doctorService.findByPhoneNo(phoneNo);
		int doctorID = doctor.getDoctorId();
		List<Appointment> appointments = appointmentService.findByDoctorId(doctorID);
	%>
	<div class="container">
		<%
		if (appointments != null) {
		%>
		<h1 align="center">Appointment Details</h1>
		<table>
			<thead>
				<tr>
					<th>Appointment ID</th>
					<th>Patient Name</th>
					<th>Gender</th>
					<th>Age</th>
					<th>Blood Group</th>
					<th>Address</th>
					<th>Appointment Date</th>
					<th>Appointment Time</th>
					<th>Appointment Reason</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Appointment appointment : appointments) {
				%>
				<tr>
					<td><%=appointment.getAppointmentID()%></td>
					<td><%=appointment.getPatient().getFullName()%></td>
					<td><%=appointment.getGender()%></td>
					<td><%=appointment.getAge()%></td>
					<td><%=appointment.getBloodGroup()%></td>
					<td><%=appointment.getAddress()%></td>
					<td><%=appointment.getAppointmentDate()%></td>
					<td><%=appointment.getAppointmentTime()%></td>
					<td><%=appointment.getAppointmentReason()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<h1 style="color: #2b92ff; text-align: center">YOU NOT HAVE ANY
			APPOINTMENT</h1>
		<%
		}
		} catch (Exception e) {
		response.sendRedirect("error.jsp");
		}
		%>
	</div>

</body>
</html>
