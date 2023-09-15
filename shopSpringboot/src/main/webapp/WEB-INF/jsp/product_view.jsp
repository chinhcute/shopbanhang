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
        <%@include file="/resources/css/admin.css" %>

    </style>
</head>
<body>
    <c:if test="${error_product == true }">
        <div class="modal fade" id="error_product" tabindex="-1" role="dialog" aria-labelledby="notEnoughQuantityModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: red; color: aliceblue;">
                        <h5 class="modal-title" id="notEnoughQuantityModalLabel">Thông báo</h5>
                    </div>
                    <div class="modal-body">
                       Sản phẩm đã tồn tại <br>
                       Đã cộng thêm số lượng sản phẩm. <br>
                       Vui lòng kiểm tra lại sản phẩm!


                    </div>
                    <div class="modal-footer">
                        <button type="button" id = "close" class="btn btn-secondary" style="background-color: red; color: aliceblue;" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
        <script>

     $(document).ready(function () {
                $("#error_product").modal("show");
            });
      $("#close").click(function() {
                $("#error_product").modal("hide");
           })
           </script>





    </c:if>

      <c:if test="${success_product == true }">
            <div class="modal fade" id="success_product" tabindex="-1" role="dialog" aria-labelledby="notEnoughQuantityModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header" style="background-color: rgb(48, 147, 5); color: aliceblue;">
                            <h5 class="modal-title" id="notEnoughQuantityModalLabel">Thông báo</h5>
                        </div>
                        <div class="modal-body">
                           Cập nhật thành công.


                        </div>
                        <div class="modal-footer">
                            <button type="button" id = "close_success_product" class="btn btn-secondary" style="background-color: rgb(48, 147, 5); color: aliceblue;" data-dismiss="modal">Đóng</button>
                        </div>
                    </div>
                </div>
            </div>
            <script>

         $(document).ready(function () {
                    $("#success_product").modal("show");
                });
          $("#close_success_product").click(function() {
                    $("#success_product").modal("hide");
               })
               </script>





        </c:if>
    <header>
        <a href="/home"><img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
        <div class="search">
            <form action="">
              <div style="margin-top: 30px;" class="input-group">
                <div  class="form-outline">
                  <input style="width: 700px;" type="search" id="form1" class="form-control"/>
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


        <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">price</th>
                <th scope="col">description</th>
                <th scope="col">quantity</th>
                <th scope="col">discount</th>
                <th scope="col">action</th>
              </tr>
            </thead>
            <tbody>
          <c:forEach items="${products}" var="product">
            <tr>
              <td>${product.id}</td>
              <td>${product.name}</td>
              <td>${product.price}</td>
              <td>${product.description}</td>
              <td>${product.quantity}</td>
              <td>${product.discount}</td>
              <td>
                <a href="/admin/edit_product/${product.id}" class="btn btn-primary">Sửa</a>
                <a href="/admin/delete_product/${product.id}" class="btn btn-danger">Xóa</a>
              </td>
            </tr>
          </c:forEach>


            </tbody>
          </table>
           <c:if test="${not empty products}">
               <ul class="pagination" style="margin-left: 40%;">
                   <c:forEach begin="0" end="${productEntityPage.totalPages - 1}" var="i">
                       <li class=" ${i == currentPage ? 'active' : ''}">
                           <a class="page-link" href="/admin/product_view?page=${i}" style="color: red;">${i + 1}</a>
                       </li>
                   </c:forEach>
               </ul>
           </c:if>


    </main>
   </div>

</body>
</html>