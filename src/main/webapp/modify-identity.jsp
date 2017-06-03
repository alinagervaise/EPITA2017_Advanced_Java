<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Welcome Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>

<body>
	<div class="container">
    	<h3>New Identity Creation</h3>
		<p><a href=""> >> back </a></p>
    	<div class="row">
        	<form role="form" action="modify" method="post">
				<div class="col-xs-6">
					<h4>About you</h4>
					<div class="form-group">
						<label>First Name</label>
						<input name="firstname" class="form-control" id="firstname" type="text"  placeholder="firstname"/>
					</div>
					<div class="form-group">
						<label>Last name</label>
						<input name="lastname" class="form-control" type="text" placeholder="Last name" />
					</div>
					<div class="form-group">
						<label>Email</label>
						<input name="email" class="form-control" id="email" type="text"  placeholder="Enter your email"/>
					</div>
					<div class="form-group">
						<label>Birthday</label>
						<input name="birthday" class="form-control" type="date" placeholder="jj/mm/aaaa" />
					</div>
				</div>
				<div class="col-xs-6">
					<h4>Address</h4>
					<div class="form-group">
						<label>Street</label>
						<input name="street" class="form-control" id="street" type="text"  placeholder="street"/>
					</div>
					<div class="form-group">
						<label>Zip Code</label>
						<input name="zipcode" class="form-control" type="text" placeholder="Zip Code" />
					</div>
					<div class="form-group">
						<label>City</label>
						<input name="city" class="form-control" id="city" type="text"  placeholder="city"/>
					</div>
					<div class="form-group">
						<label>Country</label>
						<input name="country" class="form-control" type="text" placeholder="country" />
					</div>
				</div>
            	<button type="submit" class="btn btn-default">Submit</button>
        	</form>
    
    	</div>
    </div>

</body>