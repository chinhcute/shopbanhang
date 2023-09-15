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
              }
               #loader {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: 9999;

                background: url('https://4kwallpapers.com/images/wallpapers/hello-typography-gradient-background-colorful-white-5120x2880-5956.jpg') center no-repeat;
                background-size: 1500px;

                animation: zoomInOut 6s forwards;
                animation-delay: 0s;
              }

              @keyframes zoomInOut {
                0% {
                  background-size: 1500px;
                }
                50% {
                  background-size: 2000px;
                }
                100% {
                  background-size: 2300px;
                  visibility: hidden;

                }
              }
              header{
                min-width: 1000px;
                background-image: linear-gradient(180deg, rgb(236, 18, 18), rgb(235, 99, 15));
                height: 150px;
                padding: 0 100px;

              }

              header .nav_one{
                height: 40px;
                display: flex;
                justify-content: space-between;
              }
              .header__phai ul,
          .header__trai ul{
              display: flex;



          }
          .header__item--gach::after{
              content: " ";
              display: block;
              height: 8px;
              width: 1px;
              background-color: #c7bcb9;
          }
          .header__item{
                 list-style: none;
          }
          .header__item a{

              margin-left: 19px;
              color: aliceblue;
              text-decoration: none;
          }
          .header__phai{
              margin-right: 50px;
          }
          .header__item a:hover{
              color: rgb(186, 183, 179);
              cursor: pointer;
          }

          .header__item--gach::after{
              content: " ";
              display: block;
              height: 15px;
              width: 1px;
              background-color: #baafac;
              position: relative;
              left: 140px;
              transform: translateY(-16px);
          }

          .header__item--gach1::after{
              content: " ";
              display: block;
              height: 15px;
              width: 1px;
              background-color: #baafac;
              position: relative;
              left: 230px;
              transform: translateY(-16px);
          }
          .header__item--gach2::after{
              content: " ";
              display: block;
              height: 15px;
              width: 1px;
              background-color: #baafac;
              position: relative;
              left: 120px;
              transform: translateY(-16px);
          }
          .header__item--gach3::after{
              content: " ";
              display: block;
              height: 15px;
              width: 1px;
              background-color: #baafac;
              position: relative;
              left: 80px;
              transform: translateY(-16px);
          }
             .headerimage img{
              width: 120px;
             }
             .image_search{
              display: flex;
              justify-content: space-between;
              margin: 0 60px;

             }
             .header_icon a i{
              font-size: 30px;
              color: aliceblue;
              margin-top: 30px;
             }
             .nav_product ul{
              display: flex;
              justify-content: space-around;
              margin-top: 8px;
              position: relative;
              left: -50px;


             }
             .product a{
              color: aliceblue;
              text-decoration: none;

             }
             .product {

              list-style: none;
              text-decoration: none;
             }
             .btn{
              margin-left: 30px;

             }
             .nav_product li :hover{
              color: rgb(203, 131, 102);

             }
             body {
            display: flex;
            flex-direction: column;

          }


             main{

              margin: 20px 100px 0 100px;

              max-width: 100%;

             }
             main nav{
              max-width: 200px;


              float: left;
             }
             main ul li{
              list-style: none;
              text-decoration: none;
              color: aliceblue;
             }
             main ul li a{
              line-height: 20px;
              color: black;
             }
             main nav {
              padding: 20px;
             }
             main h6{
              line-height: 20px;
             }
             main form input{
              width: 50px;


             }
             main form {
              display: flex;
             }
             section{
              float: left;
              max-height: 100%;
             }
             .flex-wrap{
              width: 1030px;


             }

             .fas{
              color: #efcc08;
             }
             footer{
              height: 300px;
              min-width: 100%;
              background-image: linear-gradient(0deg, rgb(236, 18, 18), rgb(235, 99, 15));
             }
          </style>
