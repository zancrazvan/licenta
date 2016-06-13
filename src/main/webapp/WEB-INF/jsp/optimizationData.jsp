<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../layout/taglib.jsp"%>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Data Optimization</h1>

		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-6">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Please provide the inputs</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form method="post">
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputEmail1">Number of individuals</label> <input
										type="text" class="form-control" id="nrOfIndividuals"
										name="nrOfIndividuals" placeholder="Number of individuals">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Number of
										generations</label> <input type="text" class="form-control"
										name="nrOfGenerations" id="nrOfGenerations"
										placeholder="Number of generations">
								</div>


								<div class="box-footer">
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
