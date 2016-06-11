<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
.chartWrapper {
    position: relative;
}

.chartWrapper > canvas {
    position: absolute;
    left: 0;
    top: 0;
    pointer-events:none;
}

.chartAreaWrapper {
    width: 600px;
    overflow-x: scroll;
}
</style>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/angular/resources/angular-chart.css">

<section ng-controller="allDevicesController" class="content">

	<h1>{{charts.length}}</h1>
	<!-- 	<div class="col-md-3"></div>
	<div class="col-md-6"> -->
	<div ng-repeat="chart in charts track by $index">
		<div class="row">
			<div style="overflow: scroll; overflow-y: hidden;">
				<div>
				
					<canvas
						style="max-height: 70vh; margin-left: 5%; margin-right: 5%;"
						id="line" class="chart chart-bar" chart-data="chart.data"
						chart-labels="chart.labels" chart-legend="true"
						chart-series="chart.series" chart-click="" chart-colours="colors"> </canvas>
				</div>
			</div>
		</div>
	</div>
	<!-- </div> -->

</section>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/Chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular-chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/controller/angularFunctions.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/controller/allCurvesController.js"></script>
</html>