</head>
<body>
 <div id="loader"></div>
   <header>
    <nav class="nav_one">
     <div class="header__trai">
       <ul>
           <li class="header__item header__item--gach"> <a href="">Kênh Người Bán</a> </li>
           <li class="header__item header__item--gach1"> <a href="">Trở thành Người bán Shopee</a> </li>
           <li class="header__item header__item--gach2"> <a href="">Tải ứng dụng</a> </li>
           <li class="header__item header__item--icon"> <a href=""> Kết nối</a>
               <i class="fa-brands fa-facebook"></i>
               <i class="fa-brands fa-instagram"></i>
           </li>
       </ul>
   </div>
   <div class="header__phai">
       <ul>
           <li class="header__item"><a href="">Thông Báo</a> </li>
           <li class="header__item"><a href=""> Hổ trợ </a></li>
           <li class="header__item"><a href="">Tiếng Việt</a> </li>
           <li class="header__item header__item--gach3"><a href="">Đăng kí</a> </li>
           <li class="header__item"><a href="">Đăng Nhâp</a> </li>
       </ul>
   </div>
 </div>
    </nav>
   <div class="image_search">
     <div class="headerimage">
      <a href=""> <img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
    </div>
    <div class="search">
     <form action="">
       <div style="margin-top: 30px; clear: both;" class="input-group">
         <div  class="form-outline">
           <input style="width: 700px;" type="search" id="form1" class="form-control"/>
         </div>
         <button type="button" class="btn btn-danger">
           <i class="fas fa-search"></i>
         </button>
     </div>
     <div class="nav_product">
       <ul>
         <li class="product"><a href="">Áo nam</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
     <li class="product"><a href="#">Sữa</a></li>
       </ul>
     </div>
     </form>

    </div>
    <div class="header_icon">
    <a href=""> <i class="fa-solid fa-cart-shopping"></i></a>
    </div>
   </div>

   </header>

   <main>

       <nav>
         <h6>Theo Danh Mục</h6>
         <ul>
           <li class="listdanhmuc"><a href="">Phụ kiện</a></li>
           <li class="listdanhmuc"><a href="">Phụ kiện</a></li>
           <li class="listdanhmuc"><a href="">Phụ kiện</a></li>
           <li class="listdanhmuc"><a href="">Phụ kiện</a></li>
         </ul>
         <hr>
         <h6>Nơi Bán</h6>
         <ul>
           <li class="Listnoiban"><a href="">Quảng Nam</a></li>
           <li class="Listnoiban"><a href="">Quảng Nam</a></li>
           <li class="Listnoiban"><a href="">Quảng Nam</a></li>
           <li class="Listnoiban"><a href="">Quảng Nam</a></li>
         </ul>
         <hr>
         <h6>Thương Hiệu</h6>
         <ul>
           <li class="listthuongHieu"><a href="">Chờ neo</a></li>
           <li class="listthuongHieu"><a href="">Chờ neo</a></li>
           <li class="listthuongHieu"><a href="">Chờ neo</a></li>
           <li class="listthuongHieu"><a href="">Chờ neo</a></li>
         </ul>
         <hr>
         <h6>Khoảng Giá</h6>
        <form action="">
         <input type="number" placeholder="Stat">
         <input style="margin-left: 3px;" placeholder="End" type="number">
         <button type="button" class="btn btn-danger">
           <i class="fas fa-search"></i>
         </button>
        </form>

       </nav>
       <section>

           <div class="container">
             <div class="product-frame">
               <h3>Khung Sản Phẩm</h3>
               <div class="row flex-wrap">
                 <div class="col-sm-3">
                   <div class="card">
                     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 1">
                     <div class="card-body">
                       <h5 class="card-title">Sản phẩm 1</h5>
                       <p>Giảm giá</p>
                       <p class="card-text">Mô tả sản phẩm 1.</p>
                       <div class="product__rating">
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star-half-alt"></i>
                         <span>(125)</span>
                     </div>
                       <p>Nơi bán</p>
                       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
                     </div>
                   </div>
                 </div>
                 <div class="col-sm-3">
                   <div class="card">
                     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 2">
                     <div class="card-body">
                       <h5 class="card-title">Sản phẩm 2</h5>
                       <p>Giảm giá</p>
                       <p class="card-text">Mô tả sản phẩm 2.</p>
                       <div class="product__rating">
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star-half-alt"></i>
                         <span>(125)</span>
                     </div>
                       <p>Nơi bán</p>
                       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
                     </div>
                   </div>
                 </div>
                 <div class="col-sm-3">
                   <div class="card">
                     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 3">
                     <div class="card-body">
                       <h5 class="card-title">Sản phẩm 3</h5>
                       <p>Giảm giá</p>
                       <p class="card-text">Mô tả sản phẩm 3.</p>
                       <div class="product__rating">
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star-half-alt"></i>
                         <span>(125)</span>
                     </div>
                       <p>Nơi bán</p>
                       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
                     </div>
                   </div>
                 </div>
                 <div class="col-sm-3">
                   <div class="card">
                     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 4">
                     <div class="card-body">
                       <h5 class="card-title">Sản phẩm 4</h5>
                       <p>Giảm giá</p>
                       <p class="card-text">Mô tả sản phẩm 4.</p>
                       <div class="product__rating">
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star-half-alt"></i>
                         <span>(125)</span>
                     </div>
                       <p>Nơi bán</p>
                       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
                     </div>
                   </div>
                 </div>



                 <div class="col-sm-3">
                   <div class="card">
                     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 4">
                     <div class="card-body">
                       <h5 class="card-title">Sản phẩm 5</h5>
                       <p>Giảm giá</p>
                       <p class="card-text">Mô tả sản phẩm 4.</p>
                       <div class="product__rating">
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star"></i>
                         <i class="fas fa-star-half-alt"></i>
                         <span>(125)</span>
                     </div>
                       <p>Nơi bán</p>
                       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
                     </div>
                   </div>
                 </div>

           <div class="col-sm-3">
             <div class="card">
               <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 4">
               <div class="card-body">
                 <h5 class="card-title">Sản phẩm 5</h5>
                 <p>Giảm giá</p>
                 <p class="card-text">Mô tả sản phẩm 4.</p>
                 <div class="product__rating">
                   <i class="fas fa-star"></i>
                   <i class="fas fa-star"></i>
                   <i class="fas fa-star"></i>
                   <i class="fas fa-star"></i>
                   <i class="fas fa-star-half-alt"></i>
                   <span>(125)</span>
               </div>
                 <p>Nơi bán</p>
                 <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
               </div>
             </div>
           </div>


     <div class="col-sm-3">
       <div class="card">
         <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 4">
         <div class="card-body">
           <h5 class="card-title">Sản phẩm 5</h5>
           <p>Giảm giá</p>
           <p class="card-text">Mô tả sản phẩm 4.</p>
           <div class="product__rating">
             <i class="fas fa-star"></i>
             <i class="fas fa-star"></i>
             <i class="fas fa-star"></i>
             <i class="fas fa-star"></i>
             <i class="fas fa-star-half-alt"></i>
             <span>(125)</span>
         </div>
           <p>Nơi bán</p>
           <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
         </div>
       </div>
     </div>


 <div class="col-sm-3">
   <div class="card">
     <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS31n6fYSDDO875oA06FYOFtc3J4wtUtz4lXw&usqp=CAU" class="card-img-top" alt="Product 4">
     <div class="card-body">
       <h5 class="card-title">Sản phẩm 5</h5>
       <p>Giảm giá</p>
       <p class="card-text">Mô tả sản phẩm 4.</p>
       <div class="product__rating">
         <i class="fas fa-star"></i>
         <i class="fas fa-star"></i>
         <i class="fas fa-star"></i>
         <i class="fas fa-star"></i>
         <i class="fas fa-star-half-alt"></i>
         <span>(125)</span>
     </div>
       <p>Nơi bán</p>
       <a href="#" class="btn btn-primary">Thêm vào giỏ hàng</a>
     </div>
   </div>
 </div>





       </section>

   </main>
   <footer></footer>

</body>
</html>