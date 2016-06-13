<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<section class="content" ng-controller="DissagregationController">
	<h1>{{greeting}}</h1>
	<div class="row">
		<div class="col-lg-6">
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">Id</th>
							<th>Nume</th>
							<th>Putere consumata</th>

						</tr>
						<c:forEach items="${devices}" var="d">
							<tr>
								<td>${d.id}</td>
								<td>${d.name}</td>
								<c:if test="${d.power<=200}">
									<td><span class="badge bg-green">${d.power}</span></td>
								</c:if>
								<c:if test="${d.power>200 && d.power <= 1000}">
									<td><span class="badge bg-blue">${d.power}</span></td>
								</c:if>
								<c:if test="${ d.power > 1000}">
									<td><span class="badge bg-red">${d.power}</span></td>
								</c:if>

							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<div class="col-lg-6">
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 25%">Timp pornire</th>
							<th style="width: 25%">Timp Rulare</th>
							<th style="width: 25%">Putere relativa</th>
							<th style="width: 25%">Putere absoluta</th>
						</tr>
						<c:forEach items="${switchingTimes}" var="d">
							<tr>
								<td>${d.time}</td>
								<td>${d.runningTime}</td>

								<td><span class="badge bg-green">${d.relativePower}</span></td>

								<td><span class="badge bg-blue">${d.absolutePower}</span></td>


							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<hr>

		<div class="col-lg-6">
			<h3>Solutia obtinuta are eroarea ${solutie.absoluteError}</h3>
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>Aparat</th>
							<th>Timp pornire</th>
							<th>Timp Rulare</th>
							<th>Putere relativa</th>
							<th>Putere aparat</th>
						</tr>
						<c:forEach items="${solutie.solution}" var="d">
							<tr>
								<td>${d.value.name}</td>
								<td>${d.key.time}</td>
								<td>${d.key.runningTime}</td>
								<td><span class="badge bg-green">${d.key.relativePower}</span></td>
								<td><span class="badge bg-orange">${d.value.power}</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="box">
			<div class="box-body">
				<div style="overflow: scroll; overflow-y: hidden;">
					<div
						style="width: 150%; ">
						<canvas style="max-height:750px" id="line" class="chart chart-bar" chart-data="charts.data"
							chart-labels="charts.labels" chart-legend="true"
							chart-series="charts.series" chart-click="onClick"
							chart-colours="colors" chart-options="options"> </canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/Chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/resources/angular-chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/controller/app.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/controller/dissagregation.js"></script>
