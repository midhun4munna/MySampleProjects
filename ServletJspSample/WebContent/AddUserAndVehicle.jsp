<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserRegistration Page</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$(document).on('click', '.submitform', function() {
			$.ajax({
				type : "POST",
				url : "AddUserAndVehicle",
				data : $("#register").serialize(),
				success : function(msg) {
					console.log(msg);
				},
				error : function() {

				}
			});
			event.preventDefault();
		}).on('click', '.addVehicleform', function() {
			$.ajax({
				type : "GET",
				url : "AddUserAndVehicle?reqType=addvehicle",
				data : $("#vehicleform").serialize(),
				success : function(msg) {
					console.log(msg);
				},
				error : function() {

				}
			});
			event.preventDefault();
		}).on('click', '.removeVehicleform', function() {
			$.ajax({
				type : "GET",
				url : "AddUserAndVehicle?reqType=removevehicle",
				data : $("#vehicleRemoveform").serialize(),
				success : function(msg) {
					console.log(msg);
				},
				error : function() {

				}
			});
			event.preventDefault();
		});

	});
</script>
<style>
body {
	background-color: #ffe6cc;
}

h1 {
	color: white;
	text-align: center;
}

p {
	font-family: verdana;
	font-size: 20px;
}

.txtbox {
	background-color: #ffe6ff;
	width: 150px;
	height: 25px;
	border: 3px solid black;
}

form {
	background-color: #ffffb3;
	width: 500px;
	height: 250px;
	border: 3px solid black;
	text-align: center;
}

.vehicleForm {
	background-color: #ff80aa;
	width: 500px;
	height: 400px;
	border: 3px solid black;
	text-align: center;
}

button {
	background-color: #70db70;
}
</style>

</head>
<body>
	<h1 style="color: green; margin-left: 30px; text-align: left;">Add
		User</h1>
	<form id="register" name="ajaxform" method="POST">
		<font size="5" color="#000000">First name:</font><br> <input
			type="text" class="txtbox" name="firstname" id="fname1"> <br>
		<font size="5" color="#000000">Last name:</font><br> <input
			type="text" class="txtbox" name="lastname"> <br> <font
			size="5" color="#000000">Gender:</font><br> <input type="radio"
			name="gender" value="male" checked> Male <input type="radio"
			name="gender" value="female"> Female <br> <br>
		<button name="Submit" id="addUser" class="submitform">Add
			User</button>
	</form>
	<h1 style="color: green; margin-left: 30px; text-align: left;">Add
		Vehicle</h1>
	<form id="vehicleform" name="ajaxform" method="POST"
		class="vehicleForm">
		<font size="5" color="#000000">UserName:</font><br> <input
			type="text" class="txtbox" name="firstname" id="fname2"> <br>
		<font size="5" color="#000000">Vehicle name:</font><br> <input
			type="text" class="txtbox" name="vehiclename"> <br> <font
			size="5" color="#000000">Vehicle Type:</font><br> <input
			type="text" class="txtbox" name="type"> <br> <font
			size="5" color="#000000">Company Name:</font><br> <input
			type="text" class="txtbox" name="company"> <br> <br>
		<button name="Submit" id="addVehicle" class="addVehicleform">Add
			Vehicle</button>
	</form>
	<h1 style="color: green; margin-left: 30px; text-align: left;">Remove
		Vehicle</h1>
	<form id="vehicleRemoveform" name="ajaxform" method="POST"
		class="vehicleRemoveForm">
		<font size="5" color="#000000">UserName:</font><br> <input
			type="text" class="txtbox" name="username" id="fname1"> <br>
		<font size="5" color="#000000">Vehicle Id:</font><br> <input
			type="text" class="txtbox" name="vehicleId"> <br> <br>
		<button name="Submit" id="removeVehicle" class="removeVehicleform">Remove
			Vehicle</button>
	</form>
	<p id="result"></p>

</body>
</html>