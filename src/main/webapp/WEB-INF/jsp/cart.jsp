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
  <script src="/resources/js/cart.js"></script>
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
          margin-top: 170px;
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

  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header" style="background-color: red; color: aliceblue;">
                      <h5 class="modal-title" id="myModalLabel">Thông báo</h5>

                  </div>
                  <div class="modal-body">
                      Vui lòng chọn sản phẩm để tiến hành đặt hàng.
                  </div>
                  <div class="modal-footer">
                      <button id = "close" type="button" style="background-color: red; color: aliceblue;" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                  </div>
              </div>
          </div>
      </div>



<c:if test="${showErrorModal}">
    <div class="modal fade" id="notEnoughQuantityModal" tabindex="-1" role="dialog" aria-labelledby="notEnoughQuantityModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header" style="background-color: red; color: aliceblue;">
                    <h5 class="modal-title" id="notEnoughQuantityModalLabel">Thông báo</h5>
                </div>
                <div class="modal-body">
                    Số lượng sản phẩm không đủ. Vui lòng kiểm tra lại số lượng.<br>
                    <c:forEach items="${productEntityList}" var="product">
                    Sản phẩm: ${product.name}. Mua tối đa: ${product.quantity}<br>
                   </c:forEach>


                </div>
                <div class="modal-footer">
                    <button type="button" id = "close_quantity" class="btn btn-secondary" style="background-color: red; color: aliceblue;" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>


</c:if>

<c:if test="${showSuccess}">
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header" style="background-color: rgb(39, 119, 4); color: aliceblue;">
                    <h5 class="modal-title" id="successModalLabel">Thành công</h5>
                </div>
                <div class="modal-body">
                    Mua hàng thành công.
                </div>
                <div class="modal-footer">
                    <button type="button" id = "close_success" style="background-color: rgb(48, 147, 5); color: aliceblue;" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>


</c:if>



        <div class="container mt-5">
            <h1 class="mb-4">Shopping Cart</h1>
            <div>
            <a href="/user/order_list" class="btn btn-primary">Go to Order</a>
            </div>
            <c:if test="${empty cart.cartItemEntityList}">

                <img style="margin: 0 30%;"  src="https://bizweb.dktcdn.net/100/371/296/themes/743486/assets/empty-cart.png?1679977133576" alt="">

            </c:if>
            <c:if test="${not empty cart.cartItemEntityList}">

                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">choose product</th>
                            <th scope="col">Product Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart.cartItemEntityList}" var="cartItem">
                            <tr>
                                <td>
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" name="selectedProducts" value="${cartItem.id}">
                                    </div>
                                </td>
                                <td>${cartItem.product.name}</td>
                                <td>${cartItem.product.price}</td>
                              <td>
                               <div class="form-group">
                                  <input type="number" class="form-control" data-cartItemIds="${cartItem.id}" name="quantity" value="${cartItem.quantity}">
                               </div>
                              </td>
                              <td id="total_product_${cartItem.id}">${cartItem.totalAllAmount}đ</td>


                                <td>
                                    <a href="/user/delete_cart/${cartItem.id}" class="btn btn-danger">Xóa</a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="5" class="text-right">Total:</th>
                            <th id="totalAmount"></th>
                        </tr>
                    </tfoot>
                </table>
                 <div class="text-right">
                                <button type="button" class="btn btn-primary" id="buyButton">Mua sản phẩm</button>
                 </div>
            </c:if>


        </div>
    </main>

    <footer>
        <!-- Nội dung phần footer của bạn ở đây -->
    </footer>







      </body>
      </html>