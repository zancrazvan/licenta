(function() {
	var app = angular.module('licenta', [ 'chart.js' ]);

	app.controller("TestController", function($scope, $http, $timeout) {
		var myData;

	 
		$http.get('/get-demo-data').success(
				function(data, status, headers, config) {
					$scope.data = data; // are date despre cine e logat
				 
					var curba  = data.curba;
					console.log("good");
					console.log($scope.data.curba.length);
					var labels = [];
					var values = [];
					for (i = 0; i < 50; i++) {
						var date= new Date(curba[i*100].date);
						labels[i] = date.toString();
						values[i] = curba[i*100].value;
					}
					console.log(labels);

					$scope.labels = labels;
					$scope.series = [ 'Series A' ];
					$scope.data = [ values ];
					$scope.onClick = function(points, evt) {
						console.log(points, evt);
					};
				}).error(function(data, status, headers, config) {
			console.log("first loggggg error");
		});

		/*$scope.labels = [ "January", "February", "March", "April", "May",
				"June", "July", "January", "February", "March", "April", "May",
				"June", "July" ];
		$scope.series = [ 'Series A', 'Series B' ];
		$scope.data = [
				[ 65, 59, 80, 81, 56, 55, 40, 65, 59, 80, 81, 56, 55, 40 ],
				[ 28, 48, 40, 19, 86, 27, 90, 28, 48, 40, 19, 86, 27, 1500 ] ];
		$scope.onClick = function(points, evt) {
			console.log(points, evt);
		};*/
	});
})();

function callAtRegister() {
	console.log("callReg");
	window.location = '/buddies';
}

function goGoogle(number, gen) {
	if (number === "000")
		console.log(number + gen + " lalala");
	else
		console.log(gen + " lalala");
	// window.location='www.google.com';
}

function goEvents(name) {
	console.log("mergem la eveniment: " + name);
	window.location = '/events/' + name;
}
