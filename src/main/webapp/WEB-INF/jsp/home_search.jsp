
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

     <Style>
    <%@include file="/resources/css/home.css" %>
     </Style>
  </head>
  <body>
   <jsp:include page="header.jsp" />

<main>

        <nav>
               <h6>Theo Danh Mục</h6>
                <hr>
               <h6>Các loại sản phẩm</h6>
                   <ul>
                   <c:forEach items = "${category_list}" var = "category">
                       <li><a href="/search/${category.name}">${category.name}</a><li>

                   </c:forEach>

                   </ul>

               <hr>

               <h6>Đánh giá</h6>
             <ul>
                 <li><a href="/search/rating/5"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></a></li>
                 <li><a href="/search/rating/4"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fa-light fa-star"></i>Trở lên</a></li>
                 <li><a href="/search/rating/3"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i>Trở lên</a></li>
                 <li><a href="/search/rating/2"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i>Trở lên</a></li>
                 <li><a href="/search/rating/1"><i class="fas fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i><i class="fa-light fa-star"></i>Trở lên</a></li>
                  </ul>


               <hr>
               <h6>Khoảng Giá</h6>
             <div class="container">
               <form action="/search/price" method= "get">
                       <input name= "stat" type="number" placeholder="Stat" required>
                       <input name="end" style="margin-left: 3px;" placeholder="End" type="number" required>

                       <button type="submit" class="btn btn-danger">
                         <i class="fas fa-search"></i>
                       </button>
                      </form>
              </div>

             </nav>
      <section>



              <div class="container">
                  <div class="product-frame">

                      <div class="row flex-wrap">
                      <c:if test="${empty productEntityList}">
                                     <img style="margin: 0 30%; width: 30%; margin-top : 50px; "  src="data:image/png;base64,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" alt="">
                                  </c:if>
                          <c:forEach items="${productEntityList}" var="product">
                            <c:if test="${product.quantity > 0}">
                                <div class="col-sm-3">
                                    <div class="card">
                                        <img style="height: 200px;" src="data:image/jpeg;base64,${map[product.id]}" class="card-img-top" alt="${product.name}">
                                        <div class="card-body">
                                            <h5 class="card-title">${product.name}</h5>
                                            <p>Giá gốc: <del>${product.price}</del></p>
                                            <p>Giảm giá: ${product.discount}%</p>
                                            <!-- Hiển thị đánh giá và số lượng đánh giá -->
                                            <div class="product__rating">
                                            <i class="fas fa-star"></i>
                                                ${rating[product.id]}
                                                <span>(125)</span>
                                            </div>
                                            <p>Giá mua hàng: ${product.totalAmount}</p>
                                            <p>Số lượng : ${product.quantity}</p>
                                            <p>Đã bán: ${product.sold}</p>
                                            <a href="/view_detail/${product.id}" class="btn btn-primary">Thêm vào giỏ hàng</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                          </c:forEach>
                      </div>
                  </div>
              </div>

      </section>


  </main>
  <footer></footer>
  </body>
  </html>

