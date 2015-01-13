var myApp = angular.module("PersonalTrainer",[]);

var searchName = element(by.model('comp.name'));
searchName.clear();

myApp.controller("searchCtrl",function($scope, $http){
	$http.get('http://localhost:8888/sports').
		success(function(data, status, headers, config) {
		  $scope.sports = data;
		}).
		error(function(data, status, headers, config) {
		  console.log("Youyou ça marche pas");
		});
});

