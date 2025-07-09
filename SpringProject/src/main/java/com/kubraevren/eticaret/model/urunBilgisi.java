package com.kubraevren.eticaret.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class urunBilgisi {
//Her sınıf için @Entity anotasyonları ve alanlar (örneğin id, name, price gibi).


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long urunId;

	private String urunAdi;
	private String aciklama;
	private BigDecimal fiyat;
	private int stokMiktari;
	private String kategori;
	private String marka;
	private String birim; // Örneğin: adet, kg, paket
	private Double indirimOrani;
	private Date olusturulmaTarihi;
	private Date guncellenmeTarihi;
	private boolean durum; // True: Aktif, False: Pasif
    private String minStokUyari;
	private String imageUrl;
	@Column(name = "mevcut_stok_miktari")
    private int mevcutStokMiktari;

	@ManyToMany(mappedBy = "urunler")
	private List<siparis> siparisler;
	
	 @ManyToOne
	    private satici satici; // Ürünü ekleyen satıcı

	public void addAttribute(String string, urunBilgisi urunBilgisi) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addAttribute'");
	}

    public static Object findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }


}// Veritabanında saklanacak veri yapılarını tanımlar.
