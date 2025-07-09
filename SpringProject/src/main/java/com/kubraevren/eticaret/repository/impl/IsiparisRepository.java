package com.kubraevren.eticaret.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kubraevren.eticaret.model.siparis;

@Repository
public interface IsiparisRepository extends JpaRepository<siparis, Long> {
    // Gerekirse özel sorgular ekleyebilirsiniz.
    
    
}






