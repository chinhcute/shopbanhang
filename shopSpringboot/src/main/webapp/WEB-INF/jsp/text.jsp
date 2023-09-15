<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            var visibleProducts = 4;

            function loadProducts() {
                $.ajax({
                    type: 'GET',
                    url: '/products',
                    success: function(products) {
                        var productList = $('#productList');

                        productList.empty();

                        for (var i = 0; i < products.length; i++) {
                            var product = products[i];

                            if (i < visibleProducts) {
                                productList.append('<li>' + product.name + '</li>');
                            }
                        }

                        if (visibleProducts >= products.length) {
                            $('#loadMoreButton').hide();
                        } else {
                            $('#loadMoreButton').show();
                        }
                    }
                });
            }

            loadProducts();

            $('#loadMoreButton').click(function() {
                visibleProducts += 4;
                loadProducts();
            });
        });
    </script>
</head>
<body>
    <h1>Products</h1>
    <ul id="productList"></ul>
    <button id="loadMoreButton" style="display: none;">Xem thêm</button>
</body>
</html>
