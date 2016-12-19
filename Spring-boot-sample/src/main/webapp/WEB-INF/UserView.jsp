<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	 src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>

<script>
	var app = angular.module('myApp', ['ngRoute']);

	app.config(function($routeProvider) {
		$routeProvider.when('/modify', {
			templateUrl : 'ModifyUser.jsp',
			controller : 'modifyController'
		})
		.when('/add', {
			templateUrl : 'AddUser.jsp',
			controller : 'addController'
		});
	});
	
		app.factory('UserService', function($http){
			console.log("Inside UserService.......");
			var factory = {};
			factory.user = {};
			factory.users= [];
			
	        factory.setUser = function(fname,lname,gen) {
				factory.user.firstname = fname;
				factory.user.lastname = lname;
				factory.user.gender = gen;
				console.log("Completed  UserService setUser "+factory.user.gender);
			};
			
	        factory.getUser =function getUser() {
				return factory.user;
			};
			
			return factory;
			});
	
	app.controller('modifyController', function($scope,UserService,$rootScope,$http) {
		var modifyCtrl = this;
		var user = UserService.getUser();
		modifyCtrl.ftxt = user.firstname;
		modifyCtrl.ltxt = user.lastname;
		modifyCtrl.gtxt = user.gender;
		
		console.log("Inside modifyController "+modifyCtrl.gtxt);
		
	   modifyCtrl.updateUser = function(firstname, lastname, gender) {
			console.log("Inside Update firstname " + firstname + "lastname "
					+ lastname + "gender " + gender);
			$http.get(
					'updateUser?firstname=' + firstname + "&lastname="
							+ lastname + "&gender=" + gender).then(
					function(res) {
						console.log(res.data);
						$rootScope.users = res.data;
						console.log("user Updated");
					});
		}
	});
	
	app.controller('addController', function($scope,$http,$rootScope) {
		console.log("inside addController");
		var addCtrl = this;		
		addCtrl.addUser = function(firstname, lastname, gender) {
			console.log("Inside AddUser firstname " + firstname + "lastname "
					+ lastname + "gender " + gender);
			$http.get(
					'addNewUserJpa?firstname=' + firstname + "&lastname="
							+ lastname + "&gender=" + gender).then(
					function(res) {
						console.log(res.data);
						$rootScope.users = res.data;
						console.log("user Updated");
					});
		}
		
	});
	
	app.controller('myCtrl', function($scope, $http,$rootScope,UserService) {
		var myCtrl = this;
		this.user =  {firstName:'',lastName:'',gender:''};
		this.users = [];

		$http.get('registerUser').then(function(res) {
			console.log(res.data);
			$rootScope.users = res.data;
			console.log("Inside registerUser......"+$rootScope.users)
		});

		myCtrl.modifyUser = function(user) {
			console.log("Inside Modify User Method"+user.firstName);
			UserService.setUser(user.firstName,user.lastName,user.gender);
		}

		myCtrl.removeUser = function(user) {
			console.log("Inside Remove firstname " + user.firstName
					+ "lastname " + user.lastName + "gender " + user.gender);
			$http.get(
					'removeUser?firstname=' + user.firstName + "&lastname="
							+ user.lastName + "&gender=" + user.gender).then(
					function(res) {
						console.log(res.data);
						$rootScope.users = res.data;
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
	<div ng-controller="myCtrl as ctrl" "form-group" >

		<div class="hdiv">
			<h1 style="font-family: Times New Roman; font-style: italic">
				<font color="#ff0066">USER MANAGEMENT</font>
			</h1>
			<div>
				<br>
				<div ng-view></div>
				<br> <br>
				
				<a href="#add" ><i class="fa fa-home"></i>ADDUSER</a>
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
								<td><a href="#modify" ng-click="ctrl.modifyUser(user)"><i class="fa fa-home"></i><font color="green">MODIFY </font></a><font color="#000000"> 
								    <a href="#remove" ng-click="ctrl.removeUser(user)"><i class="fa fa-home"></i><font color="red">REMOVE </font></a>
							    </td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
</body>
</html>