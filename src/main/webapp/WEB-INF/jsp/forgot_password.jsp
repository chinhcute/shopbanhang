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
     </style>
 </head>
 <body>
     <header>


     </header>
     <main>
         <div class="container">
             <div class="row justify-content-center mt-5">
               <div class="col-lg-4 col-md-6 col-sm-8">
                 <h2 class="text-center mb-4">Xác thực</h2>
               <form action="${action}" method="POST">
                   <div class="mb-3">
                       <label for="input" class="form-label">Nhập email</label>
                       <input type="email" name="email" class="form-control" id="input" placeholder="Nhập email" required>
                   </div>
                   <div style = "color: red;" role="alert" >
                       ${error}
                   </div>
                    <div style = "color: red;" role="alert" >
                                          ${errorMessage}
                      </div>

                   <button type="submit" class="btn btn-primary">Xác thực</button>
               </form>

               </div>
             </div>
           </div>
     </main>
     <footer>

     </footer>
 </body>
 </html>