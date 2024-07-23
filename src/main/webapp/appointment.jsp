<%@page import="com.mediSlot.dao.DoctorScheduleDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mediSlot.dao.DoctorDao"%>
<%@page import="com.mediSlot.model.Doctor"%>
<%@page import="com.mediSlot.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<style>
body {
	background-color: #eaf0f6; /* Light blue background */
	padding-top: 40px;
}

.form-container {
	max-width: 600px;
	margin: auto;
	background-color: rgba(255, 255, 255, 0.8);
	/* Semi-transparent white form background */
	padding: 40px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.form-container h2 {
	color: #007bff; /* Blue header text */
	text-align: center;
	margin-bottom: 30px;
}

.form-container .form-group label {
	color: #007bff; /* Blue label text */
	font-weight: bold;
}

.form-container .form-group {
	margin-bottom: 20px; /* Add margin to the bottom */
}

.form-container .btn-primary {
	background-color: #007bff; /* Blue button background */
	border-color: #007bff;
	font-weight: bold;
	transition: background-color 0.3s;
}

.form-container .btn-primary:hover {
	background-color: #0056b3; /* Darker blue on hover */
	border-color: #0056b3;
}

.error-message {
	color: #dc3545; /* Red error message text */
	font-size: 14px;
}
</style>
</head>
<body>
	<%
	try {
	%>
	<div class="container">
		<div class="form-container">
			<h2>Appointment Form</h2>
			<%
			String error = (String) session.getAttribute("timeError");
			if (error != null) {
			%>
			<p style="color: red; display: flex; justify-content: center"><%=error%></p>
			<%
			session.removeAttribute("loginError");
			}
			%>
			<form id="appointmentForm" method="post" action="checkDate">
				<div class="form-group">
					<label for="gender">Gender</label> <select class="form-control"
						id="gender" name="gender" required>
						<option value="">Select Gender</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Other">Other</option>
					</select>
				</div>
				<div class="form-group">
					<label for="age">Age</label> <input type="number"
						class="form-control" id="age" name="age" min="1"
						placeholder="Enter Age" required />
				</div>
				<div class="form-group">
					<label for="bloodGroup">Blood Group</label> <select
						class="form-control" id="bloodGroup" name="bloodGroup" required>
						<option value="">Select Blood Group</option>
						<option value="A+">A+</option>
						<option value="A-">A-</option>
						<option value="B+">B+</option>
						<option value="B-">B-</option>
						<option value="O+">O+</option>
						<option value="O-">O-</option>
						<option value="AB+">AB+</option>
						<option value="AB-">AB-</option>
					</select>
				</div>

				<div class="form-group">
					<label for="address">Address</label>
					<textarea class="form-control" id="address" name="address" rows="3"
						placeholder="Enter Address" required></textarea>
				</div>

				<%
				DBConnection dbConnection = DBConnection.getDbConnection();
				DoctorDao doctorDao = new DoctorDao(dbConnection);
				List<Doctor> doctors = doctorDao.findAll();
				%>
				<div class="form-group">
					<label for="doctor">Doctor</label> <select class="form-control"
						id="doctor" name="doctor" required>
						<option value="">Select Doctor</option>
						<%
						for (Doctor doctor : doctors) {
						%>
						<option value="<%=doctor.getDoctorId()%>"><%=doctor.getDoctorName()%></option>
						<%
						}
						%>
					</select>
				</div>


				<div class="form-group">
					<label for="preferredDate">Preferred Date for the
						Appointment</label> <input type="date" class="form-control"
						id="preferredDate" name="preferredDate"
						placeholder="Select Preferred Date" required />
				</div>

				<%
				DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
				List<String> availableTimes = doctorScheduleDao.findAllTime();
				%>
				<div class="form-group">
					<label for="preferredTime">Preferred Time for the
						Appointment</label> <select class="form-control" id="preferredTime"
						name="preferredTime" required>
						<%
						if (availableTimes != null) {
						%><option value="">Select Time</option>
						<%
						for (String availableTime : availableTimes) {
						%>
						<option value="<%=availableTime%>"><%=availableTime%></option>
						<%
						}
						} else {
						%>
						<option value="#">No time available</option>
						<%
						}
						%>
					</select>

				</div>

				<div class="form-group">
					<label for="appointmentReason">Reason for the Appointment</label>
					<textarea class="form-control" id="appointmentReason"
						name="appointmentReason" rows="3"
						placeholder="Enter Reason for Appointment" required></textarea>
				</div>

				<button type="submit" class="btn btn-primary btn-block">
					Check For Appointment</button>

			</form>
		</div>
	</div>


	<script>
		// Get current date in YYYY-MM-DD format
		let currentDate = new Date().toISOString().split('T')[0];

		// Set the minimum date attribute of the date input
		document.getElementById('preferredDate').min = currentDate;
	</script>
	<%
	} catch (Exception e) {
	response.sendRedirect("error.jsp");
	}
	%>
</body>
</html>
