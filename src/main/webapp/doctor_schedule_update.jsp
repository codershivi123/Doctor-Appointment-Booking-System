<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%@page import="com.mediSlot.service.*"%>
<%@page import="com.mediSlot.dao.DoctorDao"%>
<%@page import="com.mediSlot.model.Doctor"%>
<%@page import="com.mediSlot.model.DoctorSchedule"%>
<%@page import="java.util.List"%>
<%@page import="com.mediSlot.dao.DoctorScheduleDao"%>
<%@page import="com.mediSlot.util.DBConnection"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

.table {
	width: 100%;
	border-collapse: collapse;
	border-radius: 12px;
	overflow: hidden;
	border: 2px solid #2c3e50;
}

.table th, .table td {
	padding: 20px;
	border-bottom: 1px solid #ddd;
	text-align: left;
	transition: background-color 0.3s ease;
}

.thead-blue {
	border-radius: 12px;
	background-color: #2b92ff;
	color: #fff;
}

.update-btn {
	padding: 5px 10px;
	border-radius: 15px;
	background-color: #2b92ff;
	color: #fff;
	border: none;
	cursor: pointer;
}

.update-btn:hover {
	background-color: #0056b3;
}

tbody tr:hover {
	background-color: #dbdbdb;
}
</style>
</head>
<body>
	<%
	try {
		String phoneNo = (String) session.getAttribute("phoneNo");
		DBConnection dbConnection = DBConnection.getDbConnection();
		DoctorDao doctorDao = new DoctorDao(dbConnection);
		DoctorService doctorService = new DoctorService(doctorDao);
		Doctor doctor = doctorService.findByPhoneNo(phoneNo);

		int doctorId = doctor.getDoctorId();
		session.setAttribute("scheduledDoctorId", doctorId);
		DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);

		List<DoctorSchedule> doctorSchedules = doctorScheduleDao.findAllDoctorSchedulesById(doctorId);
	%>
	<div class="container">

		<div class="row">
			<div class="col">
				<h2>Schedule Table</h2>
			</div>
			<div class="col text-right">
				<button type="button" class="update-btn btn btn-primary"
					data-toggle="modal" data-target="#addScheduleModal">Add
					New Schedule</button>
			</div>
		</div>
		<%
		if (doctorSchedules != null) {
		%>
		<table class="table">
			<thead class="thead-blue">
				<tr>
					<th style="border-top-left-radius: 12px">Schedule Date</th>
					<th>Schedule Time</th>
					<th>Status</th>
					<th style="border-top-right-radius: 12px">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (DoctorSchedule doctorSchedule : doctorSchedules) {
				%>
				<tr>
					<td><%=doctorSchedule.getAvailableDate()%></td>
					<td><%=doctorSchedule.getAvailableTime()%></td>
					<%
					if (doctorSchedule.getBlockedTime().equals("Booked")) {
					%>
					<td style="color:green"><%=doctorSchedule.getBlockedTime()%></td>
					<%
					} else if (doctorSchedule.getBlockedTime().equals("Blocked")) {
					%><td style="color:red"><%=doctorSchedule.getBlockedTime()%></td>
					<%
					} else {
					%><td><%=doctorSchedule.getBlockedTime()%></td>
					<%
					}
					%>
					<td>
						<%
						if (doctorSchedule.getBlockedTime().equals("Available")) {
						%><button class="update-btn"
							onclick="fillForm('<%=doctorSchedule.getAvailableDate()%>', '<%=doctorSchedule.getAvailableTime()%>', '<%=doctorSchedule.getBlockedTime()%>')"
							data-toggle="modal" data-target="#exampleModal">Update</button> <%
 } else {
 %>
						<button class="update-btn"
							style="background-color: #a7a7a7; cursor: not-allowed;"
							onclick="fillForm('<%=doctorSchedule.getAvailableDate()%>', '<%=doctorSchedule.getAvailableTime()%>', '<%=doctorSchedule.getBlockedTime()%>')"
							data-toggle="modal" data-target="#exampleModal" disabled>Update</button>
					</td>
					<%
					}
					%>
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
			SCHEDULE</h1>
		<%
		}
		} catch (Exception e) {
		response.sendRedirect("error.jsp");
		}
		%>
	</div>

	<!-- Modal for updating schedule -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Update Schedule</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Form for updating schedule -->
					<form action="updateDoctorSchedule" method=post>
						<div class="form-group">
							<label for="scheduleDate">Schedule Date:</label> <input
								type="date" class="form-control" id="scheduleDate"
								name="scheduleDate">
						</div>
						<div class="form-group">
							<label for="scheduleTime">Schedule Time:</label> <input
								type="text" class="form-control" id="scheduleTime"
								name="scheduleTime">
						</div>
						<div class="form-group">
							<label for="status">Status:</label> <select class="form-control"
								id="status" name="status">
								<option value="Blocked">Blocked</option>
							</select>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save
								changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="addScheduleModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add New
						Schedule</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Form for add schedule -->
					<form action="addDoctorSchedule" method=post>
						<div class="form-group">
							<label for="newScheduleDate">Schedule Date:</label> <input
								type="date" class="form-control" id="newScheduleDate"
								name="newScheduleDate">
						</div>
						<div class="form-group">
							<label for="newScheduleTime">Schedule Time:</label> <input
								type="text" class="form-control" id="newScheduleTime"
								name="newScheduleTime"
								pattern="^(0[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$"
								placeholder="e.g 02:00 PM">
						</div>
						<div class="form-group">
							<label for="newScheduleStatus">Status:</label> <select
								class="form-control" id="newScheduleStatus"
								name="newScheduleStatus">
								<option value="Available">Available</option>
								<option value="Blocked">Blocked</option>
							</select>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Add
								Schedule</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS and jQuery -->
	<script>
		// Function to fill the modal form with schedule data
		function fillForm(scheduleDate, scheduleTime, status) {
			document.getElementById('scheduleDate').value = scheduleDate;
			document.getElementById('scheduleTime').value = scheduleTime;
			document.getElementById('status').value = status;
		}

		// Get current date in YYYY-MM-DD format
		let currentDate = new Date().toISOString().split('T')[0];

		// Set the minimum date attribute of the date input
		document.getElementById('newScheduleDate').min = currentDate;
	</script>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
