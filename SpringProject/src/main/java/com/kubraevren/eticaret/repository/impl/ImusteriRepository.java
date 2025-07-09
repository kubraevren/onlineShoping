package com.kubraevren.eticaret.repository.impl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kubraevren.eticaret.model.musteri;
@Repository
public interface ImusteriRepository extends JpaRepository<musteri, Long> {

    musteri findByEmail(String email);

    musteri findByEmailAndParola(String email, String password);
	








}
/* Ek özel sorgular tanımlayabilirsiniz, fakat save() otomatik sağlanır.
	
	musteri findByEmailAndParola(String email, String password);*/
/*
 * JpaRepository'i genişlettiğinizde, aşağıdaki yöntemlere erişim kazanırsınız:
 * 
 * save(): Yeni bir kayıt oluşturmak veya mevcut bir kaydı güncellemek için.
 * findById(): Bir kaydı birincil anahtarına göre bulmak için. 
 * findAll(): Tüm kayıtları almak için. 
 * deleteById(): Belirtilen birincil anahtar değerine sahip kaydı silmek için.
 *  Bu sayede veritabanı işlemlerini kolayca
 * gerçekleştirebilir ve gerektiğinde özel sorgular tanımlayabilirsiniz.
 */
