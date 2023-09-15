 $(document).ready(function() {

        $("#searchSuggestions").on("click", "li", function() {
           var selectedValue = $(this).text();
           $("#form1").val(selectedValue);
           $("#searchSuggestions").empty();
         });
            $(document).on("click", function(event) {
                 var clickedElement = $(event.target);
                 if (!clickedElement.closest("#searchSuggestions").length) {
                     $("#searchSuggestions").empty();
                 }
             });

           $("#form1").keyup(function() {
               var searchValue = $(this).val();

               $.ajax({
                   url: "/search",
                   data: { search: searchValue },
                   success: function(response) {
                       var suggestions = $("#searchSuggestions");
                       suggestions.empty();


                       for (var i = 0; i < response.length; i++) {
                           suggestions.append("<li class='list-group-item'>" + response[i] + "</li>");
                       }
                   }
               });
           });
       });