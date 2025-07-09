// Navbar Aç/Kapat
//document.getElementById("toggleMenu").addEventListener("click", () => {
  //  document.getElementById("sidebar").classList.toggle("hidden");
//});

// Ürün Ekleme Formu
document.getElementById("productForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const productName = document.getElementById("productName").value;
    const productStock = document.getElementById("productStock").value;
    const warehouse = document.getElementById("warehouse").value;
    const productPrice = document.getElementById("productPrice").value;

    // Yeni satır ekleme
    const table = document.getElementById("stockTable").querySelector("tbody");
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
        <td>${productName}</td>
        <td>${productStock}</td>
        <td>${warehouse}</td>
        <td>₺${productPrice}</td>
    `;
    table.appendChild(newRow);

    // Form temizle
    document.getElementById("productForm").reset();
});





