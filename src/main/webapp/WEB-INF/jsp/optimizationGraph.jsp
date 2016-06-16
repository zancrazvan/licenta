<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../layout/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<%-- <script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization
				.arrayToDataTable([
						[ 'Generation Number', 'Generation Fitness' ],
<%ArrayList<Double> fitnessEvolution = (ArrayList) request.getAttribute("fitnessEvolution");
			for (int i = 0; i < fitnessEvolution.size(); i++) {%>
	[
<%=i%>
	,
<%=fitnessEvolution.get(i)%>
	],
<%}%>
	]);

		var options = {
			title : 'Fitness Evolution',
			curveType : 'function',
			legend : {
				position : 'bottom'
			}
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('curve_chart'));

		chart.draw(data, options);
	}
</script> --%>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization
				.arrayToDataTable([
						[ 'Time Slot', 'DSO Curve', 'Solution' ],
						<%ArrayList<Double> dso = (ArrayList) request.getAttribute("dso");
			ArrayList<Double> solution = (ArrayList) request.getAttribute("solution");
			for (int i = 0; i < dso.size(); i++) {
				for (int j = 0; j < solution.size(); j++) {
									
									if(i==j)%>
									
							[	<%=i%>,<%=dso.get(i)%>,<%=solution.get(i)%>],
						<%}%>
						<%}%>
							]);

		var options = {
			title : 'Solution',
			curveType : 'function',
			legend : {
				position : 'bottom'
			}
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('curve_chart2'));

		chart.draw(data, options);
	}
</script> 


</head>
<body>
	<div id="curve_chart" style="width: 100%; height: 500px"></div>
	<div id="curve_chart2" style="width: 100%; height: 500px"></div>
</body>
