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

    main{

    }
    h3{
        margin: 30px 0 0 60px ;
    }


   body {
  display: flex;
  flex-direction: column;

}
 .product__rating .fas{
    color: rgb(204, 194, 0);
}
.hien_thi a{
    text-decoration: none;
    color: rgb(49, 51, 54);
    font-size: 12px;
    font-weight: 2px;

}
.fa-star{
    color: rgb(232, 235, 11);
}



   footer{
    height: 300px;
    min-width: 100%;
    background-image: linear-gradient(0deg, rgb(236, 18, 18), rgb(235, 99, 15));
   }
   .hover--logout{
       background-color: aliceblue;
       height: 30px;

           margin: 2px 0 0 105px;
       border-radius:  30px 0 ;
       width: 80px;
       display: none;
      }

      .list--logout:hover .hover--logout{
       display: block;
      }
    <%@include file="/resources/css/header.css" %>

    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <h3><a href="/home" class="back-link"><i class="fas fa-arrow-left"></i></a></h3>
        <div class="container">
            <h1 class="mt-5">Chi tiết sản phẩm</h1>
            <div class="row">
                <div class="col-md-6"style="display: flex;flex-direction: column;" >
                    <img style="width: 300px;" src="data:image/jpeg;base64,${src}" class="img-fluid" alt="Product Image">
                     <img style="width: 300px;" src="data:image/jpeg;base64,${qr}" class="img-fluid" alt="Product Image">
                </div>

                <div class="col-md-6">
                    <h3>${product.name}</h3>
                    <p>Giá gốc: <del>${product.price}</del></p>
                    <p>${product.description}</p>
                    <div class="product__rating">
                        <i class="fas fa-star"></i>
                        ${rating}
                        <span>(125 đánh giá)</span>
                    </div>
                    <div class="discount__code">
                        <p>Giảm giá: ${product.discount}%</p>
                    </div>
                    <div class="buy__price">
                        <p>Giá mua hàng: ${product.totalAmount} </p>
                    </div>
                   <a href="/user/carts/${product.id}" class="btn btn-primary">Thêm vào giỏ hàng</a>

                </div>
            </div>
            <div class="row mt-5">
                <div class="col-md-12">
                    <h2>Đánh giá sản phẩm</h2>
                    <div class="user__rating">

                        <i class="fas fa-star"></i>
                                                 ${rating}
                                                 <span>(125 đánh giá)</span>
                    </div>
                    <div class="comments">
                        <h4>Bình luận:</h4>
                        <div class="comment">
                            <p>Tên người dùng: Bình luận của người dùng về sản phẩm...</p>
                        </div>

                    </div>
                    <div class="add-comment">
                        <h4>Thêm bình luận:</h4>
                       <form:form action="${action}" method="post" modelAttribute="review">
                           <div class="form-group">
                               <label for="userRating">Chọn số sao:</label>

                               <form:select class="form-control" id="userRating" path="rating">
                                   <form:option value="1">1 sao</form:option>
                                   <form:option value="2">2 sao</form:option>
                                   <form:option value="3">3 sao</form:option>
                                   <form:option value="4">4 sao</form:option>
                                   <form:option value="5">5 sao</form:option>
                               </form:select>

                           </div>

                           <div class="form-group">
                               <label for="userComment">Bình luận của bạn:</label>
                               <form:textarea path="comment" class="form-control" id="userComment" rows="3"></form:textarea>
                               <small class="form-text text-muted"><form:errors path="comment" class="text-danger" /></small>
                           </div>
                           <button type="submit" class="btn btn-primary">Gửi bình luận</button>
                       </form:form>

                    </div>
                    <h4>Bình luận:</h4>
                    <c:forEach items = "${product.reviewEntityList}" var = "review">
                    <div class="comment">
                      <div class="comment d-flex align-items-center">
                         <div style="width: 50px; height: 50px; background-color: rgb(207, 132, 71); border-radius: 50%; display: inline-flex; align-items: center; justify-content: center; color: white; font-size: 24px; font-weight: bold;">P</div>
                         <div>
                          <p class="ml-3"> <strong>${review.account.email}</strong>


                                <c:forEach begin="1" end="${review.rating}">
                                 <i class="fas fa-star"></i>
                               </c:forEach>

                          <br> ${review.comment}</p>
                        </div>

                      </div>

                    </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </main>

    <footer>

    </footer>
</body>
</html>