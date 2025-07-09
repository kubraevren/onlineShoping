package com.kubraevren.stok.model;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class admin {
   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;


	@Column(name = "email" , unique = true)
	private String email;

	

	@Column(name = "kullanici_adi",unique = true)
	private String kullaniciAdi;
	
	@Column(name = "parola")
	private String parola;

	

	
}
