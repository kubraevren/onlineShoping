package com.kubraevren.eticaret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kubraevren.eticaret.model.urunBilgisi;
import com.kubraevren.eticaret.repository.impl.IurunRepository;
import java.util.Optional;

@Service
public class urunService  {

    @Autowired
    private IurunRepository urunRepository;

    public void urunEkle(urunBilgisi urun) {
        // Uygun kodu buraya ekleyin (örneğin: urunRepository.save(urun);)
    }

    public boolean urunSil(Long urunId) {
        // Uygun silme işlemi
        return false;
    }

   // public urunBilgisi findById(Long id) {
        // Repository'nin findById metodu Optional döner, bunu çözüyoruz:
       // Optional<urunBilgisi> optionalUrun = urunRepository.findById(id);
      //  return optionalUrun.orElse(null); 
        // Ya da bulunamadığında özel bir durum atabilirsiniz.
  //  }


  public urunBilgisi findById(Long id) {
      return urunRepository.findById(id).orElse(null);
  }
}
//HATA VERİRSE
//public urunBilgisi findById(Long id) {
//    return urunRepository.findById(id).orElse(null);
//}