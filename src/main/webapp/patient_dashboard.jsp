<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@page import="com.mediSlot.dao.*"%>
<%@page import="com.mediSlot.model.*"%>
<%@page import="com.mediSlot.util.*"%>
<%@page import="com.mediSlot.service.*"%>

<%
String phoneNo = (String) session.getAttribute("phoneNo");
if (phoneNo == null) {
	response.sendRedirect("index.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<style>
/* Custom CSS styles */
body {
	font-family: Arial, sans-serif;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	background-color: #f8f9fa;
}

.navbar {
	background-color: #2b92ff;
	height: 55px;
	border-bottom: 2px solid #93c7ff;
}

.navbar-brand {
	font-size: 35px;
	font-weight: bold;
	color: #ffffff !important;
	justify-content: left;
	align-content: center;
}

.navbar-nav .nav-link {
	font-size: 15px;
	color: #ffffff !important;
	border: 1px solid rgb(242, 242, 242); /* Add border */
	border-radius: 0.6rem;
	padding: 0.2rem; /* Add padding */
	margin: 1rem;
	transition: transform 0.3s ease; /* Smooth transition on hover */
}

.navbar-nav .nav-link:hover {
	border-color: rgba(56, 75, 179, 0.5); /* Change border color on hover */
	transform: translateY(-3px); /* Move link up slightly */
}

.feature {
	background-color: #ffffff;
	padding: 20px;
	flex-grow: 1; /* Fill remaining space */
	display: flex;
	justify-content: center; /* Center horizontally */
	align-items: center; /* Center vertically */
	flex-direction: column; /* Stack items vertically */
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	margin: 20px;
}

.feature h2 {
	color: #007bff;
}

.feature p {
	font-size: 18px;
	line-height: 1.6;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
	transition: all 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
	transform: scale(1.05);
}


.footer {
	background-color: #2b92ff;
	padding: 20px 0;
	margin-top: auto; /* Push to bottom */
	text-align: center;
	width: 100%;
}

.footer span {
	color: #ffffff;
}
</style>
</head>
<body>
	<%try{
	if (session.getAttribute("phoneNo") == null) {
		response.sendRedirect("patient_login.jsp");
	}
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	PatientService patientService = new PatientService(patientDao);
	Patient patient = patientService.findByPhoneNo(phoneNo); 
	%>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<p class="navbar-brand" style="font-family: cursive; margin-top:16px">MediSlot</p>
			<div class="container">
				<div class="navbar-collapse">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item nav-link" style="border:0px; font-weight: bolder; font-size:1.2rem;">Welcome, <%=patient.getFullName()%></li>
						<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<div id="appointment" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Book an Appointment</h2>
					<p>Ready to schedule your appointment? Click below to book now!</p>
					<a href="appointment.jsp" class="btn btn-primary">Book
						Appointment</a>
				</div>
			</div>
		</div>
	</div>

	<div id="upcoming-appointments" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>All Appointments</h2>
					<p>See your all appointment.</p>
					<a href="patients_appointment.jsp" class="btn btn-primary">See
						Appointment</a>
				</div>
			</div>
		</div>
	</div>

	<div id="medical-records" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Medical Records</h2>
					<p>See your all medical records</p>
					<a href="medical_record.jsp" class="btn btn-primary">See
						Medical Record</a>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<span>&copy; 2024 MediSlot. All rights reserved.</span>
		</div>
	</div>
	<%} catch (Exception e) {
		response.sendRedirect("error.jsp");
		} %>
</body>
</html>