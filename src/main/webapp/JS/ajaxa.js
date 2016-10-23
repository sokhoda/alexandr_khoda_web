var app = angular.module('myapp', []);

	app.controller("HelloCtrl", function ($scope, $http) {

		$scope.number = 5;
		$scope.randInt = 0;
		//$scope.greeting = "";
		//$scope.vect = $scope.getNumber($scope.number, 'ee');
        //
		$scope.getNumber = function(num, val) {
			var arr = new Array();
			for (var i = 0; i < num; i++) {
				arr.push(val);
			}
			return arr;
		};

		$scope.getNumber3 = function() {
			var arr = new Array();
			for (var i = 0; i < $scope.randInt; i++) {
				arr.push($scope.name);
			}

			return arr;
		};

		var getNumber4 = function() {
			var arr = new Array();
			for (var i = 0; i < $scope.randInt; i++) {
				arr.push($scope.name);
			}

			return arr;
		};

		$scope.update = function() {

		var note = $scope.name;
		$scope.greeting = $scope.name;
		var responsePromise = $http.get('/helloBodyKHO.html?login=' + $scope.name);

		responsePromise.success(
			function(data, status, headers, config) {
				$scope.randInt = data;
				$scope.vector = getNumber4();
			});
		responsePromise.error(
			function(data, status, headers, config) {
				alert('AJAX failed!');
			});
	}
});
