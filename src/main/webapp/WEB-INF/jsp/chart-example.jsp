<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="licenta">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/angular/resources/angular-chart.css">
</head>
<body ng-controller="TestController">
<!-- 	<div class="col-md-3"></div>
	<div class="col-md-6"> -->
		<canvas id="line" class="chart chart-line" chart-data="data"
			chart-labels="labels" chart-legend="true" chart-series="series"
			chart-click="onClick"> </canvas>
	<!-- </div> -->

</body>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/Chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular-chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/angularFunctions.js"></script>
</html>