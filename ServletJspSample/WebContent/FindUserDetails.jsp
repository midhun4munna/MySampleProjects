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
														url : "Register?type=single",
														data : $("#uname")
																.serialize(),
														success : function(msg) {
															console.log(msg);
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
														},
														error : function(msg) {
															console.log(msg);
														}
													});
										});

					});

	$(document)
			.ready(
					function() {
						$(document)
								.on(
										'click',
										'.getAllUser',
										function() {
											$
													.ajax({
														type : "GET",
														url : "Register?type=all",
														data : $("#uname")
																.serialize(),
														success : function(msg) {
															console.log(msg);
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
														},
														error : function(msg) {
															console.log(msg);
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

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<h1 style="color: green; margin-left: 0px; text-align: left;">Find
		User Details</h1>
	<%@ taglib uri="WEB-INF/mytags.tld" prefix="m"%>
	<%
		out.print("welcome to jsp page");
	 
	%>
	<br>
	<m:hitag/>  
	<div class="div1">
		<font size="5" color="#000000">UserName:</font><br> <input
			class="txtbox" type="text" name="username" id="uname"> <br>
		<br>
		<button name="Submit" id="userButton" class="getUser">Get
			User</button>
	</div>
	<p id="result"></p>
	<div id="id01"></div>
	<div id="id02"></div>
	<div id="id03"></div>
	<br>
	<br>
	<div>
		<button name="Submit" id="allUserButton" class="getAllUser">Get
			All User</button>
	</div>

	<div>
		<table>
			<tr>
				<th>Company</th>
				<th>State</th>
				<th>Country</th>
			</tr>
			<%
				for (int a = 1; a <= 5; a++) {
			%>
			<tr>
				<td><%="Acc"%></td>
				<td><%="Kerala"%></td>
				<td><%="India"%></td>
			</tr>
			<%
				}
			%>
		</table>

	</div>
</body>
</html>