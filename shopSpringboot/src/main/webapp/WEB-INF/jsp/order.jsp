<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    
   <Style>
    *{
      margin: 0px;
      font-family: Arial, Helvetica, sans-serif;
    }

    header{
      min-width: 1000px;
      background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
      height: 150px;
      padding: 0 100px;
      display: flex;
     
    }
    header img{
        width: 200px;
    }
    header h2{
        color: aliceblue;
        margin: auto 40px;
    }
    main{
        height: 500px;
        margin: 40px 500px;
    }

   
   body {
  display: flex;
  flex-direction: column;
 
}
 

 
   footer{
    height: 300px;
    min-width: 100%;
    background-image: linear-gradient(0deg, rgb(236, 18, 18), rgb(235, 99, 15));
   }
  
   </Style>
   
</head>
<body>
  
  <header>
   
  
    <div class="headerimage">
     <a href="/user/cart"> <img  src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
   </div>
   <h2>Thanh toán</h2>
  </header>
<main>
    <form:form action="${action}" method="POST" modelAttribute="order">
        <div class="form-group">
            <label for="name">Full Name:</label>
            <form:input type="text" path="name" class="form-control" id="name" placeholder="Enter your full name" />
            <small class="form-text text-muted"><form:errors path="name" class="text-danger" /></small>
        </div>
        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <form:input type="tel" class="form-control" path="phone" id="phone" placeholder="Enter your phone number" />
            <small class="form-text text-muted"><form:errors path="phone" class="text-danger" /></small>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <form:textarea class="form-control" path="shippingAddress" id="address" rows="3" placeholder="Enter your address"></form:textarea>
            <small class="form-text text-muted"><form:errors path="ShippingAddress" class="text-danger" /></small>
        </div>
        <div class="form-group">
            <label for="paymentMethod">Payment Method:</label>
            <form:select class="form-control" path="paymentType" id="paymentMethod">
                 <form:option value="offline">Offline</form:option>
                <form:option value="online">Online Payment</form:option>

            </form:select>
        </div>
        <div id="bankAccount" style="display: none;">
            <!-- Display the fields related to choosing a bank account for online payment -->
            <div class="form-group">
                <label for="bank">Select Bank Account:</label>

              <select class="form-control" name="bank" id="bank">
                  <c:forEach items="${bankEntityList}" var="bankEntity">
                    <option value="${bankEntity.id}">${bankEntity.name}</option>
                 </c:forEach>
             </select>



            </div>
        </div>
        <button type="submit" class="btn btn-primary">Confirm Payment</button>
    </form:form>
</main>

<script src="/resources/js/order.js"></script>


  <footer></footer>
      
</body>
</html>

