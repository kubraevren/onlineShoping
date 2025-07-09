document.getElementById('kayitOlButonu').addEventListener('click', function (event) {
    event.preventDefault(); // Formun varsayılan davranışını engelle

    const formData = new FormData(document.getElementById('kayitFormu'));
    const data = {};
    formData.forEach((value, key) => { 
        if (value.trim() === '') {
            alert(key + ' alanı boş olamaz!');
            return;
        }
        data[key] = value.trim(); 
    });

    fetch('/kayitol', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.status === 200 || response.status === 201) { // 200 veya 201 ise başarılı
            return response.text();
        } else { 
            throw new Error('Beklenmeyen durum kodu: ' + response.status);
        }
    })
    .then(data => {
        alert(data); // Kayıt başarılı mesajını göster
        window.location.href = "/girisYap"; // Giriş sayfasına yönlendir
    })
    .catch(error => {
        alert('Hata: ' + error.message); // Hata mesajını göster
    });
});
