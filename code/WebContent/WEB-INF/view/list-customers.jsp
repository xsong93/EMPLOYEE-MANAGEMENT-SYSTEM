<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<title>List Customers</title>

<!-- reference style sheet -->
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css" />
<div id="wrapper">
  <div id="header">
    <h2>CRM - Customer Relationship Manager</h2>
  </div>
</div>
<div id="container">
  <div id="content">
  
  	<!-- add a search box -->
  	<form:form action="search" method="GET">Search customer:
  	  <input type="text" name="theSearchName" />
  	  <input type="submit" value="Search" class="add-button" />
  	</form:form>
    	   
    <!-- put button: Add Customer -->
    <input type="button" value="Add Customer"
    	   onclick="window.location.href='showFormForAdd'; return false;" 
    	   class="add-button" />
    	   
    <!-- add html table here -->
    <table>
      <tr>
        <th>First Name
        <th>Last Name
        <th>Email
        <th>Action
        
      <!-- loop over and print out customers -->
      <c:forEach var="tempCustomer" items="${customers}">
      
      	<!-- construct an update link with customer id -->
      	<c:url var="updateLink" value="/customer/showFormForUpdate">
      	  <c:param name="customerId" value="${tempCustomer.id}" />
      	</c:url>
      	
      	<!-- construct a delete link with customer id -->
      	<c:url var="deleteLink" value="/customer/delete">
      	  <c:param name="customerId" value="${tempCustomer.id}" />
      	</c:url>
        <tr>
          <td>${tempCustomer.firstName}
          <td>${tempCustomer.lastName}
          <td>${tempCustomer.email}
          <td><a href="${updateLink}">Update</a>
          	  |
          	  <a href="${deleteLink}"
          	     onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
      </c:forEach>
    </table>
  </div>
</div>