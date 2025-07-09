package com.kubraevren.eticaret.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class musteri {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long musteriId;


	@Column(name = "email")
	private String email;

	

	@Column(name = "kullanici_adi")
	private String kullaniciAdi;
	
	@Column(name = "parola")
	private String parola;


	@OneToMany(mappedBy = "musteri", cascade = CascadeType.ALL)
	private List<siparis> siparisler;
}
