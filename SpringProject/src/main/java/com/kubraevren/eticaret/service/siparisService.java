package com.kubraevren.eticaret.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubraevren.eticaret.model.siparis;
import com.kubraevren.eticaret.repository.impl.IsiparisRepository;

    @Service
    public class siparisService {
        @Autowired
        private IsiparisRepository siparisRepository;
    
        public siparis siparisEkle(siparis siparis) {
            return siparisRepository.save(siparis);
        }
    
        public List<siparis> tumSiparisleriGetir() {
            return siparisRepository.findAll();
        }

      
    }
    

   

