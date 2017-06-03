<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<head>
	<title> Home Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	
	</head>
	
	<body>
		<div class="container">
			<div class="row" style="margin:20px; width: 60%; ">
				<div  style="padding: 10px; background-color: #eeeeee; border: 2px  solid #000000; border-radius:10px;">
					<p style="font-size: 60px;">Welcome to the IAM System</p>
					<p><a href="">Disconnect</a></p>
				</div>
			</div>
			<div class="row"  >
				<div class="col-sm-6" style="padding:1%; text-align: justify;" >
					<form role="form" action="create" method="get"> 
						<h4>Identity Creation</h4>
						<p>Thanks to this action, you can create a brand new Identity, you can click on the button below to begin</p>
						<button type="submit" class="btn btn-default">Create!</button>
					</form>
				</div>
				<div class="col-sm-6" style="padding:1%; text-align: justify;">
					<form  role="form" action="search" method="post">
						<h4>Identity Search</h4>
						<p>Thanks to this action, you can search an identity and then access to its information.
						Through this action, you can also modify or delete the wished identity</p>
						<button type="submit" class="btn btn-default">Search!</button>
					</form>
				</div>
			</div>
		</div>
	</body>
