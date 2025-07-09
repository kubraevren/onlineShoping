package com.kubraevren.stok.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kubraevren.eticaret.model.urunBilgisi;
@Repository
public interface stokKalemiRepository  extends JpaRepository <urunBilgisi, Long>{

  


    
}
