<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Welcome Page </title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>

<body>
	<div class="container">
    	<h3>Search Identity</h3>
		<p><a href=""> >> back </a></p>
    	<div>
        	<form role="form" action="search" method="post">
				<h4>Search Critera</h4>
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group">
							<label>First Name</label>
							<input name="firstname" class="form-control" id="firstname" type="text"  placeholder="firstname"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>Last name</label>
							<input name="lastname" class="form-control" type="text" placeholder="Last name" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>Email</label>
							<input name="email" class="form-control" id="email" type="text"  placeholder="Enter your email"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>Birthday</label>
							<input name="birthday" class="form-control" type="date" placeholder="jj/mm/aaaa" />
						</div>
					</div>
				</div>
				<!-- search by address-->
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group">
							<label>Street</label>
							<input name="street" class="form-control" id="street" type="text"  placeholder="street"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>Zip Code</label>
							<input name="zipcode" class="form-control" type="text" placeholder="Zip Code" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>City</label>
							<input name="city" class="form-control" id="city" type="text"  placeholder="city"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label>Country</label>
							<input name="country" class="form-control" type="text" placeholder="country" />
						</div>
					</div>
				</div>
            	<button type="submit" class="btn btn-primary">Submit</button>
        	</form>
			
			<!-- search results  -->
			<form xmlns="http://www.w3.org/1999/xhtml" class="form-horizontal">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Selection</th>
                        <th>UID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Birthdate</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                      <c:forEach  var="identity" items="${identities}">
		                    <tr>
		                        <td> <input name="selection" type="radio" id='<c:out value="${identity.id}"/> ' class="radio-btn" /> </td>
		                        
		                        <td> <c:out value="${identity.uid}" /> </td>
		                        <td> <c:out value="${identity.firstname}"/> </td>
		                        <td> <c:out value="${identity.lastname}"/> </td>
		                        <td> <c:out value="${identity.birthdate}"/> </td>
		                        <td> <c:out value="${identity.email}"/> </td>
		                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class=" col-sm-offset-2 col-sm-10 text-right">
                    <button type="submit" href="modify" class="btn btn-primary submit-btn">Modify</button>
                    <button type="submit" href="delete" class="btn btn-primary submit-btn">Delete</button>
                    <button type="submit" href="cancel" class="btn btn-default">Cancel</button>
                </div>
            </div>
        </form>
    
    	</div>
    </div>

</body>

<script>
    $(document).ready(function(){
    	console.log("HERE----->"+ $(".submit-btn").attr('href').text);
    	$('.radio-btn').click(function () {
            if ($(this).is(':checked')) {
                alert("Allot Thai Gayo Bhai");
            }
        });
    });
</script>