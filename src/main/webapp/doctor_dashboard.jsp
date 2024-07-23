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
<html lang="en">
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
	border: 1px solid rgb(242, 242, 242);
	border-radius: 0.6rem;
	padding: 0.2rem;
	margin: 1rem;
	transition: transform 0.3s ease;
}

.navbar-nav .nav-link:hover {
	border-color: rgba(56, 75, 179, 0.5);
	transform: translateY(-3px);
}

.feature {
	background-color: #ffffff;
	padding: 20px;
	flex-grow: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
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
	transition: transform 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
	transform: scale(1.05);
}

.footer {
	background-color: #2b92ff;
	padding: 20px 0;
	margin-top: auto;
	text-align: center;
	width: 100%;
}

.footer span {
	color: #ffffff;
}
</style>
</head>
<body>
	<%
	try {

		DBConnection dbConnection = DBConnection.getDbConnection();
		DoctorDao doctorDao = new DoctorDao(dbConnection);
		DoctorService doctorService = new DoctorService(doctorDao);
		Doctor doctor = doctorService.findByPhoneNo(phoneNo);
	%>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<p class="navbar-brand"
				style="font-family: cursive; margin-top: 16px">MediSlot</p>
			<div class="container">
				<div class="navbar-collapse">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item nav-link"
							style="border: 0px; font-weight: bolder; font-size: 1.2rem;">Welcome,
							<%=doctor.getDoctorName()%></li>

						<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<div id="appointments" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Appointments</h2>
					<p>View and manage your appointments here.</p>
					<a href="appointment_details.jsp" class="btn btn-primary">View
						Appointments</a>
				</div>
			</div>
		</div>
	</div>


	<div id="prescriptions" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Prescriptions</h2>
					<p>Manage prescriptions for your patients.</p>
					<a href="manage_prescription.jsp" class="btn btn-primary">Manage
						Prescriptions</a>
				</div>
			</div>
		</div>
	</div>

	<div id="scheduled-date" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Scheduled Date</h2>
					<p>Set or modify your scheduled date for appointments.</p>
					<a href="doctor_schedule_update.jsp" class="btn btn-primary">Set
						Scheduled Date</a>
				</div>
			</div>
		</div>
	</div>

	<div id="medical-records" class="feature">
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="text-align: center">
					<h2>Medical History</h2>
					<p>See medical history of your patient.</p>
					<a href="medical_history.jsp" class="btn btn-primary">See
						Medical History</a>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="container">
			<span>&copy; 2024 MediSlot. All rights reserved.</span>
		</div>
	</div>
	<%
	} catch (Exception e) {
	response.sendRedirect("error.jsp");
	}
	%>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
