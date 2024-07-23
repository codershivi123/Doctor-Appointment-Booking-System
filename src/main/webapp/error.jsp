<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MediSlot</title>
<link href="assets/image/icon.png" rel="icon">
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
}

.container {
	text-align: center;
}

.btn {
	padding: 10px 20px;
	border: 2px solid #007bff;
	background-color: transparent;
	color: #007bff;
	text-decoration: none;
	border-radius: 5px;
	transition: all 0.3s ease;
}

.btn:hover {
	background-color: #007bff;
	color: #fff;
}
</style>
</head>
<body>
	<div class="container p-3">
		<img
			src="assets/image/error.png"
			class="img-fluid" />
		<h1 class="display-3">Sorry!!!...........Page not found</h1>
		<br> <br> <a class="btn btn-outline-primary"
			href="index.jsp">Home Page</a>
	</div>
</body>

</html>