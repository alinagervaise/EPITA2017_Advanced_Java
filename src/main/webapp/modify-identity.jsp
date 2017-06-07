<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Welcome Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>

<body>
	<div class="container">
    	<h3>Modify Identity </h3>
		<p><a href=""> >> back </a></p>
    	<div class="row">
        	<form role="form" action="modify" method="post">
				<div class="col-xs-6">
					<h4>About you</h4>
					<div class="form-group">
						<label>First Name</label>
						<input name="firstname" class="form-control" id="firstname" type="text"
						  value=" <c:out value="${identity.firstname}" />"/>
					</div>
					<div class="form-group">
						<label>Last name</label>
						<input name="lastname" class="form-control" type="text"
						 value=" <c:out value="${identity.lastname}" />" />
					</div>
					<div class="form-group">
						<label>Email</label>
						<input name="email" class="form-control" id="email" type="text" 
						 value=" <c:out value="${identity.email}" />"/>
					</div>
					<div class="form-group">
						<label>Birthday</label>
						<input name="birthdate" class="form-control" type="date"
						 value=" <c:out value="${identity.birthdate}" />" />
					</div>
				</div>
				<div class="col-xs-6">
					<h4>Address</h4>
					<div class="form-group">
						<label>Street</label>
						<input name="street" class="form-control" id="street" type="text" 
						 value=" <c:out value="${address.street}" />"/>
					</div>
					<div class="form-group">
						<label>Zip Code</label>
						<input name="zipcode" class="form-control" type="text" 
						value=" <c:out value="${address.zipCode}" />" />
					</div>
					<div class="form-group">
						<label>City</label>
						<input name="city" class="form-control" id="city" type="text"
						  value=" <c:out value="${address.city}" />"/>
					</div>
					<div class="form-group">
						<label>Country</label>
						<input name="country" class="form-control" type="text" 
						value="<c:out value="${address.country}" />" />
					</div>
				</div>
            	<button type="submit" class="btn btn-default">Submit</button>
        	</form>
    
    	</div>
    </div>

</body>
