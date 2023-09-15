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
        height: 800px;
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
    height: 300px;
    min-width: 100%;
    background-image: linear-gradient(0deg, rgb(236, 18, 18), rgb(235, 99, 15));
   }
    </style>
</head>
<body>

  <header>


    <div class="headerimage">
     <a href="/admin/home"> <img src="https://i.pinimg.com/originals/37/76/d1/3776d1a3b7f3d91f30313583641564dc.gif" alt=""></a>
   </div>
   <h2>${name}</h2>
  </header>
   <main>
       <div class="container">
           <h1>${name}</h1>
           <form:form action="${action}" method="post" modelAttribute="product" enctype="multipart/form-data">
           <c:if test = "${name} eq 'edit product'">
           <div class="mb-3">
                   <label for="id" class="form-label">id</label>
                  <form:input path="id" type="number" class="form-control" id="id" placeholder="id"/>

            </div>
            </c:if>
               <div class="mb-3">
                   <label for="name" class="form-label">Tên sản phẩm</label>
                   <form:input path="name" type="text" class="form-control" id="name" placeholder="Nhập tên sản phẩm"/>
                   <small class="form-text text-muted"><form:errors path="name" class="text-danger" /></small>
               </div>
               <div class="mb-3">
                   <label for="description" class="form-label">Mô tả sản phẩm</label>
                   <form:textarea path="description" class="form-control" id="description" rows="3" placeholder="Nhập mô tả sản phẩm"></form:textarea>
                   <small class="form-text text-muted"><form:errors path="description" class="text-danger" /></small>
               </div>
               <div class="mb-3">
                   <label for="price" class="form-label">Giá sản phẩm</label>
                   <form:input path="price" type="text" class="form-control" id="price" placeholder="Nhập giá sản phẩm"/>
                   <small class="form-text text-muted"><form:errors path="price" class="text-danger" /></small>
               </div>
               <div class="mb-3">
                   <label for="discount" class="form-label">Giảm giá</label>
                   <form:input type="number" path="discount" class="form-control" id="discount" placeholder="Nhập giảm giá"/>
                   <small class="form-text text-muted"><form:errors path="discount" class="text-danger" /></small>
               </div>
               <div class="mb-3">
                   <label for="quantity" class="form-label">Số lượng</label>
                   <form:input path="quantity" type="number" class="form-control" id="quantity" placeholder="Nhập số lượng"/>
                   <small class="form-text text-muted"><form:errors path="quantity" class="text-danger" /></small>
               </div>

                     <div class="mb-3">
                         <label for="image" class="form-label">Hình ảnh</label>
                         <input name="imageFile" type="file" class="form-control" id="image" placeholder="Hình ảnh sản phẩm"  />
                         <div style = "color: red;" role="alert">
                                                    ${error}
                                                </div>
                     </div>

                 <div class="mb-3">
                     <label for="category" class="form-label">Danh mục sản phẩm</label>
                     <form:select class="form-control" id="category" path="category.id">
                         <form:option value="0" label="Chọn danh mục" />
                         <form:options items="${category_list}" />
                     </form:select>
                     <small class="form-text text-muted"><form:errors path="category.id" class="text-danger" /></small>
                 </div>


               <button type="submit" class="btn btn-primary">Thêm</button>
           </form:form>
       </div>
   </main>

    <footer>

    </footer>
</body>
</html>