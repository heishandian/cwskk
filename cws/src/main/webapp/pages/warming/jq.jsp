<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script>
	$().ready(function() {
		$("#submitForm").validate({
			rules: {
				firstname: "required",
				lastname: "required",
				username: {
					required: true,
					minlength: 2
				},
				password: {
					required: true,
					minlength: 5
				},
				confirm_password: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				topic: {
					required: "#newsletter:checked",
					minlength: 2
				},
				agree: "required"
			},
			messages: {
				firstname: "请输入姓",
				lastname: "请输入名",
				username: {
					required: "输入用户名",
					minlength: "最少两个字符"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address",
				agree: "Please accept our policy"
			}
		});
	});
	</script>
	<style>
	
	input:focus { border: 1px dotted black; }
	<!--input 错误的时候提示 -->/
	input.error { border: 1px dotted red; }
	#signupForm {
		width: 670px;
	}
	#signupForm label.error {
		margin-left: 10px;
		width: auto;
		display: inline;
	}
	#newsletter_topics label.error {
		display: none;
		margin-left: 103px;
	}
	</style>
</head>
<body>
<div id="main">
	<form class="cmxform" id="submitForm" method="get" action="">
			<p>
				<label for="firstname">Firstname</label>
				<input id="firstname" name="firstname" type="text">
			</p>
			<p>
				<label for="lastname">Lastname</label>
				<input id="lastname" name="lastname" type="text">
			</p>
			<p>
				<label for="username">Username</label>
				<input id="username" name="username" type="text">
			</p>
			<p>
				<label for="password">Password</label>
				<input id="password" name="password" type="password">
			</p>
			<p>
				<label for="confirm_password">Confirm password</label>
				<input id="confirm_password" name="confirm_password" type="password">
			</p>
			<p>
				<label for="email">Email</label>
				<input id="email" name="email" type="email">
			</p>
			<p>
				<label for="agree">Please agree to our policy</label>
				<input type="checkbox" class="checkbox" id="agree" name="agree">
			</p>
			<p>
				<label for="newsletter">I'd like to receive the newsletter</label>
				<input type="checkbox" class="checkbox" id="newsletter" name="newsletter">
			</p>
				<label for="topic_marketflash">
					<input type="checkbox" id="topic_marketflash" value="marketflash" name="topic">Marketflash
				</label>
				<label for="topic_fuzz">
					<input type="checkbox" id="topic_fuzz" value="fuzz" name="topic">Latest fuzz
				</label>
				<label for="topic_digester">
					<input type="checkbox" id="topic_digester" value="digester" name="topic">Mailing list digester
				</label>
				<label for="topic" class="error">Please select at least two topics you'd like to receive.</label>
			<p>
				<input class="submit" type="submit" value="Submit">
			</p>
	</form>
</div>
</body>
</html>
