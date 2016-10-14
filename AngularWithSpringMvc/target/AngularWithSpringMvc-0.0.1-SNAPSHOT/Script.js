var app = angular.module('myApp', []).controller('myCtrl',
  function($scope) {
    var employees = [
                      {firstname:"Midhun",lastname:"M",gender:"Male"},
                      {firstname:"Munna",lastname:"M",gender:"Male"},
                      {firstname:"Ram",lastname:"R",gender:"Male"},
                      {firstname:"Raju",lastname:"M",gender:"Male"},
                     ];
    
    $scope.employees= employees;

  });