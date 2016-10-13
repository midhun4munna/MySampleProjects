<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="com.samples.servlets.dao.UserDAO"%>
<%@ page import="com.samples.servlets.Entity.UserDetails"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	border-collapse: separate;
	width: 100%;
}

td, th {
	border: 2px solid black;
	text-align: left;
	padding: 5px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<h1><font color="red">REGISTERED USER LIST</font></h1>
	<div>
		<table>
			<tr>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Gender</th>
			</tr>

			<%
				UserDAO userDao = new UserDAO();
				List<UserDetails> userList = userDao.getAllUser();
				System.out.println("UserList Size is : " + userList.size());
				for (int a = 0; a < userList.size(); a++) {
			%>
			<tr>
				<%
					System.out.println("UserList FirstName : " + userList.get(a).getFirstName() + " a " + a);
				%>
				<td><%=userList.get(a).getFirstName()%></td>
				<td><%=userList.get(a).getLastName()%></td>
				<td><%=userList.get(a).getGender()%></td>
			</tr>
			<%
				}
			%>
		</table>

	</div>
</body>
</html>