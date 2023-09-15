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
             <%@include file="/resources/css/login.css" %>
         </style>
      </head>
      <body>

        <header>


          <div class="headerimage">
           <a href="/home"> <img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
         </div>
         <h2>${name}</h2>
        </header>
        <main>
         <div class="container">
             <div class="row justify-content-center mt-5">
                 <div class="col-lg-4 col-md-6 col-sm-8">
                     <h2 class="text-center mb-4">${name}</h2>
                     <form action="${action}" method="post">
                         <div class="mb-3">
                             <label for="email" class="form-label">Email</label>
                             <input type="email" name="email" class="form-control" id="email" placeholder="Enter your username">
                             <span id="emailError" class="text-danger"></span>
                         </div>
                         <div class="mb-3">
                             <label for="password" class="form-label">Password</label>
                             <input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
                         </div>




       <c:if test="${not empty errorMessage}">
           <p style="color: red">${errorMessage}</p>
       </c:if>






                         <button type="submit" class="btn btn-primary">${name}</button>
                     </form>
                    <c:if test="${name eq 'Đăng nhập'}">
                    <div class="add_account" >
                      <div class="mt-3" th:if="${login}">
                        <a href="/forgot_password">Quên mật khẩu?</a>
                      </div>
                      <div class="mt-3" style = "margin-left: 20px;" th:if="${login}">
                        <a href="/signout">Tạo tài khoản mới</a>
                      </div>
                 </div>
                 </c:if>
                 </div>
             </div>
         </div>

        </main>
        <script>
            $(document).ready(function() {
                $('#email').on('input', function() {
                    var email = $(this).val();
                    checkEmail(email);
                });

                function checkEmail(email) {
                    $.ajax({
                        type: 'GET',
                        url: '/checkEmail',
                        data: {
                            email: email
                        },
                        success: function(response) {
                            if (response === false) {
                                $('#emailError').text("Nhập email chưa đúng");
                            } else {
                                $('#emailError').text("");
                            }
                        },

                    });
                }
            });
        </script>
        <c:if test="${name eq 'Đăng kí'}">
       <script>
           $(document).ready(function() {
               $('#email').on('input', function() {
                   var email = $(this).val();
                   checkGmail(email);
               });

               function checkGmail(email) {
                   $.ajax({
                       type: 'post',
                       url: '/check-email',
                       data: {
                           email: email
                       },
                       success: function(response) {
                           if (response === false) {
                               $('#emailError').text("Email đã được sữ dụng");
                           } else {
                               $('#emailError').text("");
                           }
                       }
                   });
               }
           });
       </script>
       </c:if>


        <footer></footer>


      </body>
      </html>

