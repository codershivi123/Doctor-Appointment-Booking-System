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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<style>
.navbar {
	background-color: #2b92ff;
	height: 55px;
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

.jumbotron {
	display: flex;
	align-items: center;
	justify-content: space-around; /* Center content horizontally */
	padding: 50px;
	margin: 0px;
	background-color: #f8f9fa; /* Change jumbotron background color */
	border-radius: 15px;
	box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1); /* Add shadow */
}

.content {
	max-width: 50%; /* Adjust content width */
	padding: 25px;
}

.content h2 {
	font-size: 3rem;
	font-weight: 550;
	color: #007bff; /* Change heading color */
}

.content h4 {
	font-size: 1.5rem;
	margin-bottom: 15px; /* Add some space below subheading */
	font-style: oblique;
	font-family: cursive;
}

.content p {
	font-size: 1.2rem;
	line-height: 1.6; /* Increase line height for better readability */
	text-align: justify;
}

.footer {
	background-color: #2b92ff;
	color: white;
	padding: 20px 0;
	margin-top: auto; /* Push to bottom */
	text-align: center;
	width: 100%;
}

.image-container {
	display: flex;
	flex-direction: column; /* Align images vertically */
	justify-content: center; /* Center images vertically */
	align-items: center; /* Center images horizontally */
	margin-bottom: 20px;
}

.image-container img {
	max-width: 90%; /* Reduce image width */
	height: auto;
	margin: 10px; /* Add margin between images */
	border-radius: 20px; /* Add border radius to images */
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1); /* Add shadow */
	animation: slideUp 1s ease;
	transition: transform 0.3s ease; /* Smooth transition on hover */
}

.image-container img:hover {
	transform: scale(1.05); /* Scale image on hover */
}
</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<p class="navbar-brand" style="font-family: cursive; margin-top:16px">MediSlot</p>
			<div class="container">
				<div class="navbar-collapse">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<div class="jumbotron">
		<div class="content text-center" style="color: black">
			<h2 class="display-4 mb-3">About Us</h2>
			<h4>Welcome to MediSlot - Your trusted partner in healthcare</h4>
			<p>Welcome to MediSlot, where we're on a mission to transform the
				way you access healthcare. Founded with a vision of making quality
				healthcare more accessible and convenient for everyone, MediSlot is
				your go-to destination for seamless appointment booking and
				healthcare management. At MediSlot, we understand the frustration
				and challenges that often accompany the traditional healthcare
				system. Long wait times, confusing appointment processes, and
				limited access to healthcare providers can make managing your health
				a daunting task. That's why we've made it our goal to simplify and
				streamline the entire healthcare experience. Our platform is
				designed with you in mind, putting the power of healthcare
				management directly into your hands. Whether you're scheduling a
				routine check-up, seeking specialized care, or simply in need of
				urgent medical attention, MediSlot is here to help. With a
				comprehensive network of trusted healthcare providers and a
				user-friendly interface, finding and booking appointments has never
				been easier. But MediSlot is more than just a booking platform.
				We're committed to fostering a community of health-conscious
				individuals who prioritize their well-being.</p>
		</div>
		<div class="image-container">
			<img src="assets/image/about_image.png" alt="Appointment Booking"
				class="img-fluid rounded" /> <img
				src="assets/image/index_image1.png" alt="Appointment Booking"
				class="img-fluid rounded" />
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<span>&copy; MediSlot. All rights reserved.</span>
		</div>
	</div>
</body>
</html>
