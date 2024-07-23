<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page session="true"%>

<%@page import="java.util.List"%>
<%@page import="com.mediSlot.dao.*"%>
<%@page import="com.mediSlot.util.DBConnection"%>
<%@page import="com.mediSlot.model.*"%>
<%@page import="com.mediSlot.service.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
	overflow-x: auto;
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

		TreatmentPlanDao treatmentPlanDao = new TreatmentPlanDao(dbConnection);
		TreatmentPlanService treatmentPlanService = new TreatmentPlanService(treatmentPlanDao);

		DoctorDao doctorDao = new DoctorDao(dbConnection);
		DoctorService doctorService = new DoctorService(doctorDao);

		String phoneNo = (String) session.getAttribute("phoneNo");
		Doctor doctor = doctorService.findByPhoneNo(phoneNo);
		int doctorID = doctor.getDoctorId();

		List<TreatmentPlan> treatmentPlans = treatmentPlanService.findAllRecordByDoctorId(doctorID);
	%>
	<div class="container">
		<%
		if (treatmentPlans != null) {
		%>
		<h1 align="center">Medical History</h1>
		<table>
			<thead>
				<tr>
					<th>Plan ID</th>
					<th>Patient Name</th>
					<th>Diagnosis Date</th>
					<th>Medical Condition</th>
					<th>Treatment</th>
					<th>Prescription</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (TreatmentPlan treatmentPlan : treatmentPlans) {
				%>
				<tr>
					<td><%=treatmentPlan.getPlanId()%></td>
					<td><%=treatmentPlan.getAppointment().getPatient().getFullName()%></td>
					<td><%=treatmentPlan.getAppointment().getAppointmentDate()%></td>
					<td><%=treatmentPlan.getDiagnosis()%></td>
					<td><%=treatmentPlan.getTreatment()%></td>
					<td><%=treatmentPlan.getPrescription()%></td>
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
			MEDICAL RECORD</h1>
		<%
		}
		} catch (Exception e) {
		response.sendRedirect("error.jsp");
		}
		%>
	</div>

</body>
</html>