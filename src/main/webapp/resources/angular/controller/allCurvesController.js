'use strict;'
angular.module('licenta').controller(
		'allDevicesController',
		function($scope, $http) {
			$scope.colors = [ { // default
				"fillColor" : "rgba(224, 108, 112, 1)",
				"strokeColor" : "rgba(207,100,103,1)",
				"pointColor" : "rgba(220,220,220,1)",
				"pointStrokeColor" : "#fff",
				"pointHighlightFill" : "#fff",
				"pointHighlightStroke" : "rgba(151,187,205,0.8)"
			} ];
			console.log("created");
			$http.get('/getAllDevicesSignature').success(
					function(data, status, headers, config) {

						console.log(data);
						$scope.charts = [];
						var chart = {
							"series" : [],
							"labels" : [],
							"data" : []

						};

						for (i = 0; i < data.length; i++) {
							var labels = [];
							var values = [];
							var curba = data[i].curba;
							for (j = 0; j < data[i].curba.length; j++) {
								var date = new Date(curba[j].date);
								labels[j] = date.getHours() + ":"
										+ date.getMinutes() + ":"
										+ date.getSeconds();
								values[j] = curba[j].value;
							}
							chart.series = [ 'Series A' ];
							chart.labels = labels;
							chart.data = [ values ];
							$scope.charts.push(chart);
						}
						console.log($scope.charts);
						/*
						 * var curba = data.curba; console.log("good");
						 * console.log($scope.data.curba.length); var labels =
						 * []; var values = []; for (i = 0; i < 50; i++) { var
						 * date = new Date(curba[i * 100].date); labels[i] =
						 * date.toString(); values[i] = curba[i * 100].value; }
						 * console.log(labels);
						 * 
						 * $scope.labels = labels; $scope.series = [ 'Series A' ];
						 * $scope.data = [ values ];
						 */
						$scope.onClick = function(points, evt) {
							console.log(points, evt);
						};
					}).error(function(data, status, headers, config) {
				console.log("first loggggg error");
			});
		});