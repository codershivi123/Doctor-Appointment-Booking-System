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
	max-width: 400px;
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

.login-link {
	text-align: center;
	margin-top: 20px;
}

.login-link a {
	color: #007bff; /* Blue login link text */
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s;
}

.login-link a:hover {
	color: #0056b3; /* Darker blue on hover */
}
</style>
</head>
<body>
	<div class="container">
		<div class="form-container">
			<h2>Doctor Registration</h2>
			<%
			String error = (String) session.getAttribute("regError");
			if (error != null) {
			%>
			<p style="color: red; display: flex; justify-content: center"><%=error%></p>
			<%
			session.removeAttribute("regError");
			}
			%>
			<form method=post action="doctorRegestration">
				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						class="form-control" id="name" name="name"
						placeholder="Enter your name" required />
				</div>
				<div class="form-group">
					<label for="specialization">Specialization</label> <input
						type="text" class="form-control" id="specialization"
						name="specialization" placeholder="Enter your specialization"
						required />
				</div>
				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" id="email" name="email"
						placeholder="Enter your email (eg :- acc@xyz.com)" required />
				</div>

				<div class="form-group">
					<label for="phone">Phone Number</label> <input type="tel"
						class="form-control" id="phone" name="phone" pattern="[1-9]{1}[0-9]{9}"
						placeholder="Enter your phone number (10 digits)" required />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password" pattern=".{6,}"
						placeholder="Enter your password (min. 6 characters)" required />
				</div>
				<button type="submit" class="btn btn-primary btn-block">
					Register</button>
			</form>
			<div class="login-link">
				<p>
					Already have an account? <a href="doctor_login.jsp">Login here</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
