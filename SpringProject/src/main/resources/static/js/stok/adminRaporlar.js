// adminRaporlar.js
document.addEventListener("DOMContentLoaded", function() {
  // Özet Kartlar Verilerini Güncelleme (Bu kısım dinamik verilerle değiştirilebilir)
  document.getElementById('totalSales').innerText = '₺150,000';
  document.getElementById('totalVisitors').innerText = '65,200';
  document.getElementById('newCustomers').innerText = '450';
  document.getElementById('stockAlert').innerText = '10 Ürün';

  // Aylık Satış Grafiği (Line Chart)
  var ctx = document.getElementById('monthlySalesChart').getContext('2d');
  var monthlySalesChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran', 'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık'],
      datasets: [{
        label: 'Satış Miktarı (₺)',
        data: [12000, 15000, 13000, 17000, 16000, 19000, 22000, 20000, 18000, 21000, 24000, 23000],
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

  // Kategori Satış Oranları (Pie Chart)
  var ctxPie = document.getElementById('categorySalesChart').getContext('2d');
  var categorySalesChart = new Chart(ctxPie, {
    type: 'pie',
    data: {
      labels: ['Elektronik', 'Giyim', 'Kitap & Kırtasiye', 'Ev & Yaşam', 'Spor'],
      datasets: [{
        label: 'Kategori Satış Oranları',
        data: [50000, 30000, 15000, 20000, 10000],
        backgroundColor: [
          '#FF6384',
          '#36A2EB',
          '#FFCE56',
          '#4BC0C0',
          '#9966FF'
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

  // Haftalık Trafik (Bar Chart)
  var ctxBar = document.getElementById('weeklyTrafficChart').getContext('2d');
  var weeklyTrafficChart = new Chart(ctxBar, {
    type: 'bar',
    data: {
      labels: ['Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma', 'Cumartesi', 'Pazar'],
      datasets: [{
        label: 'Ziyaretçi Sayısı',
        data: [2000, 2500, 2200, 3000, 2800, 3500, 4000],
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

  // Günlük Ziyaretçiler (Line Chart)
  var ctxDaily = document.getElementById('dailyVisitorsChart').getContext('2d');
  var dailyVisitorsChart = new Chart(ctxDaily, {
    type: 'line',
    data: {
      labels: ['Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma', 'Cumartesi', 'Pazar'],
      datasets: [{
        label: 'Ziyaretçi Sayısı',
        data: [800, 1200, 900, 1500, 1300, 1800, 2000],
        backgroundColor: 'rgba(153, 102, 255, 0.2)',
        borderColor: '#9966FF',
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
          beginAtZero: true
        }
      }
    }
  });

  // Depo Doluluk Dağılımı (Doughnut Chart)
  var ctxDoughnut = document.getElementById('warehousePolarChart').getContext('2d');
  var warehousePolarChart = new Chart(ctxDoughnut, {
    type: 'doughnut',
    data: {
      labels: ['Elektronik', 'Giyim', 'Kitap', 'Ev & Yaşam', 'Spor'],
      datasets: [{
        label: 'Depo Doluluk Oranı (%)',
        data: [70, 60, 50, 80, 40],
        backgroundColor: [
          '#FF6384',
          '#36A2EB',
          '#FFCE56',
          '#4BC0C0',
          '#9966FF'
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

  // amCharts Harita
  am4core.useTheme(am4themes_animated);
  
  var chart = am4core.create("countriesSalesMap", am4maps.MapChart);
  chart.geodata = am4geodata_worldLow;
  chart.projection = new am4maps.projections.Miller();
  
  chart.homeGeoPoint = {
    latitude: 50,
    longitude: 20
  };
  chart.homeZoomLevel = 2.5;

  var polygonSeries = chart.series.push(new am4maps.MapPolygonSeries());
  polygonSeries.useGeodata = true;
  polygonSeries.exclude = ["GL", "AQ"];

  polygonSeries.data = [
    { id: "US", value: 15000 },
    { id: "TR", value: 8000 },
    { id: "RU", value: 9000 },
    { id: "DE", value: 5000 },
    { id: "GB", value: 7000 },
    { id: "FR", value: 6500 },
    { id: "CN", value: 18000 },
    { id: "JP", value: 12000 },
    { id: "IT", value: 4000 },
    { id: "ES", value: 3500 },
    { id: "CA", value: 6000 },
    { id: "BR", value: 2200 },
    { id: "AR", value: 1900 },
    { id: "MX", value: 2700 },
    { id: "ZA", value: 2100 },
    { id: "EG", value: 2800 },
    { id: "IN", value: 11000 }
  ];

  polygonSeries.heatRules.push({
    property: "fill",
    target: polygonSeries.mapPolygons.template,
    min: am4core.color("#ffe0e6"),
    max: am4core.color("#c2185b")
  });

  var polygonTemplate = polygonSeries.mapPolygons.template;
  polygonTemplate.tooltipText = "{name}: {value}";
  polygonTemplate.strokeWidth = 0.5;
  polygonTemplate.nonScalingStroke = true;
  
  var hs = polygonTemplate.states.create("hover");
  hs.properties.fill = am4core.color("#367B25");

  chart.zoomControl = new am4maps.ZoomControl();
});
