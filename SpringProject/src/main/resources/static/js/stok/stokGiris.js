document.getElementById('stokgirisButonu').addEventListener('click', function (event) {
    event.preventDefault(); // Formun varsayılan davranışını engelle

    const email = document.getElementById("email").value.trim();
    const kullaniciAdi = document.getElementById("kullaniciAdi").value.trim();
    const parola = document.getElementById("parola").value.trim();

    if (!email || !kullaniciAdi || !parola) {
        alert("Lütfen tüm alanları doldurun!");
        return;
    }

    const data = { email, kullaniciAdi, parola };

    fetch("/stokgiris", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.status === 200) {
            return response.text(); // Başarılı yanıt mesajını döner
        } else if (response.status === 401) {
            throw new Error("Hatalı e-posta, kullanıcı adı veya şifre!");
        } else {
            throw new Error("Beklenmeyen durum kodu: " + response.status);
        }
    })
    .then(data => {
        window.location.href = "/deneme"; // Admin paneline yönlendir
    })
    .catch(error => {
        alert("Hata: " + error.message); // Hata mesajını göster
    });
});
