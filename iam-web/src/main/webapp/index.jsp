<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
	<title>Welcome Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/iam.css"/>
</head>

<body>
	<div class="container">
    	<h3>Welcome to the identity and access management application</h3>
    	<div class="col-xs-6">
        	<form role="form" action="authenticate" method="post">
        		<div class="form-group">
        			<label>Username</label>
            		<input name="username" class="form-control" id="username" type="text"  placeholder="Enter your username"/>
            	</div>
            	<div class="form-group">
        			<label>Password</label>
            		<input name="password" class="form-control" type="password" placeholder="Enter your password" />
            	</div>
            	<c:if test="${error_msg != null}">
            		<div class="form-group">
        				<label class="error">${error_msg}</label>
            		</div>
            	</c:if>
            	<c:if test="${logout_msg != null}">
            		<div class="form-group">
        				<label class="error">${logout_msg}</label>
            		</div>
            	</c:if>
            	
            	<button type="submit" class="btn btn-default">Login</button>
            	
        	</form>
    
    	</div>
    </div>

</body>
