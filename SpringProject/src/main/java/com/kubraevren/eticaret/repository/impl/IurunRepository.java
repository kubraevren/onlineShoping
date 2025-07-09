package com.kubraevren.eticaret.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kubraevren.eticaret.model.urunBilgisi;

@Repository
public interface IurunRepository extends JpaRepository<urunBilgisi, Long>{

}
