package com.kubraevren.stok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubraevren.eticaret.model.urunBilgisi;
import com.kubraevren.stok.model.admin;
import com.kubraevren.stok.repository.IStokRepository;
import com.kubraevren.stok.repository.stokKalemiRepository;

@Service
public class stokService {

    @Autowired
    private IStokRepository stokRepository;

    @Autowired
    private stokKalemiRepository stokKalemiRepository;

    public admin kayitEt(admin admin) {
       return stokRepository.save(admin);
    }

   
    public boolean kullaniciDogrula(String email, String password) {
        admin admin = stokRepository.findByEmail(email);
        if (admin == null) {
            return false; 
        }
        return password.equals(admin.getParola());
    }


    public void urunKaydet(urunBilgisi urun) {
       
        stokKalemiRepository.save(urun);
    }


    public List<urunBilgisi> findAll() {
        return stokKalemiRepository.findAll();
    }


    public Object findById(Long id) {
       return stokKalemiRepository.findById(id);
       
    }


    public void save(urunBilgisi existingProduct) {
        stokKalemiRepository.save(existingProduct);
    }


    public void deleteById(Long id) {
        stokKalemiRepository.deleteById(id);   
    }




 /*   public void stokEkle(Long urunId, int miktar) {
        stokUrunler stok = stokRepository.findByUrunId(urunId);
        if (stok != null) {
            stok.setMevcutStokMiktari(stok.getMevcutStokMiktari() + miktar);
        } else {
            stok = new stokUrunler();
            stok.setUrunId(urunId);
            stok.setMevcutStokMiktari(miktar);
        }
        stokRepository.save(stok);
    }

    public boolean stokAzalt(Long urunId, int miktar) {
        stokUrunler stok = stokRepository.findByUrunId(urunId);
        if (stok != null && stok.getMevcutStokMiktari() >= miktar) {
            stok.setMevcutStokMiktari(stok.getMevcutStokMiktari() - miktar);
            stokRepository.save(stok);
            return true;
        } else {
            return false; // Yeterli stok yok
        }
    }

	public List<stokUrunler> getAllStoklar() {
		  return stokRepository.findAll();
	}

	public boolean stokSil(Long urunId) {
		 Optional<stokUrunler> stok = stokRepository.findById(urunId);
		    if (stok.isPresent()) {
		        stokRepository.delete(stok.get());
		        return true;
		    }
		    return false;  // Ürün bulunamazsa false döner
	}*/ 
}
