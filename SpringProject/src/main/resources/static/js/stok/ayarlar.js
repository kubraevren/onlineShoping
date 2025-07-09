// ayarlar.js
document.addEventListener("DOMContentLoaded", function() {
    // Genel Ayarlar Formu
    const generalSettingsForm = document.getElementById("generalSettingsForm");
    const siteNameInput = document.getElementById("siteName");
    const siteLogoInput = document.getElementById("siteLogo");
    const adminEmailInput = document.getElementById("adminEmail");
  
    generalSettingsForm.addEventListener("submit", function(event) {
      event.preventDefault();
  
      const siteName = siteNameInput.value.trim();
      const adminEmail = adminEmailInput.value.trim();
      const siteLogo = siteLogoInput.files[0];
  
      if (siteName === "" || adminEmail === "") {
        alert("Lütfen gerekli alanları doldurun.");
        return;
      }
  
      // Logo Yükleme ve Diğer Ayarlar Backend Entegrasyonu ile Yapılabilir
      // Örnek: AJAX ile veri gönderme
  
      alert("Genel ayarlar başarıyla güncellendi.");
      generalSettingsForm.reset();
    });
  
    // Güvenlik Ayarları Formu
    const securitySettingsForm = document.getElementById("securitySettingsForm");
    const passwordPolicySelect = document.getElementById("passwordPolicy");
    const twoFactorAuthSelect = document.getElementById("twoFactorAuth");
  
    securitySettingsForm.addEventListener("submit", function(event) {
      event.preventDefault();
  
      const passwordPolicy = passwordPolicySelect.value;
      const twoFactorAuth = twoFactorAuthSelect.value;
  
      if (passwordPolicy === "" || twoFactorAuth === "") {
        alert("Lütfen gerekli alanları doldurun.");
        return;
      }
  
      // Güvenlik Ayarları Backend Entegrasyonu ile Yapılabilir
      // Örnek: AJAX ile veri gönderme
  
      alert("Güvenlik ayarları başarıyla güncellendi.");
      securitySettingsForm.reset();
    });
  
    // Bildirim Ayarları Formu
    const notificationSettingsForm = document.getElementById("notificationSettingsForm");
    const emailNotificationsSelect = document.getElementById("emailNotifications");
    const smsNotificationsSelect = document.getElementById("smsNotifications");
  
    notificationSettingsForm.addEventListener("submit", function(event) {
      event.preventDefault();
  
      const emailNotifications = emailNotificationsSelect.value;
      const smsNotifications = smsNotificationsSelect.value;
  
      if (emailNotifications === "" || smsNotifications === "") {
        alert("Lütfen gerekli alanları doldurun.");
        return;
      }
  
      // Bildirim Ayarları Backend Entegrasyonu ile Yapılabilir
      // Örnek: AJAX ile veri gönderme
  
      alert("Bildirim ayarları başarıyla güncellendi.");
      notificationSettingsForm.reset();
    });
  });
  