<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []).controller('myCtrl',
			function($scope, $http) {
				$http.get('registerUser').then(function(res) {
					console.log(res.data);
					$scope.users = res.data;
				});
			});
</script>
<style>
table {
	border-collapse: collapse;
}

table, th, td {
	border: 2px solid black;
}
</style>
</head>
<body ng-app="myApp">
	<div ng-controller="myCtrl">
	
		<form id="register" name="form" method="POST">
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
		<table>
			<thead>
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>GenderName</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="user in users">
					<td>{{user.firstName}}</td>
					<td>{{user.lastName}}</td>
					<td>{{user.gender}}</td>
				</tr>
			</tbody>
		</table>


	</div>

</body>
</html>