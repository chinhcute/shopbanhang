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
        margin-top: 30px;
        height: 500px;
    }
    .search{
        width: 1000px;
        margin: auto 100px;
        display: flex;
    }
    .form-outline{
      display: flex;
    }
    footer{
    height: 300px;
    min-width: 100%;
    background-image: linear-gradient(0deg, rgb(236, 18, 18), rgb(235, 99, 15));
   }
    </style>
</head>
<body>

  <header>


    <div class="headerimage">
     <a href="/home"> <img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
   </div>

  </header>
    <main>
      <div class="container">
        <h1>Tạo tài khoản ngân hàng</h1>
       <form:form action="${action}" method="post" modelAttribute="bank">

           <div class="mb-3">
               <label for="name" class="form-label">Họ và tên</label>
               <form:input path="name" type="text" class="form-control" id="name" placeholder="Nhập họ và tên" />
               <small class="form-text text-muted"><form:errors path="name" class="text-danger" /></small>
           </div>
          <div class="mb-3">
              <label for="account_number" class="form-label">Số tài khoản</label>
              <form:input type="text" path="account_number" class="form-control" id="account_number" placeholder="Nhập số tài khoản" />
              <small class="form-text text-muted"><form:errors path="account_number" class="text-danger" /></small>
          </div>

           <button type="submit" class="btn btn-primary">Tạo</button>
       </form:form>


      </div>
    </main>

    <footer>

    </footer>
</body>
</html>