var app = angular.module('myApp', []); 

app.controller('todoCtrl', function($scope,ToDoService) {
    $scope.todoList = [];
    
    $scope.todoAdd = function() {
        var tododetail = $scope.todoInput;
        var status = $scope.status;
		var url =  'addtodo';
		$scope.todoInput = "";
		ToDoService.postTodo(url,tododetail,status);
    };
    
    $scope.getTodos = function() {
		var url =  'gettodo';
		ToDoService.getTodos(url);
    };

    $scope.remove = function(slno) {
        var url =  'removeTodo?slno='+slno;
        ToDoService.removeTodo(url);
    };
});

app.factory('ToDoService', function($http,$rootScope){
	var todoObj = {};
	todoObj.todos= [];
	$rootScope.todos = todoObj.todos;
	
	
	todoObj.getTodos = function(url){
			$http.get(url).then(
			function(res) {
				$rootScope.todos = res.data;
			});
	}
	
	todoObj.postTodo = function(url,todoDetail,status){
		var dataObj = {
				"todoDetail" : todoDetail,
				"status" : status
		};
		$http.post(url, dataObj).then(
				function(res) {
		$rootScope.todos = res.data;
				});
	}
	
	todoObj.removeTodo = function(url){
			$http.delete(url).then(
			function(res) {
				$rootScope.todos = res.data;
			});
	}
	
	return todoObj;
	});