
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

     <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.min.js">

<script src="/resources/js/jquere-v3.5.1.js"></script>

            <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/brands.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/solid.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.2.1/css/fontawesome.css">
              <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css">

              <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
              <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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

              <div class="slise">

                                              <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                                <ol class="carousel-indicators">
                                                  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                                  <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                                  <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                                                </ol>
                                                <div class="carousel-inner">
                                                  <div class="carousel-item active">
                                                    <img height="300px" src="https://donghohungthinh.com/upload/upload/Sieu-giam-gia-30-phan-tram-700.jpg" class="d-block w-100" alt="...">
                                                  </div>
                                                  <div class="carousel-item">
                                                        <img height="300px" src="https://www.bigc.vn/files/banners/2021/jul-21/resize-template-mua1tang1-cover-blog-go.png" class="d-block w-100" alt="...">
                                                     </div>
                                                  <div class="carousel-item">
                                                    <img height="300px" src="https://hita.com.vn/storage/news_img/tin-t%E1%BB%A9c_5f39f775eee43.png" class="d-block w-100" alt="...">
                                                  </div>
                                                </div>
                                                <button class="carousel-control-prev" type="button" data-target="#carouselExampleIndicators" data-slide="prev">
                                                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                  <span class="sr-only">Previous</span>
                                                </button>
                                                <button class="carousel-control-next" type="button" data-target="#carouselExampleIndicators" data-slide="next">
                                                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                  <span class="sr-only">Next</span>
                                                </button>
                                              </div>
                                              <script>
                                            $('.carousel').carousel({
                                          interval: 3000
                                        })
                                              </script>
                                                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                                                  <script type="text/javascript" src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
                                  </div>


        <c:forEach items="${category_list}" var="category">
            <div class="container">
                <div class="product-frame">
                    <h3>${category.name}</h3>
                    <div class="row flex-wrap">
                        <c:forEach items="${category.productEntityList}" var="product">
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
        </c:forEach>

      </section>


  </main>
  <footer></footer>
  </body>
  </html>

