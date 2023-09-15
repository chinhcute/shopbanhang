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
      *{
              font-family: Arial, Helvetica, sans-serif;
            }
        .card-header{
            background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
            color: #ffffff;
        }


        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>
 <jsp:include page="header.jsp" />


    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <div class="card">
                    <div class="card-header">
                        My Account
                    </div>
                    <div class="card-body">
                        <p class="card-text">Name:  ${bankEntity.name} </p>
                        <p class="card-text">Account_number: ${bankEntity.account_number} </p>
                        <p class="card-text">Surplus: ${bankEntity.surplus} </p>



                    </div>
                          <form action="${action}" method="POST">
                            <div class="mb-3">
                              <label for="input" class="form-label">Nạp tiền</label>
                              <input type="number" name="price" class="form-control" id="input" placeholder="Nhập số tiền" required>
                               <div style = "color: red;" role="alert">
                                                                                   ${error}
                                                                               </div>
                            </div>

                            <button type="submit" class="btn btn-danger" >Nạp</button>
                          </form>
                </div>

            </div>
        </div>
    </div>
</body>
</html>