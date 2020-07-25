<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<title>Save Customer</title>
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
<div id="wrapper">
  <div id="header">
    <h2>CRM - Customer Relationship Manager</h2>
  </div>
</div>
<div id="container">
  <h3>Save Customer</h3>
  <form:form action="saveCustomer" modelAttribute="customer" method="POST">
  
    <!-- need to associate this data with customer id -->
    <form:hidden path="id" />
    <table>
      <tbody>
        <tr>
          <td><label>First name:</label>
          <td><form:input path="firstName" />
        <tr>
          <td><label>Last name:</label>
          <td><form:input path="lastName" />
        <tr>
          <td><label>Email:</label>
          <td><form:input path="email" />
        <tr>
          <td><label></label>
          <td><input type="submit" value="Save" class="save" />
      </tbody>
    </table>
  </form:form>
  <div style="clear; both;"></div>
  <p><a href="${pageContext.request.contextPath}/customer/list">Back to List</a></p>
</div>