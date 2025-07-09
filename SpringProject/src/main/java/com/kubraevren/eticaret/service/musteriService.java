package com.kubraevren.eticaret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubraevren.eticaret.model.musteri;
import com.kubraevren.eticaret.repository.impl.ImusteriRepository;

@Service
public class musteriService {

	@Autowired
	private ImusteriRepository musteriRepository;

    public musteri kayitEt(musteri musteri) {
       return musteriRepository.save(musteri);
    }

	public boolean kullaniciDogrula(String email, String password) {
		musteri musteri = musteriRepository.findByEmail(email);

			if (musteri == null) {
			return false;
		}
	 return musteri.getParola().equals(password);
	}

    public musteri getByEmailAndPassword(String email, String password) {
      return musteriRepository.findByEmailAndParola(email, password);
	}

    public musteri findById(Long id) {
		return musteriRepository.findById(id).orElse(null);	
    }
	//DEĞİŞİKLİK YAPILDI
	
	/*  @Transactional
	public Optional<musteri> getMusteriByKullaniciAdi(String kullaniciAdi) {
	    return musteriRepository.findByKullaniciAdi(kullaniciAdi);
	}

    public boolean validateUser(String email, String parola) {
		musteri musteri = musteriRepository.findByEmailAndParola(email, parola);
		return musteri != null;
    }

*/ 
	
}
