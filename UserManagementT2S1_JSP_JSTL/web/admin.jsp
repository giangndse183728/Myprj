 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : admin
    Created on : Jan 22, 2024, 8:36:01 AM
    Author     : PC
--%>

<%@page import="java.util.List"%>
<%@page import="sample.sp24.t2s1.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
      <style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 20px;
    background-color: #f8f9fa;
    color: #333;
}

form {
    margin-top: 10px;
}

.link {
    display: flex;
    
}

.link a {
    margin-right: 20px;
}






/* Add more specific styles if needed */

      </style>
      <body>

          <h1>Welcome: ${sessionScope.LOGIN_USER.fullName} </h1>
          <c:url var="logoutLink" value="MainController">
              <c:param name="action" value = "Logout"></c:param>

          </c:url>
          <div class="link">
              <a href="MainController?action=Create_User_Page" class="btn btn-primary">Create User</a> </br>
               <a href="${logoutLink}" class="btn btn-secondary"> Logout </a>
          </div>

          <form action="MainController" method="POST">
              Search: <input type="text" name="search" value="${param.search}">
              <input type="submit" name="action" value="Search">
          </form>
          <c:if test="${requestScope.LIST_USER != null}" >
              <c:if test="${not empty requestScope.LIST_USER}">
                  <table border="1" class="table">
                      <thead class="table-dark">
                          <tr>
                              <th>No</th>
                              <th>User ID</th>
                              <th>Full name</th>
                              <th>Role ID</th>
                              <th>Password</th>
                              <th>Update</th>
                              <th>Delete</th>
                          </tr>
                      </thead>
                      <tbody>
                          <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                          <form action="MainController" method="POST">

                              <tr>
                                  <td> ${counter.count}</td>
                                  <td> 
                                      <input type="text" name="userID" value="${user.userID}" readonly=""/>
                                  </td>
                                  <td> 
                                      <input type="text" name="fullName" value="${user.fullName}"/>
                                  </td>
                                  <td> 
                                      <input type="text" name="roleID" value="${user.roleID}"/>
                                  </td>
                                  <td>${user.password}</td>
                                  <td>
                                      <input class="btn btn-warning" type="submit" name="action" value="Update"/>
                                      <input type="hidden" name="search" value="${param.Search}"/>
                                  </td>
                                  <td>
                                      <input class="btn btn-danger" type="submit" name="action" value="Delete"/>
                                      <input  type="hidden" name="search" value="${param.Search}"/>
                                  </td>
                              </tr>
                          </form>
                      </c:forEach>
                  </tbody>
              </table>

          </c:if>
      </c:if>

      <form action="MainController" method="POST">
          Search Order: <input type="text" name="search" value="${param.search}">
          <input type="submit" name="action" value="SearchOrder">
      </form>
      <c:if test="${requestScope.LIST_ORDER != null}" >
          <c:if test="${not empty requestScope.LIST_ORDER}">
              <table border="1" class="table">
                  <thead class="table-dark" >
                      <tr>
                          <th>No</th>
                          <th>Order ID</th>
                          <th>User ID</th>
                          <th>Order Date</th>
                          <th>Total</th>
                      </tr>
                  </thead>
                  <tbody>
                      <c:forEach var="order" varStatus="counter" items="${requestScope.LIST_ORDER}">
                      <form action="MainController" method="POST">
                          <tr>
                              <td>${counter.count}</td>
                              <td>${order.orderID}</td>
                              <td>${order.userID}</td>
                              <td>${order.orderDate}</td>
                              <td>${order.total}</td>
                          </tr>
                      </form>
                  </c:forEach>
              </tbody>
          </table>
      </c:if>
</c:if>

          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
