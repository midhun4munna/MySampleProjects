var app = angular.module('MyApp', []);
	
app.filter('reverse',[function(){
	    return function(string){
	           return string.split('').reverse().join('');
			}
		}]);
		
app.controller('CalculatorContoller',function($scope){
		var calcCtrl = this;
			$scope.add = function(){
				$scope.sum = $scope.num1 + $scope.num2;
			}
			
		calcCtrl.sub = function(x,y) {
			return x - y;
		}
		});
		
app.factory('playerService',function(){
		var data = [{"Id":"1","Name":"Sachin"},{"Id":"2","Name":"Kohli"}];
		var service = {};
			service.getPlayers = function(){
				return data;
			}
			
		return service;
		});
		
app.factory('SampleService',function($http){
		var urlBase  = "http://localhost/8888";
		var service = {};
			service.getPlayers = function(){
				return $http.get(urlBase+'/players');
			}
			
		return service;
		});