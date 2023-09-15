<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

  <div id="loader"></div>
  <header>
   <nav class="nav_one">
    <div class="header__trai">
      <ul>
          <li class="header__item header__item--gach"> <a href="/admin/home">Kênh Người Bán</a> </li>
          <li class="header__item header__item--gach1"> <a href="/signout-admin">Trở thành Người bán Shopee</a> </li>
          <li class="header__item header__item--gach2"> <a href="/user/add_bank">Bank</a> </li>
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

          <c:choose>
                             <c:when test="${email != null and email != 'anonymousUser'}">

                                 <li class="header__item list--logout" >
                                 <a href="/user/account" class="account-link">
                                     ${email}
                                   </a>
                                      <div class="hover--logout"><a href="/logout" style="color: black; text-align: center;">Logout</a></div>
                                 </li>




                             </c:when>
                             <c:otherwise>
                             <li class="header__item header__item--gach3"><a href="/signout">Đăng kí</a> </li>
                                 <li class="header__item"><a href="/login">Đăng nhập</a>
                                 </li>
                             </c:otherwise>
                         </c:choose>
      </ul>
  </div>
</div>
   </nav>
  <div class="image_search">
    <div class="headerimage">
     <a href="/home"> <img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
   </div>

<script src="/resources/js/header.js"></script>



   <div class="search">

    <form action="/search/product" method = "GET">
      <div style="margin-top: 30px; clear: both;" class="input-group">
        <div  class="form-outline">
          <input style="width: 700px;" type="search" name="search" id="form1" class="form-control"/>
          <ul id="searchSuggestions" class="list-group"></ul>
        </div>
        <button type="submit" class="btn btn-danger">
          <i class="fas fa-search"></i>
        </button>
    </div>
    <div class="nav_product">
      <ul>
      <c:forEach items="${productEntityLists}" var="product">
          <li class="product">
              <a href="/view_detail/${product.id}">${product.name}</a>
          </li>
      </c:forEach>

      </ul>
    </div>
    </form>

   </div>
   <div class="header_icon">
   <a href="/user/cart"> <i class="fa-solid fa-cart-shopping"></i></a>
   </div>
  </div>

  </header>





