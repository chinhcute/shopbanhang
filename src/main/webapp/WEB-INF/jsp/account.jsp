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
    <style>
     *{
          font-family: Arial, Helvetica, sans-serif;
        }
        .card-header{
            background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
            color: #ffffff;
        }
                .mb-2:hover {
            background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
            color: #ffffff;
            text-decoration: none;
        }

        .mb-2 a:hover {
            background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
            color: #ffffff;
            text-decoration: none;
        }

        <%@include file="/resources/css/home.css" %>
    </style>
     <script src="/resources/js/account.js"></script>
</head>
<body>
 <jsp:include page="header.jsp" />



<c:if test="${showError}">
    <div class="modal fade" id="showErrors" tabindex="-1" role="dialog" aria-labelledby="notEnoughQuantityModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header" style="background-color: red; color: aliceblue;">
                    <h5 class="modal-title" id="notEnoughQuantityModalLabel">Thông báo</h5>
                </div>
                <div class="modal-body">
                    Số tiền bạn không đủ! <br>
                    Vui lòng nộp thêm tiên !

                </div>
                <div class="modal-footer">
                    <button type="button" id = "close" class="btn btn-secondary" style="background-color: red; color: aliceblue;" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>




</c:if>


    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <div class="card">
                    <div class="card-header">
                        My Account
                    </div>
                    <div class="card-body">
                        <p class="card-text">Email:  ${accountEntity.email} </p>
                        <p class="card-text">Date of Birth: ${accountEntity.birthday} </p>
                        <p class="card-text">Gender: ${accountEntity.sex} </p>
                        <p class="card-text">Address: ${accountEntity.address} </p>

                    </div>
                        <div class="bank-items">
                        <c:forEach items = "${bankEntityList}" var= "bank">
                            <a style="color: black; text-decoration: none;" href="/user/recharge/${bank.id}">
                                <div class="card mb-2">
                                    <div class="card-body">
                                        <p class="card-text">${bank.name}</p>
                                        <p class="card-text">Số dư: ${bank.surplus}</p>
                                    </div>
                                </div>
                            </a>
                            </c:forEach>


                        </div>
                </div>
                    <a href="/logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </div>
</body>
</html>