# IssueMindAI - Mimarî Dokümantasyonu

Bu belge, **IssueMindAI** projesinin mikroservis mimarisini, servisler arası iletişim yöntemlerini, kullanılan teknolojileri, konfigürasyon yapısını ve bileşen etkileşimlerini detaylı şekilde açıklar.

---

## 🔷 1. Genel Mimarî Yapı

IssueMindAI, **mikroservis mimarisi** kullanılarak inşa edilmiştir. Her servis belirli bir görevi yerine getirir, bağımsız olarak geliştirilip dağıtılabilir. Tüm bileşenler loosely coupled (gevşek bağlı) olacak şekilde yapılandırılmıştır.

Ana bileşenler:

- **Kafka** ile mesaj tabanlı asenkron iletişim
- **Spring Boot** ile geliştirilmiş mikroservisler
- **React** ile oluşturulmuş frontend kullanıcı arayüzü
- **Redis** ile hızlı veri erişimi ve geçici veri saklama
- **JasperReports** ile PDF rapor üretimi

---

## 🔷 2. Servisler Arası İletişim

### 📌 Kafka

Kafka, mikroservisler arası olay tabanlı mesajlaşmayı sağlar.

- `issue-events` topic → `issue-producer-service` tarafından üretilir, `nlp-processor-service` ve `dashboard-backend` tarafından dinlenir.
- `nlp-results` topic → NLP çıktıları burada yayınlanır.
- `feedback-events` topic → `feedback-collector-service` üretir, `feedback-analyzer-service` dinler.

### 📌 REST API

- Frontend doğrudan `dashboard-backend`, `jasperreports-service`, `feedback-collector-service` gibi servislere HTTP istekleri gönderir.
- `dashboard-backend` frontend ile Redis arasında köprü görevi görür.

---

## 🔷 3. Kullanılan Teknolojiler

| Katman/Bileşen     | Teknoloji                        |
|--------------------|----------------------------------|
| Frontend           | React                            |
| Backend            | Spring Boot                      |
| Mesajlaşma         | Apache Kafka                     |
| Cache              | Redis                            |
| Raporlama          | JasperReports (.jrxml)           |
| Konfigürasyon      | Spring Boot `application.yml`    |
| Dokümantasyon      | Markdown (.md)                   |

---

## 🔷 4. Servis Açıklamaları

### ✅ issue-producer-service
- HTTP üzerinden alınan `Issue` verisini `issue-events` topic'ine gönderir.

### ✅ nlp-processor-service
- Kafka üzerinden `issue-events` topic'ini dinler.
- NLP işlemlerini uygular ve sonucu `nlp-results` topic'ine yayınlar.

### ✅ insight-generator-service
- `nlp-results` topic'ini dinler.
- İçgörü (insight) üretir, daha sonra kullanılmak üzere Redis’e ya da doğrudan frontend’e servis edilebilir.

### ✅ dashboard-backend
- Redis’ten veri okur, React frontend'e sunar.
- Servisler arası API köprüsü görevindedir.

### ✅ jasperreports-service
- JasperReports kullanarak PDF formatında raporlar üretir.
- React frontend'den gelen taleplere karşılık verir.

### ✅ feedback-collector-service
- Kullanıcıdan gelen geri bildirimleri alır ve `feedback-events` topic’ine gönderir.

### ✅ feedback-analyzer-service
- Kafka üzerinden gelen feedback verilerini alır.
- Redis'e analiz edilmiş halde yazar.

---

## 🔷 5. Dizin Yapısı

```text
IssueMindAI/
├── README.md
├── docker-compose.yml
├── shared/
├── frontend/
│   └── src/
│       ├── components/
│       ├── pages/
│       └── services/
└── services/
    ├── issue-producer-service/
    ├── nlp-processor-service/
    ├── insight-generator-service/
    ├── dashboard-backend/
    ├── jasperreports-service/
    ├── feedback-collector-service/
    └── feedback-analyzer-service/
```

---

## 🔷 6. Bileşen Etkileşim Şeması

```text
            [React Frontend]
                   |
           [dashboard-backend]
                   |
                 Redis
                   ↑
           ----------------------
           |                    |
 [feedback-analyzer]     [insight-generator]
           ↑                    ↑
[feedback-collector]   [nlp-processor-service]
           ↑                    ↑
           |                    |
    Kafka - feedback-events   Kafka - issue-events
                             ↑
                [issue-producer-service]
```

---

## 🔷 7. Gelecekteki Geliştirme Alanları

- Eureka + Spring Cloud Config ile servis keşfi ve merkezi konfigürasyon
- Prometheus + Grafana ile merkezi monitoring/loglama
- OAuth2 / Keycloak entegrasyonu ile güvenlik

---
