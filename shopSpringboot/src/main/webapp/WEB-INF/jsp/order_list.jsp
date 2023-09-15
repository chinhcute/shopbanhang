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

          .search{
              width: 1000px;
              margin: auto 100px;
              display: flex;
          }
          .form-outline{
            display: flex;
          }
          footer{
          margin-top : 250px;
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
         <h2>Order</h2>
        </header>


  <main>

  <c:if test="${order_success}">
      <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header" style="background-color: rgb(39, 119, 4); color: aliceblue;">
                      <h5 class="modal-title" id="successModalLabel">Thành công</h5>
                  </div>
                  <div class="modal-body">
                     Huỷ đơn hàng thành công
                  </div>
                  <div class="modal-footer">
                      <button type="button" id = "close_success" style="background-color: rgb(48, 147, 5); color: aliceblue;" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                  </div>
              </div>
          </div>
      </div>
      <script>
        $(document).ready(function () {
                      $("#successModal").modal("show");
                  });

                  $("#close_success").click(function() {
                                  $("#successModal").modal("hide");
                             })
        </script>

  </c:if>

        <div class="container mt-5">
            <h1 class="mb-4">Order</h1>
            <div class="btn-group mb-3" role="group">
                      <div class="btn-group">
                            <a href="/user/order_list" style="width: 250px;" class="btn btn-danger ${param.status == null ? 'active' : ''}" >Tất cả</a>
                            <a href="/user/order_list?status=Completed" style="width: 250px;" class="btn btn-danger ${param.status == 'Completed' ? 'active' : ''}">Hoàn thành</a>
                            <a href="/user/order_list?status=Unpaid" style="width: 250px;" class="btn btn-danger ${param.status == 'Unpaid' ? 'active' : ''}">Chưa thanh toán</a>
                            <a href="/user/order_list?status=Paid" style="width: 260px;" class="btn btn-danger ${param.status == 'Paid' ? 'active' : ''}">Đã thanh toán</a>
                            <a href="/user/order_list?status=Cancel" style="width: 290px;" class="btn btn-danger ${param.status == 'Cancel' ? 'active' : ''}">Hủy</a>
                        </div>
                </div>
            <c:if test="${empty orderEntityList}">
                 <img style="margin: 0 30%; width: 30%;"  src="https://afbruxelles.extranet-aec.com/img/empty-cart.png" alt="">
            </c:if>
            <c:if test="${not empty orderEntityList}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Created Date</th>
                            <th>Shipped Date</th>
                            <th>Status</th>
                            <th>Shipping Address</th>
                            <th>Payment Type</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orderEntityList}" var="order">
                            <tr>
                                <td>${order.name}</td>
                                <td>${order.createdDate}</td>
                                <td>${order.shippedDate}</td>
                                <td>${order.status}</td>
                                <td>${order.shippingAddress}</td>
                                <td>${order.paymentType}</td>
                                 <td>
                                           <c:if test="${order.status != 'Completed' && order.status != 'Cancel'}">
                                                         <a href="/user/delete_orders/${order.id}" class="btn btn-danger">Huỷ</a>
                                                     </c:if>
                                 </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>


        </div>
    </main>

    <footer>
    </footer>







      </body>
      </html>