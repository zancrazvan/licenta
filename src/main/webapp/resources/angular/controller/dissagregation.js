'use strict;'
angular.module('licenta').controller(
		'DissagregationController',
		function($scope, $http) {
			$scope.greeting = "interactiv? == ";
			$scope.colors = [ { // default
				"fillColor" : "rgba(255, 0, 0, 1)",
				"strokeColor" : "rgba(255,0,0,1)",
				"pointColor" : "rgba(255,0,0,1)",
				"pointStrokeColor" : "#0f0",
				"pointHighlightFill" : "#00f",
				"pointHighlightStroke" : "rgba(255,255,255,1)"
			} ];
			$scope.options = {

				mantainAspectRatio : false,
				title : {
					display : true,
					text : 'Custom Chart Title'
				}
			}

			console.log("created");
			$http.get('/chartToBeDissagregated').success(
					function(data, status, headers, config) {

						console.log(data);
						$scope.charts = [];
						var chart = {
							"series" : [],
							"labels" : [],
							"data" : []

						};

						var labels = [];
						var values = [];

						for (j = 0; j < 50; j++) {

							labels[j] = data.timeSlots[j].time;
							values[j] = data.timeSlots[j].power;
						}
						chart.series = [ 'Total' ];
						chart.labels = labels;
						chart.data = [ values ];

						for (i = 0; i < data.switches.length; i++) {
							var val = [];
							console.log(data.switches[i]);
							for (j = 0; j <= 50; j++) {
								if (j >= data.switches[i].time
										&& j < data.switches[i].time
												+ data.switches[i].runningTime
												- 1) {
									val[j] = data.switches[i].relativePower;
								}
							}
							chart.data.push(val);
							chart.series.push('aparat ' + i);
							console.log(chart.series);
						}
						$scope.charts = chart;
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
			$scope.step4 = false;
			$scope.step0 = true;
			$scope.step1 = false;
			$scope.step2 = false;
			$scope.step3 = false;
			 

			$scope.showStep0 = function() {
				$scope.step4 = false;
				$scope.step0 = true;
				$scope.step1 = false;
				$scope.step2 = false;
				$scope.step3 = false;
				$(window).scrollTop(0);
				console.log("step1");
				 
			};
			$scope.showStep1 = function() {
				$scope.step0 = false;
				$scope.step1 = true;
				$scope.step2 = false;
				$scope.step3 = false;
				$(window).scrollTop(0);
				console.log("step1");
				 
			};
			$scope.showStep2 = function() {
				$scope.step0 = false;
				$scope.step1 = false;
				$scope.step2 = true;
				$scope.step3 = false;
				$(window).scrollTop(0);
				console.log("step2");
				 
			};
			$scope.showStep3 = function() {
				$scope.step0 = false;
				$scope.step1 = false;
				$scope.step2 = false;
				$scope.step3 = true;
				$(window).scrollTop(0);
				console.log("step3");
				 
			};
			$scope.showStep4 = function() {
				$scope.step0 = false;
				$scope.step1 = false;
				$scope.step2 = false;
				$scope.step3 = false;
				$scope.step4 = true;
				$(window).scrollTop(0);
				console.log("step3");
				 
			};
			 

		});