<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hiển thị hình ảnh</title>
</head>
<body>
    <h1>Hiển thị hình ảnh</h1>
<c:if test="${empty imageDataList}">
                    <p>No data</p>
                  </c:if>
    <c:forEach items="${imageDataList}" var="imageData">

        <img src="data:image/jpeg;base64,${imageData}" alt="Hình ảnh">
    </c:forEach>



</body>
</html>