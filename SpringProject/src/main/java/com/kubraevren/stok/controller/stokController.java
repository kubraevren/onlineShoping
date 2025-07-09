package com.kubraevren.stok.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kubraevren.eticaret.model.urunBilgisi;
import com.kubraevren.stok.model.admin;
import com.kubraevren.stok.repository.stokKalemiRepository;
import com.kubraevren.stok.service.stokService;

@Controller
public class stokController {

    @Autowired 
    stokService stokService;
    @Autowired
    private stokKalemiRepository stokKalemiRepository;

    
    @GetMapping("/stokgiris")
    public String stokgiris() {
        return "stok/stokgiris"; 
    }
    @GetMapping("/stokkayit")
    public String stokkayit() {
        return "stok/stokkayit"; 
    }

    
    @GetMapping("/adminRaporlar")
    public String adminRaporlar() {
        return "stok/adminRaporlar"; 
    }
 





    @GetMapping("/dashboard")
    public String dashboard() {
        return "stok/dashboard"; 
    }

    @GetMapping("/depoBilgileri")
    public String depoBilgisi() {
        return "stok/depoBilgileri"; 
    }

    @GetMapping("/ayarlar")
    public String ayarlar() {
        return "stok/ayarlar"; 
    }

    @GetMapping("/admin")
    public String urunEkle(Model model) {
        model.addAttribute("urunBilgisi", new urunBilgisi()); // Doğru kullanım
        return "stok/urunEkle"; 
    }

   @GetMapping("/urunler")
@ResponseBody
public List<urunBilgisi> getAllUrunler() {
    return stokKalemiRepository.findAll();
}


    @GetMapping("/urunEkle")
    public String urunEkle() {
        return "stok/urunEkle"; 
    }

@PostMapping("/urunEkle")
public String urunEkle(@ModelAttribute urunBilgisi yeniUrun,
                       @RequestParam("image") MultipartFile image,
                       Model model) {
    try {
        // Dış klasör yolu: proje kökü /uploads
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        System.out.println("Resim kaydetme dizini: " + uploadDir);

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            boolean created = uploadDirFile.mkdirs();
            System.out.println("Dizin oluşturuldu: " + created);
        }

        if (!image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            System.out.println("Yüklenecek dosya: " + originalFilename);

            // Dosyayı /uploads dizinine kaydet
            String filePath = uploadDir + originalFilename;
            File dest = new File(filePath);
            image.transferTo(dest);
            System.out.println("Dosya başarıyla kaydedildi: " + filePath);

            // Ürün nesnesine görüntü URL'sini ata
            yeniUrun.setImageUrl("/uploads/" + originalFilename);
        } else {
            System.out.println("Yüklenen resim dosyası boş.");
        }

        stokKalemiRepository.save(yeniUrun);
        System.out.println("Ürün başarıyla veritabanına kaydedildi.");
        return "redirect:/anasayfa";
    } catch(Exception e) {
        e.printStackTrace();
        model.addAttribute("message", "Ürün eklenirken hata: " + e.getMessage());
        return "stok/urunEkle";
    }
}








    @PostMapping("/stokkayit")
public ResponseEntity<String> registerUser(@RequestBody admin admin) {
    try {
        stokService.kayitEt(admin);
        return ResponseEntity.ok("Kayıt başarılı!"); // Bu HTTP 200 döner
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Kayıt sırasında bir hata oluştu: " + e.getMessage());
    }
}

@PostMapping("/stokgiris")
public ResponseEntity<String> loginUserr(@RequestBody Map<String, String> credentials) {
    try {
        
        String email = credentials.get("email");
        String password = credentials.get("parola");
        System.out.println("Email: " + email + ", Parola: " + password);

        boolean isAuthenticated = stokService.kullaniciDogrula(email, password);

        if (isAuthenticated) {
           
            return ResponseEntity.ok("Giriş başarılı!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı e-posta veya şifre!");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Giriş sırasında bir hata oluştu: " + e.getMessage());
    }
}




@PutMapping("/urunGuncelle/{id}")
public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody urunBilgisi updatedProduct) {
    urunBilgisi existingProduct = stokKalemiRepository.findById(id).orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));
    existingProduct.setUrunAdi(updatedProduct.getUrunAdi());
    existingProduct.setFiyat(updatedProduct.getFiyat());
    existingProduct.setStokMiktari(updatedProduct.getStokMiktari());
    // Diğer alanları güncelle...
    stokService.save(existingProduct);
    return ResponseEntity.ok("Ürün başarıyla güncellendi!");
}


@DeleteMapping("/urunSil/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    stokService.deleteById(id);
    return ResponseEntity.ok("Ürün başarıyla silindi!");
}







/*

@PostMapping("/urunEklee")
public String urunEklee(@ModelAttribute urunBilgisi urun, Model model) {
    stokKalemiRepository.save(urun);
    return "redirect:/anasayfa";
}




@GetMapping("/anasayfaUrunler")
public String getHomepageProducts(Model model) {
    List<urunBilgisi> urunler = stokKalemiRepository.findAll(); // stokService ile ürünleri çek
    model.addAttribute("urunler", urunler); // ürünleri model ile frontend'e gönder
    return "anasayfa"; // "anasayfa.html" dosyasını döndür
}  

















@PostMapping("/urunler")
public String addUrun(@ModelAttribute urunBilgisi urunBilgisi,
                          @RequestParam("image") MultipartFile image,
                          Model model) {
        try {
            urunBilgisiService.saveUrun(urunBilgisi, image);
            model.addAttribute("message", "Ürün başarıyla eklendi.");
            return "urunler"; // Başarılı eklemeden sonra aynı sayfaya dönülebilir
        } catch (Exception e) {
            model.addAttribute("message", "Ürün eklenirken hata oluştu: " + e.getMessage());
            return "urunler";
        }
    }


*/

   
}
