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
	$(document)
			.ready(
					function() {

						$(document)
								.on(
										'click',
										'.submitform',
										function() {
											$
													.ajax({
														type : "POST",
														url : "Register",
														data : $("#register")
																.serialize(),
														success : function(msg) {
															console.log(msg);
															$('#result')
																	.html(
																			'<font color="red">'
																					+ msg
																					+ '</font>');
															$
																	.ajax({
																		type : "GET",
																		url : "Register?username=Midhun",
																		data : $(
																				"#register")
																				.serialize(),
																		success : function(
																				msg) {
																			$(
																					"#register")
																					.get(
																							0)
																					.reset();
																		}
																	});
														},
														error : function() {
															$('#result').html(
																	msg);
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

button {
	background-color: #70db70;
}
</style>

</head>
<body>
	<h1 style="color: green; margin-left: 30px; text-align: left;">Enter
		User Details</h1>
	<form id="register" name="ajaxform" method="POST">
		<font size="5" color="#000000">First name:</font><br> <input
			type="text" class="txtbox" name="firstname" id="fname1"> <br>
		<font size="5" color="#000000">Last name:</font><br> <input
			type="text" class="txtbox" name="lastname"> <br> <font
			size="5" color="#000000">Gender:</font><br> <input type="radio"
			name="gender" value="male" checked> Male <input type="radio"
			name="gender" value="female"> Female <br> <br>
		<button name="Submit" id="addUser" class="submitform">Add
			Address</button>
	</form>
	<p id="result"></p>

</body>
</html>