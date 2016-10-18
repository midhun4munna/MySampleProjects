<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script>
	var app = angular.module('myApp', []).controller('myCtrl',
			function($scope, $http) {
				$http.get('registerUser').then(function(res) {
					console.log(res.data);
					$scope.users = res.data;
					
	
				});
				
					$scope.malechked = "true";
					$scope.femalechked = "false";
					$scope.gendertxt = "male";
				
					$scope.modifyUser = function(user){
					console.log(user.firstName);
					$scope.fnametxt = user.firstName;
					$scope.lnametxt = user.lastName;
					$scope.gendertxt = user.gender;
					if(user.gender == "male"){
						$scope.malechked = "true";
						$scope.femalechked = "false";
					}else{
						$scope.malechked = "false";
						$scope.femalechked = "true";
					}
					}
					
					$scope.updateUser = function(firstname,lastname,gender){
					console.log("Inside Update firstname "+firstname+ "lastname "+lastname + "gender "+gender);
					$http.get('updateUser?firstname='+firstname+"&lastname="+lastname+"&gender="+gender).then(function(res) {
					console.log(res.data);
					$scope.users = res.data;
					console.log("user Updated");
				    });
					}
					
					$scope.addUser = function(firstname,lastname,gender){
					console.log("Inside AddUser firstname "+firstname+ "lastname "+lastname + "gender "+gender);
					$http.get('addNewUserJpa?firstname='+firstname+"&lastname="+lastname+"&gender="+gender).then(function(res) {
					console.log(res.data);
					$scope.users = res.data;
					console.log("user Updated");
				    });
					}
					
					$scope.removeUser = function(user){
					console.log("Inside Remove firstname "+user.firstName+ "lastname "+user.lastName + "gender "+user.gender);
					$http.get('removeUser?firstname='+user.firstName+"&lastname="+user.lastName+"&gender="+user.gender).then(function(res) {
					console.log(res.data);
					$scope.users = res.data;
					console.log("user Updated");
				    });
					}
					

			});
</script>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: separate;
	width: 70%;
}

td, th {
	border: 2px solid black;
	text-align: left;
	padding: 5px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
tr:nth-child(odd) {
	background-color: white;
}
body {
	background-color: #e6e6ff;
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
	width: 40%;
	height: 30px;
	border: 1px solid black;
}

form {
	background-color: #e6fffa;
	width: 60%;
	height: 280px;
	border: 1px solid black;
}

button {
	background-color: #00cccc;
}
.hdiv {
	background-color: #1ac6ff;
	width: 100%;
	height: 85px;
	border: 1px solid black;
}

</style>
</head>
<body ng-app="myApp">
	<div ng-controller="myCtrl" "form-group">
		
		<div class="hdiv"><h1 style="font-family: Times New Roman;font-style: italic"><font color="#ff0066">USER MANAGEMENT</font></h1><div><br>
		<div id ="formInput">
		<form id="register" name="form" method="POST" class="form-horizontal">
		<font size="5" color="#000000">First name:</font><br> <input
			type="text" class="form-control txtbox" name="firstname" id="fname1" ng-model="fnametxt"> <br>
		<font size="5" color="#000000">Last name:</font><br> <input
			type="text" class="form-control txtbox" name="lastname" ng-model="lnametxt"> <br> <font
			size="5" color="#000000">Gender:</font><br> 
		<input type="radio"
			name="gender" value="male" ng-checked="{{malechked}}" ng-model="gendertxt"> <font size="4" color="#000000">Male</font> <input type="radio"
			name="gender" value="female" ng-checked="{{femalechked}}" ng-model="gendertxt"> <font size="4" color="#000000">Female</font> <br> 
		<button name="Submit" id="addUser" class="btn btn-primary submitform" ng-click="addUser(fnametxt,lnametxt,gendertxt)">Add
			User</button>
		<button name="Submit" id="modifyUser" class="btn btn-primary  btnModify" ng-click="updateUser(fnametxt,lnametxt,gendertxt)">Modify
			User</button>
	</form>
	</div>
	<br>
	<br>
	  <div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>GenderName</th>
					<th>ChangeMe</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="user in users">
					<td>{{user.firstName}}</td>
					<td>{{user.lastName}}</td>
					<td>{{user.gender}}</td>
					<td><button name="Submit" id="modifyUser" class="btn btn-success modiftyBtn" ng-click="modifyUser(user)"><font color="#000000">Modify</font></button>
					<button name="Submit" id="removeUser" class="btn btn-danger removeBtn" ng-click="removeUser(user)" style="background-color: #ff704d">Remove</button></td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>

</body>
</html>