# IssueMindAI - Teknik Değerlendirme Notları

Bu belge, IssueMindAI projesi geliştirilirken kazanılan teknik bilgi ve deneyimleri özetlemektedir.

---

## 1. Mikroservis Mimarisi Prensipleri

- **Servis Ayrıştırması**: Sistem, farklı işlevleri yürüten mikroservislere bölünerek geliştirildi.
- **Tek Sorumluluk İlkesi**: Her servis yalnızca bir görevi yerine getiriyor.
- **Bağımsız Geliştirme ve Dağıtım**: Servisler birbirinden bağımsız olarak geliştirilebilmekte.

---

## 2. Servisler Arası İletişim Yöntemleri

### REST API
- `dashboard-backend`, `jasperreports-service` ve `feedback-collector-service` gibi servisler React frontend'e REST API sağlar.

### Apache Kafka
- Servisler arasında asenkron iletişim için Kafka kullanıldı.
- Event-driven mimari sayesinde servisler gevşek bağlı olarak çalışıyor.

---

## 3. Dağıtık Sistem Tasarımı

- **Redis**: Gerçek zamanlı veri saklama ve hızlı erişim için kullanıldı.
- **Kafka**: Yüksek hacimli mesaj trafiği yönetimi sağlandı.
- **JasperReports**: Dinamik PDF rapor üretimi entegre edildi.
- Sistem, ölçeklenebilir ve yönetilebilir bir yapıya sahip olacak şekilde dağıtık tasarlandı.

---

## 4. Veri ve Olay Akışı

Örnek süreç:
1. Kullanıcı issue oluşturur.
2. `issue-producer-service` Kafka’ya mesaj gönderir.
3. `nlp-processor-service` NLP işlemlerini yapar.
4. `insight-generator-service` veri analizini tamamlar.
5. `dashboard-backend` ile kullanıcıya çıktı gösterilir.

Bu süreç, event sourcing ve streaming temelli sistemleri kavrama açısından önemlidir.

---

## 5. Bileşen Entegrasyonları

- Kafka ile servisler arası loosely coupled yapı kuruldu.
- Redis ile performans artırımı sağlandı.
- REST ve mesajlaşma protokolleri bir arada kullanılarak hibrit iletişim stratejisi oluşturuldu.

---

## 6. Kullanılan Teknolojiler ve Öğrenilenler

| Katman           | Teknoloji         | Kazanım |
|------------------|------------------|---------|
| Frontend         | React            | Modern UI geliştirme, bileşen mimarisi |
| Backend          | Spring Boot      | REST API, bağımsız servis geliştirme |
| Mesajlaşma       | Apache Kafka     | Event-driven architecture |
| Cache            | Redis            | Performans iyileştirme |
| Raporlama        | JasperReports    | Dinamik PDF rapor üretimi |

---

## 7. Yazılım Mimarisini Parçalama Becerisi

- Gerçek bir uygulama nasıl alt parçalara ayrılır?
- Her parça nasıl bağımsız çalışabilir?
- Sistemler arası sorumluluklar nasıl net tanımlanır?

Bu yapı sayesinde büyük sistemlerin yönetilebilir hale gelmesi sağlanır.

---

## 8. Sonuç

Bu proje sayesinde aşağıdaki konularda pratik bilgi edinildi:

- Mikroservis mimarisi ve uygulaması
- Asenkron sistemlerin tasarımı
- Kafka, Redis gibi altyapı bileşenlerinin kullanımı
- Bileşenler arası entegrasyon ve veri akışı
- UML ve PlantUML ile mimari görselleştirme
