<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<body class="login-page" onload='document.f.j_username.focus();'>
	<div class="login-box">
		<div class="login-logo">
			<b>LICENTA</b>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Login to Dashboard</p>
			<form action='/j_spring_security_check' method='POST'>
				<div class="form-group has-feedback">
					<input type="email" class="form-control" name='j_username'
						placeholder="Email" /> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name='j_password' class="form-control"
						placeholder="Password" /> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<button type="submit" name="login"
							class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>


			<!--<a href="#">I forgot my password</a><br>-->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->


</body>
