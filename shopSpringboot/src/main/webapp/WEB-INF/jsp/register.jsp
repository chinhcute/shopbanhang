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

                     <form:form action="${action}" method="post" modelAttribute="accounts">
                         <div class="mb-3">
                             <label for="email" class="form-label">Email</label>
                             <form:input type="email" path="email" class="form-control" id="email" placeholder="Enter your email" />
                             <small class="form-text text-muted"><form:errors path="email" class="text-danger" /></small>
                         </div>
                         <div class="mb-3">
                             <label for="password" class="form-label">Password</label>
                             <form:input type="password" path="password" class="form-control" id="password" placeholder="Enter your password" />
                              <small class="form-text text-muted"><form:errors path="password" class="text-danger"/></small>
                         </div>
                         <div class="mb-3">
                             <label for="address" class="form-label">Address</label>
                             <form:input type="text" path="address" class="form-control" id="address" placeholder="Enter your address" />
                             <small class="form-text text-muted"><form:errors path="address" class="text-danger" /></small>
                         </div>
                         <div class="mb-3">
                             <label for="sex" class="form-label">Gender</label>
                             <form:select path="sex" class="form-control" id="sex">
                                 <form:option value="nam">nam</form:option>
                                 <form:option value="nữ">nữ</form:option>
                                 <form:option value="unknown">unknown</form:option>
                             </form:select>
                         </div>
                         <div class="mb-3">
                             <label for="birthday" class="form-label">Ngày sinh</label>
                             <form:input type="date" path="birthday" class="form-control" id="birthday" />
                             <small class="form-text text-muted"><form:errors path="birthday" class="text-danger" /></small>
                         </div>

                         <button type="submit" class="btn btn-primary">Đăng kí</button>
                     </form:form>




                 </div>
             </div>
         </div>

        </main>


        <footer></footer>


      </body>
      </html>

