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
                       <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <div class="container mt-5">
        <div class="row justify-content-center">
          <div class="col-md-6">
            <h2 class="text-center mb-4">Order Detail</h2>
            <p><strong>Name:</strong> ${orderEntity.name}</p>
            <p><strong>Created Date:</strong> ${orderEntity.createdDate}</p>
            <p><strong>Shipped Date:</strong> ${orderEntity.shippedDate}</p>
            <p><strong>Status:</strong> ${orderEntity.status}</p>
            <p><strong>Shipping Address:</strong> ${orderEntity.shippingAddress}</p>
            <p><strong>Payment Type:</strong> ${orderEntity.paymentType}</p>
          </div>
        </div>
        <div class="row justify-content-center mt-4">
          <div class="col-md-6">
            <ul class="list-group">
               <c:forEach items = "${productEntityList}" var = "product" >

                  <li class="list-group-item">${product.name} - ${product.price}</li>
               </c:forEach>
              </ul>
               <p>${price}</p>



            <form action="${action}" method="post">

              <div class="form-group">
                <label for="bank">Select Bank:</label>
                <select class="form-control" name = "bankId" id="bank">
                   <c:forEach items="${bankEntityList}" var="bankEntity">
                      <option value="${bankEntity.id}">${bankEntity.name}</option>
                  </c:forEach>
                </select>
              </div>

              <button type="submit" class="btn btn-primary">Request Refund</button>
            </form>

          </div>
        </div>

      </div>

</body>
</html>