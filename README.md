📣 Gila Notification System
🧩 Overview

This project implements a notification system that routes messages to users based on:

Category subscriptions (Sports, Finance, Movies)
Preferred notification channels (SMS, Email, Push)

The system is designed with clean architecture principles, scalability in mind, and extensibility for future channels.

🏗️ Architecture

The backend follows a layered architecture:

Controller → Service → Dispatcher → NotificationSender (Strategy)
                          ↓
                      Repository (JPA)
Key Design Decisions
Strategy Pattern for notification channels
Separation of concerns across layers
DTO-based API responses (no entity exposure)
Centralized error handling
Mocked user data (as per requirements)
⚙️ Tech Stack
Backend
Java 17 / 21
Spring Boot
Spring Data JPA
H2 In-Memory Database
Gradle
Lombok
Frontend
React
Axios
Testing & Quality
JUnit 5
Mockito
Jacoco (code coverage)
🚀 How to Run
1. Clone the repo
git clone https://github.com/romegadra/gilacodechallenge.git
cd gilacodechallenge
2. Run Backend
cd backend
./gradlew bootRun

Backend runs on:

http://localhost:8080
3. Run Frontend
cd frontend
npm install
npm start

Frontend runs on:

http://localhost:3000
🌐 Application URLs
Component	URL
Frontend	http://localhost:3000

Backend API	http://localhost:8080/api/notifications

H2 Console	http://localhost:8080/h2-console
🧪 H2 Database Access
JDBC URL: jdbc:h2:mem:notificationdb
User: sa
Password: (empty)
📡 API Endpoints
Send Notification
POST /api/notifications

Request:

{
  "category": "SPORTS",
  "message": "Game starts at 8 PM"
}

Response:

{
  "message": "Message processed successfully",
  "totalNotifications": 2,
  "successfulNotifications": 2,
  "failedNotifications": 0
}
Get Logs
GET /api/notifications/logs
🧪 Testing

Run tests:

./gradlew test
📊 Code Coverage (Jacoco)

Generate report:

./gradlew test jacocoTestReport

Open:

build/reports/jacoco/test/html/index.html
✅ Features Implemented
Category-based message routing
Multi-channel notification delivery
Strategy pattern for extensibility
Notification logs with status tracking (SENT / FAILED)
Error handling with structured responses
Validation for input data
Unit tests with multiple scenarios
Code coverage reporting
Simple UI for interaction and logs
⚠️ Assumptions
Users are mocked in memory (no persistence required)
Notification sending is simulated (no external providers)
Focus is on architecture and extensibility
🔮 Future Improvements
Integrate real providers (Twilio, SendGrid, Firebase)
Add authentication & authorization
Move to persistent database (PostgreSQL)
Add retry mechanism for failed notifications
Introduce message queue (Kafka / RabbitMQ)
Improve UI/UX
Add integration tests
👨‍💻 Author

Rodrigo Medina

🎯 Notes

This project focuses on clean design, scalability, and maintainability, rather than external integrations.
