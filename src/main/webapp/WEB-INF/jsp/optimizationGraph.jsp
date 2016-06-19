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

					if (i == j)%>
									
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

<body>
	<!-- <div id="curve_chart" style="width: 100%; height: 500px"></div> -->
	<div id="curve_chart2" style="width: 100%; height: 500px"></div>
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">Data Table With Full Features</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Time Slot</th>
						<th>Device Name</th>
						<th>Power (W)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bin" items="${bestChromosom.bins}">
						<c:forEach var="element" items="${bin.elements}">
							<tr>
								<td>${bin.id}</td>
								<td>${ element.name}</td>
								<td>${ element.value}</td>

							</tr>
						</c:forEach>
					</c:forEach>

				</tbody>
				<tfoot>
					<tr>
						<th>Time Slot</th>
						<th>Device Name</th>
						<th>Power (W)</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->

</body>
<script
	src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script
	src="${pageContext.request.contextPath}/resources/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script
	src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script
	src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
<!-- page script -->
<script>
  $(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>