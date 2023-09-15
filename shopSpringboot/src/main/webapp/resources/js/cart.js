
    $(document).ready(function () {

        $('input[name="selectedProducts"]').change(function () {
            var selectedProducts = $('input[name="selectedProducts"]:checked').map(function () {
                return $(this).val();
            }).get();

            if (selectedProducts.length === 0) {
                $('#totalAmount').text(0);
            }

            $.ajax({
                type: "GET",
                url: "/total",
                data: {selectedProducts: selectedProducts},
                traditional: true,
                success: function (data) {
                    $('#totalAmount').text(data);
                }
            });
        });
$("#buyButton").click(function () {
    var selectedProducts = [];
    $("input[name='selectedProducts']:checked").each(function () {
        var cartItemId = $(this).val();
        selectedProducts.push(parseInt(cartItemId));
    });
    if(selectedProducts.length === 0){
        $("#myModal").modal("show");
    } else{
       $.ajax({
            type: "GET",
            url: "/user/order",
            data: { listId: selectedProducts },
            traditional: true,
            success: function (data) {
                window.location.href = "/user/order_after";
            },
            error: function () {
                alert("Vui lòng chọn sản phẩm");
            }
        });
    }


});

 $("#close").click(function() {
        $("#myModal").modal("hide");
   })

    $("#close_quantity").click(function() {
           $("#notEnoughQuantityModal").modal("hide");
      })


 $(document).ready(function () {
                $("#notEnoughQuantityModal").modal("show");
    });

     $(document).ready(function () {
                $("#successModal").modal("show");
            });
      $("#close_success").click(function() {
                $("#successModal").modal("hide");
           })




$('input[name="quantity"]').change(function () {
console.log('Sự kiện change đã được kích hoạt.');
    var cartItemId = $(this).attr('data-cartItemIds');


    if (typeof cartItemId !== 'undefined') {
    console.log('Sự kiện change đã được kích hoạt.');
        var quantity = parseInt($(this).val());

        var formData = new FormData();
        formData.append('cartItemId', cartItemId);
        formData.append('quantity', quantity);

        $.ajax({
            type: "POST",
            url: "/updateQuantity",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                $('#total_product_' + cartItemId).text(data.totalAmount + 'đ');
            },
            error: function () {
                alert("Đã xảy ra lỗi khi cập nhật số lượng!");
            }
        });
    }
});
 });




