document.getElementById('stokkayitButonu').addEventListener('click', function (event) {
    event.preventDefault(); // Formun varsayılan davranışını engelle

    const form = document.getElementById('stokkayitFormu');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        if (value.trim() === '') {
            alert(key + ' alanı boş olamaz!');
            return;
        }
        data[key] = value.trim();
    });

    fetch('/stokkayit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    })
    .then((response) => {
        if (response.ok) { // 200 veya 201 durum kodları için
            return response.text();
        } else {
            throw new Error('Hata: ' + response.status);
        }
    })
    .then((message) => {
        alert(message); // Kayıt başarılı mesajını göster
        window.location.href = '/stokgiris'; // Giriş sayfasına yönlendir
    })
    .catch((error) => {
        alert('Hata: ' + error.message); // Hata mesajını göster
    });
});
