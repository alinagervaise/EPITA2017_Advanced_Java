<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<head>
	<title> Home Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/iam.css"/>
	</head>
	
	<body>
		<div class="container">
			<div class="row" style="margin:20px; width: 60%; ">
				<div  style="padding: 10px; background-color: #eeeeee; border: 2px  solid #000000; border-radius:10px;">
					<%String val = (String)session.getAttribute("displayName");  %>
					<p>Hello <%=val%></p>
					<p style="font-size: 60px;">Welcome to the IAM System</p>
					<P  class="error"> Sorry your home page is not configured yet. Contact the administrator. </P>
					<p><a href="/logout">Disconnect</a></p>
				</div>
			</div>
			
		</div>
	</body>

<script>
