<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/brands.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/solid.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/fontawesome.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css">
              <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>


         <link rel="stylesheet" type="text/css" href='${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/css/bootstrap.min.css' />
         <script type="text/javascript" src="${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

    <style>
        <%@include file="/resources/css/admin.css" %>
    </style>
</head>
<body>
    <header>
        <a href="/home"><img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
        <div class="search">
            <form action="/admin/search_order" method = "GET">
              <div style="margin-top: 30px;" class="input-group">
                <div  class="form-outline">
                  <input style="width: 700px;" type="text" name = "key" class="form-control"/>
                  <button type="button" class="btn btn-danger">
                         <i class="fas fa-search"></i>
                   </button>
                </div>

            </div>
            </form>
            </div>
            <p style="color: aliceblue;">${message}</p>
    </header>


   <div class="main">
    <nav>
        <ul>
            <li><i class="fa-solid fa-house"></i><a href="/admin/home">Home</a></li>
              <li><i class="fa-solid fa-arrow-up"></i><a href="/admin/add_product">Add produck</a></li>
             <li><i class="fa-solid fa-gear"></i> <a href="/admin/product_view">Product</a></li>
             <li><i class="fa-solid fa-right-from-bracket"></i><a href="/logout">Logout</a></li>

        </ul>


    </nav>
    <main>

        <table class="table" style = " margin: 40px;">
            <thead>
              <tr>
                <th scope="col">Name</th>
                <th scope="col">CreatedDate</th>
                <th scope="col">ShippedDate</th>
                <th scope="col">Status</th>
                <th scope="col">ShippingAddress</th>
                <th scope="col">PaymentType</th>

              </tr>
            </thead>
            <tbody>
            <c:forEach items = "${orderEntityList}" var = "order">
                <tr>
                <td>${order.name}</td>
                <td>${order.createdDate}</td>
                <td>${order.shippedDate}</td>
               <td>${order.status}</td>
               <td>${order.shippingAddress}</td>
               <td>${order.paymentType}</td>


              </tr>
            </c:forEach>



            </tbody>
          </table>
          <c:if test="${not empty orderEntityList}">
              <ul class="pagination" style="margin-left: 40%;">
                  <c:forEach begin="0" end="${orderEntityPage.totalPages - 1}" var="i">
                      <li class="page-item ${i == currentPage ? 'active' : ''}">
                          <a class="page-link" href="/admin/home?page=${i}">${i + 1}</a>
                      </li>
                  </c:forEach>
              </ul>
          </c:if>




    </main>
   </div>

</body>
</html>