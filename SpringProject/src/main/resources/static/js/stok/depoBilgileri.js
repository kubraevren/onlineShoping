// depoBilgileri.js
document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("warehouseModal");
    const addWarehouseBtn = document.getElementById("addWarehouseBtn");
    const closeBtn = document.querySelector(".close-btn");
    const warehouseForm = document.getElementById("warehouseForm");
    const warehouseModalTitle = document.getElementById("warehouseModalTitle");
  
    // Depo Ekleme Butonuna Tıklanınca Modalı Aç
    addWarehouseBtn.addEventListener("click", function() {
      modal.style.display = "block";
      warehouseModalTitle.innerText = "Depo Ekle";
      warehouseForm.reset();
      warehouseForm.dataset.mode = "add"; // Ekleme modu
    });
  
    // Modalı Kapatmak için Kapatma Butonuna Tıklayınca
    closeBtn.addEventListener("click", function() {
      modal.style.display = "none";
    });
  
    // Modalın dışına tıklayınca kapat
    window.addEventListener("click", function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    });
  
    // Form Gönderildiğinde
    warehouseForm.addEventListener("submit", function(event) {
      event.preventDefault();
  
      const warehouseName = document.getElementById("warehouseName").value.trim();
      const warehouseLocation = document.getElementById("warehouseLocation").value.trim();
      const warehouseCapacity = document.getElementById("warehouseCapacity").value;
      const warehouseStock = document.getElementById("warehouseStock").value;
      const warehouseStatus = document.getElementById("warehouseStatus").value;
  
      if (warehouseName === "" || warehouseLocation === "" || warehouseCapacity === "" || warehouseStock === "" || warehouseStatus === "") {
        alert("Lütfen tüm alanları doldurun.");
        return;
      }
  
      const mode = warehouseForm.dataset.mode;
  
      if (mode === "add") {
        addWarehouse(warehouseName, warehouseLocation, warehouseCapacity, warehouseStock, warehouseStatus);
      } else if (mode === "edit") {
        const rowIndex = parseInt(warehouseForm.dataset.rowIndex);
        editWarehouse(rowIndex, warehouseName, warehouseLocation, warehouseCapacity, warehouseStock, warehouseStatus);
      }
  
      modal.style.display = "none";
    });
  
    // Depo Ekleme Fonksiyonu
    function addWarehouse(name, location, capacity, stock, status) {
      const tbody = document.querySelector(".warehouse-list tbody");
      const rowCount = tbody.rows.length + 1;
      const row = tbody.insertRow();
  
      row.insertCell(0).innerText = rowCount;
      row.insertCell(1).innerText = name;
      row.insertCell(2).innerText = location;
      row.insertCell(3).innerText = capacity;
      row.insertCell(4).innerText = stock;
  
      const statusCell = row.insertCell(5);
      if (status === "İyi") {
        statusCell.innerHTML = '<span class="status in-stock">İyi</span>';
      } else if (status === "Düşük") {
        statusCell.innerHTML = '<span class="status low-stock">Düşük</span>';
      } else {
        statusCell.innerHTML = '<span class="status completed">Tamamlandı</span>';
      }
  
      const actionsCell = row.insertCell(6);
      actionsCell.innerHTML = `
        <button class="edit-btn"><i class="fas fa-edit"></i> Düzenle</button>
        <button class="delete-btn"><i class="fas fa-trash-alt"></i> Sil</button>
      `;
  
      // Yeni eklenen satır için event listener ekle
      addRowEventListeners(row);
    }
  
    // Depo Düzenleme Fonksiyonu
    function editWarehouse(index, name, location, capacity, stock, status) {
      const tbody = document.querySelector(".warehouse-list tbody");
      const row = tbody.rows[index];
  
      row.cells[1].innerText = name;
      row.cells[2].innerText = location;
      row.cells[3].innerText = capacity;
      row.cells[4].innerText = stock;
  
      const statusCell = row.cells[5];
      if (status === "İyi") {
        statusCell.innerHTML = '<span class="status in-stock">İyi</span>';
      } else if (status === "Düşük") {
        statusCell.innerHTML = '<span class="status low-stock">Düşük</span>';
      } else {
        statusCell.innerHTML = '<span class="status completed">Tamamlandı</span>';
      }
    }
  
    // Satırları Silme Fonksiyonu
    function deleteWarehouse(row) {
      if (confirm("Bu depoyu silmek istediğinize emin misiniz?")) {
        row.remove();
        updateRowNumbers();
      }
    }
  
    // Satır Numaralarını Güncelleme Fonksiyonu
    function updateRowNumbers() {
      const tbody = document.querySelector(".warehouse-list tbody");
      Array.from(tbody.rows).forEach((row, index) => {
        row.cells[0].innerText = index + 1;
      });
    }
  
    // Mevcut satırlara event listener ekle
    const existingRows = document.querySelectorAll(".warehouse-list tbody tr");
    existingRows.forEach((row, index) => {
      addRowEventListeners(row, index);
    });
  
    // Yeni eklenen satırlara event listener ekleme
    function addRowEventListeners(row) {
      const editBtn = row.querySelector(".edit-btn");
      const deleteBtn = row.querySelector(".delete-btn");
  
      editBtn.addEventListener("click", function() {
        const cells = row.cells;
        const name = cells[1].innerText;
        const location = cells[2].innerText;
        const capacity = cells[3].innerText;
        const stock = cells[4].innerText;
        const status = cells[5].innerText;
  
        modal.style.display = "block";
        warehouseModalTitle.innerText = "Depo Düzenle";
        warehouseForm.warehouseName.value = name;
        warehouseForm.warehouseLocation.value = location;
        warehouseForm.warehouseCapacity.value = capacity;
        warehouseForm.warehouseStock.value = stock;
        warehouseForm.warehouseStatus.value = status === "İyi" ? "İyi" : (status === "Düşük" ? "Düşük" : "Tamamlandı");
        warehouseForm.dataset.mode = "edit";
        warehouseForm.dataset.rowIndex = row.rowIndex - 1; // table headers satırını hesaba katmak için
      });
  
      deleteBtn.addEventListener("click", function() {
        deleteWarehouse(row);
      });
    }
  });
  