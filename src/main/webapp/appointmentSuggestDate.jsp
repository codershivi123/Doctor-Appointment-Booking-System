<%@page import="java.util.*"%>
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

			<form id="appointmentSuggestDateForm" method="post"
				action="confirmAppointment">
				<div class="form-group">
					<label for="gender">Gender</label> <input type="text"
						class="form-control" id="gender" name="gender"
						value="<%=session.getAttribute("gender")%>" readonly />
				</div>
				<div class="form-group">
					<label for="age">Age</label> <input type="number"
						class="form-control" id="age" name="age"
						value="<%=session.getAttribute("age")%>" readonly />
				</div>
				<div class="form-group">
					<label for="bloodGroup">Blood Group</label> <input type="text"
						class="form-control" id="bloodGroup" name="bloodGroup"
						value="<%=session.getAttribute("bloodGroup")%>" readonly />
				</div>

				<div class="form-group">
					<label for="address">Address</label>
					<textarea class="form-control" id="address" name="address" rows="3"
						readonly><%=session.getAttribute("address")%></textarea>

				</div>

				<div class="form-group">
					<label for="doctor">Doctor</label> <input type="text"
						class="form-control" id="doctor" name="doctor"
						value="<%=session.getAttribute("doctor")%>" readonly />
				</div>


				<div class="form-group">
					<label for="preferredDate">Preferred Date for the
						Appointment</label> <input type="date" class="form-control"
						id="preferredDate" name="preferredDate"
						value="<%=session.getAttribute("preferredDate")%>" readonly />
				</div>

				<div class="form-group">
					<label for="suggestedTime">Suggested Time for the
						Appointment</label> <select class="form-control" id="suggestedTime"
						name="suggestedTime" required>
						<option value="">Select Time</option>
						<%
						@SuppressWarnings("unchecked")
						List<String> availableTimes = (List<String>) session.getAttribute("availableTimes");
						for (String availableTime : availableTimes) {
						%>
						<option value="<%=availableTime%>"><%=availableTime%></option>
						<%
						}
						%>
					</select>
				</div>

				<div class="form-group">
					<label for="appointmentReason">Reason for the Appointment</label>
					<textarea class="form-control" id="appointmentReason"
						name="appointmentReason" rows="3"
						placeholder="Enter Reason for Appointment" readonly><%=session.getAttribute("appointmentReason")%></textarea>
				</div>

				<button type="submit" class="btn btn-primary btn-block">
					Check For Appointment</button>

			</form>
		</div>
	</div>
	<%
	} catch (Exception e) {
	response.sendRedirect("error.jsp");
	}
	%>
</body>
</html>
