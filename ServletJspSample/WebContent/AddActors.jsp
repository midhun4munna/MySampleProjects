<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get User Details</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$(document)
								.on(
										'click',
										'.getUser',
										function() {
											$
													.ajax({
														type : "GET",
														url : "AddActorOrFilm",
														data : $("#uname")
																.serialize(),
														success : function(msg) {
															obj = JSON
																	.parse(msg);

															$('#id01')
																	.html(
																			'FirstName:<font color="red">'
																					+ obj.fName
																					+ '</font>');
															$('#id02')
																	.html(
																			'LastName:<font color="red">'
																					+ obj.lName
																					+ '</font>');
															$('#id03')
																	.html(
																			'Gender:<font color="red">'
																					+ obj.gender
																					+ '</font>');
															$("#uname").get(0)
																	.reset();
														}
													});
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

.div1 {
	background-color: #ffffb3;
	width: 500px;
	height: 100px;
	border: 3px solid black;
	text-align: center;
}

button {
	background-color: #70db70;
}
</style>
</head>
<body>
	<h1 style="color: green; margin-left: 0px; text-align: left;">Add
		Actor</h1>
	<div class="div1">
		<font size="5" color="#000000">Actor:</font><br> <input
			class="txtbox" type="text" name="username" id="uname"> <br>
		<br>
		<button name="Submit" id="userButton" class="getUser">Add</button>
	</div>
	<p id="result"></p>
	<div id="id01"></div>
	<div id="id02"></div>
	<div id="id03"></div>

</body>
</html>