package com.kubraevren.eticaret.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kubraevren.eticaret.model.musteri;
import com.kubraevren.eticaret.model.siparis;
import com.kubraevren.eticaret.model.urunBilgisi;
import com.kubraevren.eticaret.service.musteriService;
import com.kubraevren.eticaret.service.siparisService;
import com.kubraevren.eticaret.service.urunService;

@Controller
public class siparisController {
//Amacı: HTTP isteklerini alır ve uygun servislere yönlendirir.
@Autowired
urunService urunService;
@Autowired
musteriService musteriService;

 @Autowired
    private siparisService siparisService;

    @Autowired
@Qualifier("simpMessagingTemplate") // Hangi bean'in kullanılacağını belirtiyoruz
private SimpMessagingTemplate messagingTemplate;
@GetMapping("/tumSiparisler")
@ResponseBody
public List<siparis> tumSiparisleriGetir() {
    return siparisService.tumSiparisleriGetir();
}


@PostMapping("/siparis")
@ResponseBody
public siparis yeniSiparis(@RequestBody Map<String, Object> orderData) {
    Long urunId = Long.valueOf(orderData.get("urunId").toString());
    Long kullaniciId = Long.valueOf(orderData.get("kullaniciId").toString());
    Integer miktar = Integer.valueOf(orderData.get("miktar").toString());

    // Ürün ve müşteri (kullanıcı) nesnelerini çekin
    urunBilgisi urun = urunService.findById(urunId);
    musteri musteri = musteriService.findById(kullaniciId);

    siparis yeniSiparis = new siparis();
    yeniSiparis.setSiparisTarihi(new Date(System.currentTimeMillis()));  // Tarih için anlık zamanı kullanın.
    yeniSiparis.setDurum("Beklemede");
    yeniSiparis.setMusteri(musteri);
    yeniSiparis.setUrunler(Arrays.asList(urun)); 
    // Eğer miktar destekliyorsa sipariş kalemlerini buna göre oluşturun

    siparis kaydedilenSiparis = siparisService.siparisEkle(yeniSiparis);

    // Admin dashboard güncellemesi için WebSocket mesajı gönderme kodu mevcutsa burada çalışır.
    messagingTemplate.convertAndSend("/topic/orders", kaydedilenSiparis);

    return kaydedilenSiparis;
}



public void sendMessage(String destination, Object message) {
    messagingTemplate.convertAndSend(destination, message);
}

}
//İçeriği: @RestController veya @Controller anotasyonları ile işaretlenir. İstekleri işlemek için @GetMapping, @PostMapping gibi anotasyonlar kullanılır.