document.getElementById("girisYapButonu").addEventListener("click", function (e) {
    e.preventDefault(); // Sayfanın yeniden yüklenmesini engelle

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!email || !password) {
        alert("Lütfen tüm alanları doldurun!");
        return;
    }

    const data = { email, password };

    fetch("/girisYap", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.status === 200) {
            return response.text(); // Başarılı yanıt mesajını döner
        } else if (response.status === 401) {
            throw new Error("Hatalı e-posta veya şifre!");
        } else {
            throw new Error("Beklenmeyen durum kodu: " + response.status);
        }
    })
    .then(data => {
        window.location.href = "/anasayfa"; // Ana sayfaya yönlendir
    })
    .catch(error => {
        alert("Hata: " + error.message); // Hata mesajını göster
    });
});
