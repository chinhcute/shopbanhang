    document.getElementById("paymentMethod").addEventListener("change", function() {
        var bankAccountField = document.getElementById("bankAccount");
        if (this.value === "online") {

            bankAccountField.style.display = "block";
        } else {

            bankAccountField.style.display = "none";
        }
    });