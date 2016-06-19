<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<body class="login-page" onload='document.f.j_username.focus();'>
	<div class="login-box">
		<div class="login-logo">
			<b>LICENTA</b>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Register into the demand response
				application</p>
			<form:form modelAttribute="newUser">
				<div class="form-group has-feedback">
					<form:input class="form-control" placeholder="Email" path="email" />
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<form:input class="form-control" placeholder="Password"
						path="password" type="password" />
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<form:input class="form-control" placeholder="First Name"
						path="firstName" />
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<form:input class="form-control" placeholder="Last Name"
						path="lastName" />
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<button type="submit" name="login"
							class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
					<!-- /.col -->
				</div>
			</form:form>


			<!--<a href="#">I forgot my password</a><br>-->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->


</body>
