// dashboard.js
document.addEventListener("DOMContentLoaded", function() {
  // Özet Kartlar Verilerini Dinamik Olarak Güncelleme
  // Bu kısım backend entegrasyonu ile dinamikleştirilebilir
  document.getElementById('totalProducts').innerText = '1,200';
  document.getElementById('totalWarehouses').innerText = '5';
  document.getElementById('dailySales').innerText = '₺25,000';
  document.getElementById('newCustomers').innerText = '120';

  // İşlem Paneli Butonları
  document.getElementById('addProductBtn').addEventListener('click', function() {
    window.location.href = 'urunler.html';
  });

  document.getElementById('addWarehouseBtn').addEventListener('click', function() {
    window.location.href = 'depoBilgileri.html';
  });

  document.getElementById('generateReportBtn').addEventListener('click', function() {
    window.location.href = 'adminRaporlar.html';
  });

  document.getElementById('settingsBtn').addEventListener('click', function() {
    window.location.href = 'ayarlar.html';
  });

  // Aylık Satış Grafiği (Line Chart)
  var ctx = document.getElementById('monthlySalesChart').getContext('2d');
  var monthlySalesChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran', 'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık'],
      datasets: [{
        label: 'Satış Miktarı (₺)',
        data: [20000, 25000, 22000, 27000, 30000, 35000, 32000, 33000, 31000, 40000, 42000, 45000],
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor: '#4bc0c0',
        borderWidth: 2,
        fill: true,
        tension: 0.4
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          display: true,
          position: 'top',
        },
        title: {
          display: false,
        }
      },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            callback: function(value) {
              return '₺' + value;
            }
          }
        }
      }
    }
  });

  // Ürün Stok Durumu (Doughnut Chart)
  var ctxDoughnut = document.getElementById('productStockChart').getContext('2d');
  var productStockChart = new Chart(ctxDoughnut, {
    type: 'doughnut',
    data: {
      labels: ['Stokta', 'Düşük Stok', 'Stokta Yok'],
      datasets: [{
        label: 'Stok Durumu',
        data: [800, 300, 100],
        backgroundColor: [
          '#28a745', // Yeşil
          '#ffc107', // Sarı
          '#dc3545'  // Kırmızı
        ],
        hoverOffset: 4
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'right',
        },
        title: {
          display: false,
        }
      }
    }
  });

  // Depo Bazlı Stok Dağılımı (Bar Chart)
  var ctxBar = document.getElementById('warehouseStockChart').getContext('2d');
  var warehouseStockChart = new Chart(ctxBar, {
    type: 'bar',
    data: {
      labels: ['Merkez Depo', 'Doğu Depo', 'Batı Depo', 'Kuzey Depo', 'Güney Depo'],
      datasets: [{
        label: 'Stok Miktarı',
        data: [5000, 3000, 4000, 3500, 4500],
        backgroundColor: '#36A2EB'
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          display: false,
        },
        title: {
          display: false,
        }
      },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  // Son İşlemler Tablosunu Dinamik Olarak Güncelleme (Örnek)
  const recentActivities = [
    { id: 4, type: 'Depo Güncelleme', item: 'Yeni Depo - İzmir', quantity: '-', user: 'Admin', date: '2025-01-02 14:20' },
    { id: 5, type: 'Stok Ekleme', item: 'Laptop', quantity: '30', user: 'Admin', date: '2025-01-02 13:15' },
    { id: 6, type: 'Stok Çıkışı', item: 'Tablet', quantity: '15', user: 'Admin', date: '2025-01-02 12:50' },
    // Daha Fazla İşlem ekleyebilirsiniz
  ];

  const recentActivitiesBody = document.getElementById('recentActivitiesBody');
  recentActivities.forEach(activity => {
    const row = recentActivitiesBody.insertRow();
    row.insertCell(0).innerText = activity.id;
    row.insertCell(1).innerText = activity.type;
    row.insertCell(2).innerText = activity.item;
    row.insertCell(3).innerText = activity.quantity;
    row.insertCell(4).innerText = activity.user;
    row.insertCell(5).innerText = activity.date;
  });

  // Depo Konumları Haritası
  am4core.useTheme(am4themes_animated);
  
  var map = am4core.create("warehouseMap", am4maps.MapChart);
  map.geodata = am4geodata_worldLow;
  map.projection = new am4maps.projections.Miller();
  
  map.homeGeoPoint = {
    latitude: 40,
    longitude: 35
  };
  map.homeZoomLevel = 3;

  // Depo Verileri
  var warehouseData = [
    { id: "TR", name: "Merkez Depo", latitude: 41.0082, longitude: 28.9784 },
    { id: "TR", name: "Doğu Depo", latitude: 39.9334, longitude: 32.8597 },
    { id: "TR", name: "Batı Depo", latitude: 38.4237, longitude: 27.1428 },
    { id: "TR", name: "Kuzey Depo", latitude: 41.1793, longitude: 29.0665 },
    { id: "TR", name: "Güney Depo", latitude: 36.8969, longitude: 30.7075 }
  ];

  var imageSeries = map.series.push(new am4maps.MapImageSeries());
  imageSeries.data = warehouseData;
  imageSeries.tooltip.label.interactionsEnabled = true;

  var imageTemplate = imageSeries.mapImages.template;
  imageTemplate.nonScaling = true;
  imageTemplate.tooltipText = "{name}";
  imageTemplate.propertyFields.latitude = "latitude";
  imageTemplate.propertyFields.longitude = "longitude";

  var circle = imageTemplate.createChild(am4core.Circle);
  circle.radius = 8;
  circle.fill = am4core.color("#ff5200");
  circle.stroke = am4core.color("#fff");
  circle.strokeWidth = 2;

  // Tooltip için Event Listener
  imageSeries.events.on("hit", function(ev) {
    alert(`Depo Adı: ${ev.target.dataItem.dataContext.name}`);
  });

  // Harita Kontrolleri
  map.zoomControl = new am4maps.ZoomControl();
});
