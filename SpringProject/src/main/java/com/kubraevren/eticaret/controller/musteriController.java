package com.kubraevren.eticaret.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kubraevren.eticaret.model.musteri;
import com.kubraevren.eticaret.model.urunBilgisi;
import com.kubraevren.eticaret.service.musteriService;
import com.kubraevren.eticaret.service.urunService;
import com.kubraevren.stok.repository.stokKalemiRepository;

import jakarta.servlet.http.HttpSession;

@Controller
// @RequestMapping("/eticaret")
public class musteriController {

    @Autowired
    private urunService urunService;

    @Autowired
    private musteriService musteriService;
    @Autowired
    private stokKalemiRepository stokKalemiRepository;

    // http://localhost:8080/eticaret/home

    /*
     * @GetMapping("/anasayfa")
     * public String anaSayfa(HttpSession session, Model model) {
     * musteri user = (musteri) session.getAttribute("loggedInUser");
     * model.addAttribute("user", user); // thymeleaf'te user üzerinden erişeceğiz
     * 
     * return "eticaret/anasayfa";
     * 
     * }
     */

    @GetMapping("/anasayfa")
    public String anaSayfa(HttpSession session, Model model) {
        // Oturumdaki kullanıcıyı al
        musteri user = (musteri) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);

        // Ürünleri çek ve modele ekle
        List<urunBilgisi> urunler = stokKalemiRepository.findAll(); // stokService ile ürünleri çek
        model.addAttribute("urunler", urunler); // Ürünleri modele ekle

        return "eticaret/anasayfa"; // Thymeleaf'te "anasayfa.html" dosyasını döndür
    }

    @GetMapping("/girisYap")
    public String girisYap() {
        return "eticaret/girisYap";
    }

    @GetMapping("/kayitol")
    public String kayitol() {
        return "eticaret/kayitol";
    }
    // Uygun bir controller (örneğin, UrunController) içerisine ekleyin

    @GetMapping("/urunDetay/{urunId}")
    public String urunDetay(@PathVariable Long urunId, Model model, HttpSession session) {
        urunBilgisi urun = urunService.findById(urunId);
        if(urun == null) {
            return "404"; // Ürün bulunamadıysa uygun bir hata sayfası
        }
        model.addAttribute("urun", urun);
// Oturumdaki kullanıcıyı alıp model'e ekleyin
musteri user = (musteri) session.getAttribute("loggedInUser");
model.addAttribute("user", user);

        return "eticaret/urunDetay"; 
    }


    





    @GetMapping("/sepetim")
    public String sepetim() {
        return "eticaret/sepetim";
    }

    @GetMapping("/odeme")
    public String odeme() {
        return "eticaret/odeme";
    }

    @GetMapping("/cikisYap")
    public String cikisYap(HttpSession session) {
        session.invalidate(); // Oturumu sona erdirir
        return "redirect:/anasayfa"; // Ziyaretçi olarak ana sayfaya yönlendir
    }

    @PostMapping("/kayitol")
    public ResponseEntity<String> registerUser(@RequestBody musteri musteri) {
        try {
            musteriService.kayitEt(musteri);
            return ResponseEntity.ok("Kayıt başarılı!"); // Bu HTTP 200 döner
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Kayıt sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    @PostMapping("/girisYap")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials, HttpSession session) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");

            // Kullanıcı doğrulama işlemi (örnek kod, kendi login servisinizi bağlayın)
            boolean isAuthenticated = musteriService.kullaniciDogrula(email, password);
            // Şimdi kullanıcı gerçekten var mı diye obje getirelim:
            musteri foundUser = musteriService.getByEmailAndPassword(email, password);
            // (burada "kullaniciDogrula" yerine gerçek bir metodla user objesi
            // getirebilirsin.
            // yoksa isAuthenticated = true/false dönen bir metot varsa orada user'ı dönen
            // bir metod yazman lazım.)
            if (foundUser != null) {
                // Kullanıcı bulundu, session'a ekliyoruz
                session.setAttribute("loggedInUser", foundUser);
                return ResponseEntity.ok("Giriş başarılı!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Hatalı e-posta veya şifre!");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Giriş sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    /*
     * @PostMapping("/girisYap")
     * public ResponseEntity<?> loginUser(@RequestBody Map<String, String>
     * credentials) {
     * String email = credentials.get("email"); // JSON'dan email'i al
     * String parola = credentials.get("parola"); // JSON'dan parola'yı al
     * 
     * if (email == null || parola == null) {
     * return ResponseEntity.badRequest().body("Email veya parola eksik!");
     * }
     * // Kullanıcı doğrulama
     * boolean isValid = musteriService.validateUser(email, parola);
     * if (isValid) {
     * return ResponseEntity.ok("Giriş başarılı");
     * } else {
     * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
     * body("Email veya parola hatalı");
     * }
     * }
     * 
     * 
     * 
     */

}