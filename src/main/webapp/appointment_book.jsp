<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.container {
	text-align: center;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	display: flex;
	flex-direction: column;
	align-items: center;
}

.gif {
	width: 200px;
	height: 200px;
	margin-bottom: 20px;
}

.button {
	padding: 12px 24px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	text-decoration: none;
	transition: background-color 0.3s ease;
}

.button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<img src="assets/image/success.gif"
			alt="Booking Confirmed" class="gif">
		<h2 style="color: #333;">Booking Confirmed!</h2>
		<h3>Kindly Check your Email for Appointment Details...</h3>
		<br> <a href="patient_dashboard.jsp" class="button">Go to
			Dashboard</a> <br>
		<h3>Thank You for booking with MediSlot !!!</h3>
	</div>
</body>
</html>
