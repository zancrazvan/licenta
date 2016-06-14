<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<section class="content" ng-controller="DissagregationController">

 
	<div class="row" ng-show="step0">
		<div class="col-lg-10 col-lg-offset-1">
			<div align="center" style="backgound-color: #FFFFFF;">
				<img src="resources/devicePictures/pas1.png" ng-click="showStep1()">
			</div>
		</div>
	</div>
	<div class="row" ng-show="step1">
		<div class="col-lg-10 col-lg-offset-1">
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<caption>Aparate salvate in baza de date</caption>
						<tr>
							<th style="width: 10px">Id</th>
							<th>Nume</th>
							<th>Putere consumata</th>
							<th></th>

						</tr>
						<c:forEach items="${devices}" var="d">
							<tr>
								<td>${d.id}</td>
								<td>${d.name}</td>
								<c:if test="${d.power<=200}">
									<td><span class="badge bg-green">${d.power}W</span></td>
								</c:if>
								<c:if test="${d.power>200 && d.power <= 1000}">
									<td><span class="badge bg-blue">${d.power}W</span></td>
								</c:if>
								<c:if test="${ d.power > 1000}">
									<td><span class="badge bg-red">${d.power}W</span></td>
								</c:if>
								<td><div>
										<img
											style="max-height: 90px; height: auto; overflow: hidden;"
											src="${pageContext.request.contextPath}/${d.picturePath}"
											alt="no picture to display">
									</div></td>

							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row" ng-show="step1">
		<div class="col-lg-10 col-lg-offset-1">
			<div align="center" style="backgound-color: #FFFFFF;">
				<img src="resources/devicePictures/pas2.png" ng-click="showStep2()">
			</div>
		</div>
	</div>
	<div class="row" ng-show="step2">
		<div class="col-lg-10 col-lg-offset-1">
			<div class="box">
				<div class="box-body">
					<div style="overflow: scroll; overflow-y: hidden;">
						<div style="width: 150%;">
							<canvas style="max-height: 750px" id="line"
								class="chart chart-bar" chart-data="charts.data"
								chart-labels="charts.labels" chart-legend="true"
								chart-series="charts.series" chart-click="onClick"
								chart-colours="colors" chart-options="options"> </canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row" ng-show="step2">
		<div class="col-lg-10 col-lg-offset-1">
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<caption>Lista fronturilor crescatoare detetate de pe
							curba de consum( fiecare rand reprezinta detectarea pornirii unui
							aparat)</caption>
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

								<td><span class="badge bg-green">${d.relativePower}W</span></td>

								<td><span class="badge bg-blue">${d.absolutePower}W</span></td>


							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="row" ng-show="step2">
		<div class="col-lg-10 col-lg-offset-1">
			<div align="center" style="backgound-color: #FFFFFF;">
				<img src="resources/devicePictures/pas3.png" ng-click="showStep3()">
			</div>
		</div>
	</div>
	<div class="row" ng-show="step3">
		<hr>

		<div class="col-lg-10 col-lg-offset-1">
			<h3>Solutia obtinuta are eroarea ${solutie.absoluteError}</h3>
			<div class="box">
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>Aparat</th>
							<th>Timp pornire</th>
							<th>Timp Rulare</th>
							<th>Puterea detectata pe curba</th>
							<th>Puterea aparatului asociat cu profilul detectat</th>
							<th>Aparat</th>
						</tr>
						<c:forEach items="${solutie.solution}" var="d">
							<tr>
								<td>${d.value.name}</td>
								<td>${d.key.time}</td>
								<td>${d.key.runningTime}</td>
								<td><span class="badge bg-green">${d.key.relativePower}W</span></td>
								<td><span class="badge bg-orange">${d.value.power}W</span></td>

								<td><div>
										<img style="max-height: 50px; height: auto; overflow: hidden;"
											src="${pageContext.request.contextPath}/${d.value.picturePath}"
											alt="no picture to display">
									</div></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row" ng-show="step3">
		<div class="col-lg-10 col-lg-offset-1">
			<div align="center" style="backgound-color: #FFFFFF;">
				<img src="resources/devicePictures/pas4.png" ng-click="showStep0()">
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
