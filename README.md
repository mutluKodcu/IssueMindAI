# IssueMindAI

> NLP tabanlı, Kafka destekli, modül bazlı iş analizi ve karar destek platformu (NLP + Kafka + React + Spring Boot)

## Amaç
- Haftalık gelen iş kayıtlarını (issue/task) modüller bazında:
- NLP ile analiz etmek (CRUD, bug, refactor sınıflandırması),
- Skor hesaplamak (yoğunluk, tekrar, karmaşıklık),
- Teknik öneriler üretmek (refactor önerisi, test açığı, servisleşme ihtiyacı),
- ReactJS dashboard’ta grafiklerle göstermek (modül yükleri, dağılım, zaman serileri),
- Raporları JasperReports ile sunmak,
- Kafka üzerinden mikroservis mimarisi ile haberleşmek.

## Teknolojiler
- Spring Boot (Java 17)
- Apache Kafka
- Docker + Docker Compose
- Redis
- JasperReports
- ReactJS + Recharts
- NLP (Java veya Python tabanlı)

| Katman     | Teknoloji                          | Açıklama                                      |
| ---------- | -----------------------------------| --------------------------------------------- |
| Frontend   | ReactJS, Recharts                  | Dinamik grafiksel arayüz                      |
| Backend    | Spring Boot, Redis, JasperReports  | Mikroservis mimarisi ile REST & Kafka API'ler |
| Mesajlaşma | Apache Kafka                       | Servisler arası async iletişim                |
| Deployment | Docker + Docker Compose            | Tüm sistemi container olarak çalıştırma       |
| NLP        | Java (OpenNLP) veya Python (spaCy) | Metin analizi (CRUD, bug, tekrar vs.)         |


## Mikroservisler
| Servis Adı                | Açıklama |
|---------------------------|----------|
| `issue-producer-service`  | Haftalık iş verisini Kafka'ya yollar |
| `nlp-processor-service`   | NLP ile metin analizi yapar |
| `insight-generator-service` | NLP sonuçlarını yorumlayıp skor, tekrar, öneri üretir |
| `dashboard-backend`          | Redis + REST API / WebSocket sunar. Verileri Redis’ten okuyup React frontend’e servis eder. |
| `feedback-collector-service` | feedback toplayan ve Kafka üzerinden diğer servislerle paylaşan mikroservistir |
| `feedback-analyzer-service`  | #feedback-events topic'ini dinler, Redis bağlantısı kurar ve gelen feedbackleri Redis’e yazar |
| `jasperreports-service`   | Raporları PDF olarak üretir |
| `frontend`                | ReactJS dashboard: grafikler, modül skorları, öneriler |

## Kafka Topic Yapısı
| Topic Adı          | Açıklama            |
| ------------------ | ------------------- |
| `issue-events`     | Producer → NLP      |
| `analysis-results` | NLP → Insight       |
| `insight-results`  | Insight → Dashboard |


## Klasör/Yol
| Klasör/Yol           | Açıklama                                                    |
| -------------------- | ----------------------------------------------------------- |
| `services/`          | Tüm backend mikroservisler burada yer alır.                 |
| `frontend/`          | React tabanlı kullanıcı arayüzü.                            |
| `shared/`            | Ortak Java sınıfları veya konfigürasyon (isteğe bağlı).     |
| `.gitignore`         | IDE, OS ve derleme çıktısı dosyalarını dışlar.              |
| `docker-compose.yml` | Kafka, Redis, servislerin birlikte ayağa kalkmasını sağlar. |
| `application.yml`    | Her servisin kendi portu ve Kafka ayarları          |


##  Docker ile Çalıştırmak
```bash
docker-compose up --build
```
* Kafka, Redis, Spring Boot servisleri ve frontend tek komutla ayağa kalkacak

##  Proje Yapısı

```
IssueMindAI/
├── README.md
├── .gitignore
├── docker-compose.yml
├── shared/                                 # Ortak sınıf ve konfigürasyonlar
│
├── frontend/                               # ReactJS arayüz projesi
│   ├── public/
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── services/
│       └── App.jsx
│
└── services/
    ├── issue-producer-service/             # Kafka Producer mikroservisi
    │   ├── pom.xml
    │   └── src/
    │       ├── main/
    │       │   ├── java/com/uyg5/dmtbkts/issueproducerservice/
    │       │   │   ├── IssueProducerServiceApplication.java
    │       │   │   ├── controller/IssueController.java
    │       │   │   ├── kafka/IssueProducer.java
    │       │   │   ├── model/Issue.java
    │       │   │   └── config/             # (gerekirse Kafka config)
    │       │   └── resources/
    │       │       └── application.yml
    │
    ├── nlp-processor-service/              # Kafka Consumer + NLP
    │   ├── pom.xml
    │   └── src/main/java/com/uyg5/dmtbkts/nlpprocessorservice/
    │       ├── NLPProcessorApplication.java
    │       ├── consumer/NLPConsumer.java
    │       ├── service/NLPService.java
    │       ├── model/Issue.java
    │       └── config/
    │       └── resources/application.yml
    │
    ├── insight-generator-service/          # NLP sonucu analiz ve öneri üretimi
    │   ├── pom.xml
    │   └── src/main/java/com/uyg5/dmtbkts/insightgeneratorservice/
    │       ├── InsightGeneratorApplication.java
    │       ├── consumer/InsightConsumer.java
    │       ├── service/InsightService.java
    │       ├── model/InsightResult.java
    │       └── config/
    │       └── resources/application.yml
    │
    ├── dashboard-backend/                  # Redis & frontend için API servisi
    │   ├── pom.xml
    │   └── src/main/java/com/uyg5/dmtbkts/dashboardbackend/
    │       ├── DashboardBackendApplication.java
    │       ├── controller/DashboardController.java
    │       ├── service/DashboardService.java
    │       └── config/
    │       └── resources/application.yml
    │
    └── jasperreports-service/              # PDF rapor üretimi
        ├── pom.xml
        └── src/main/java/com/uyg5/dmtbkts/jasperreportsservice/
            ├── JasperReportsApplication.java
            ├── controller/ReportController.java
            ├── service/ReportService.java
            ├── resources/
            │   ├── application.yml
            │   └── reports/                # .jrxml şablonları
```

## Coding Flow

### Kafka + issue-producer-service
Spring Boot uygulaması
REST API üzerinden gelen işleri issue-events Kafka topic’ine gönder

###  nlp-processor-service
- Kafka consumer
- Mesaj al, NLP ile analiz et (CRUD mi? Tekrar mı? vs.)
- Sonucu analysis-results topic’ine yolla

### insight-generator-service
- analysis-results dinle
- Modül bazlı skor hesapla
- Sistem önerisi üret (örn. "Refactor önerilir")
- Sonucu insight-results topic’ine yolla

### dashboard-backend + Redis
- Kafka’dan veriyi al
- Redis’e cache'le
- React frontend’e REST API veya WebSocket ile sun

### frontend (ReactJS)
- Modül filtreleme
- Grafiklerle veri gösterimi (line chart, bar chart, pie)
- Öneri kartları (AI insights)

### jasperreports-service
- PDF rapor isteği REST üzerinden alınır
- Jasper template kullanılarak çıktı oluşturulur



##  Test
* Kafka'daki issue-events topic'ine bu mesaj gider.
``` 
curl -X POST http://localhost:8081/api/issues \
-H "Content-Type: application/json" \
-d '{
  "id": "123",
  "title": "Kayıt eklenemiyor",
  "description": "Modül A'da bug var",
  "module": "A",
  "type": "bug"
}'
```