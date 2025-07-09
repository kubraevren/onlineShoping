package com.kubraevren.stok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kubraevren.stok.model.admin;

@Repository
public interface IStokRepository extends JpaRepository<admin, Long> {
 

     admin findByEmail(String email);

     admin findByParola(String parola);
}








/*stokUrunler findByUrunId(Long urunId);

public stokUrunler save(stokUrunler stok);
*/