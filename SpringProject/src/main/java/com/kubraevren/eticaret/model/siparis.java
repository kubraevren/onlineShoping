package com.kubraevren.eticaret.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class siparis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siparisId;
    private Date siparisTarihi;
    private String durum;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private musteri musteri;

    @ManyToMany
    @JoinTable(name = "SiparisUrun",
        joinColumns = @JoinColumn(name = "siparis_id"),
        inverseJoinColumns = @JoinColumn(name = "urun_id"))
    private List<urunBilgisi> urunler;
}


