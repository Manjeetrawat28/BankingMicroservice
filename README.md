   # 🏦 **Microservices Banking System (Saga Orchestration)**

## 📌 Overview

This project is a distributed banking system built using Microservices Architecture and the Saga Orchestration Pattern.

It simulates real-world banking operations such as:

* User creation
* Account management
* Debit/Credit transactions
* Email notifications

**The system ensures:**

* ✅ Data consistency (without 2PC)
* ✅ High scalability
* ✅ Fault tolerance
* ✅ Strong auditing & logging

---

## 🏗️ Architecture Overview

### 🔑 Core Principles

* Single Responsibility per Service
* Loose Coupling
* Centralized Orchestration
* Event-Driven Communication
* Eventual Consistency (Saga Pattern)

### 🔄 End-to-End Flow (Debit Transaction Example)

1. Client → Transaction Service
2. Transaction → User Service (Async - WebClient)
3. Transaction → Account Service (Sync - REST)
4. Account → Perform Debit + Persist
5. Transaction → Update State Table
6. Transaction → Kafka → Notification Service
7. Response returned to Client

### 🧠 Detailed Flow

1. Inbound request stored in INBOUND_LOG
2. Transaction state initialized
3. User validation (async call)
4. Account service invoked (critical sync call)
5. Transaction persisted
6. Outbound response logged
7. Notification triggered asynchronously

---

## 🧩 Microservices Breakdown

### 1️⃣ User Microservice

#### 📌 Responsibilities

* Create and fetch users
* Generate:
    * User ID
    * Account Number
* Enforce one account per email

#### 🗄️ Tables

**user_master**

* user_id (PK)
* name
* email (UNIQUE)
* created_at
* updated_at

**account_master**

* account_id (PK)
* account_number (UNIQUE)
* user_id (FK)
* account_status
* created_at

> **💡 Key Rule:** One user = One account (strict constraint)

---

### 2️⃣ Account Microservice

#### 📌 Responsibilities

* Check account status
* Balance inquiry
* Debit / Credit operations

#### 🗄️ Tables

**account_master**

* account_number
* balance
* status

**transaction_master**

* transaction_id
* account_number
* from_account
* to_account
* amount
* transaction_type (DEBIT/CREDIT)
* status
* request_payload
* created_at

> **💡 Key Design:** Ensures strong consistency within service boundary. All transactions are persisted before response.

---

### 3️⃣ Transaction Microservice (Orchestrator) ⭐

*🔥 Heart of the system*

#### 📌 Responsibilities

* Central entry point for all requests
* Implements Saga Orchestration
* Coordinates all services
* Handles:
    * Logging
    * Auditing
    * Error handling

#### 🗄️ Transaction Service – Internal Tables

**1. 🧾 transaction_state**

Tracks complete lifecycle:

* transaction_id
* request_payload
* response_payload
* status (INITIATED | SUCCESS | FAILED)
* error_message
* start_time
* end_time

**2. 📥 inbound_log**

Stores incoming requests:

* request_id
* api_name
* request_payload
* headers
* timestamp
* source
* **✔️ Used for:** Debugging input issues, Request traceability, Audit compliance

**3. 📤 outbound_log**

Stores final responses:

* response_id
* request_id
* response_payload
* status (SUCCESS | FAILURE)
* error_reason
* response_time
* timestamp
* **✔️ Used for:** SLA tracking, Failure analysis, Output auditing

#### ⚖️ Why Separate Logs?

| Inbound Log | Outbound Log |
|---|---|
| Captures request | Captures response |
| Input debugging | Output debugging |
| Tracks caller | Tracks system result |

#### 📡 Communication Pattern

| From | To | Type | Purpose |
|---|---|---|---|
| Transaction | User | Async (WebClient) | Validation |
| Transaction | Account | Sync (REST) | Critical operations |
| Transaction | Notification | Kafka (Async) | Event-driven |

#### 📡 Kafka Request-Reply Flow

```text
Transaction Service
    ↓ (Request Topic)
Kafka
    ↓
Notification Service
    ↓ (Reply Topic)
Kafka
    ↓
Transaction Service
```

**✔️ Fully asynchronous & decoupled**

---

### 4️⃣ Notification Microservice

#### 📌 Responsibilities

Send email notifications for:

* Debit
* Credit

#### ⚠️ Important Design

* **❗ Non-blocking service**
* Notification failure does NOT impact transaction
* Improves resilience & performance

---

## ❗ Error Handling Strategy

### 🧠 Business Errors

* Insufficient balance
* User not found
* Duplicate email
* **✔️ Handled via:** Custom exceptions, Proper error codes

### ⚙️ Technical Errors

* Timeouts
* Kafka failures
* DB issues
* **✔️ Handled via:** Graceful fallback, Timeout configs, Safe error responses

---

## 📦 Standard API Response

```json
{
  "status": "SUCCESS | FAILURE",
  "code": "ERROR_CODE",
  "message": "Detailed message",
  "data": {}
}
```

---

## 📊 Logging & Observability

* AOP-based logging
* Log4j integration
* **Tracks:**
  * Request & response
  * Execution time
  * Errors

---

## ⚡ Performance & Reliability

* **HikariCP** → DB connection pooling
* **Timeouts** → Prevent cascading failures
* **Async processing** → Better throughput
* **Kafka** → Decoupled communication

---

## 🔐 Data Consistency

* ❌ No 2PC
* ✅ Saga Orchestration
* **Each step is:**
  * Logged
  * Traceable
  * Recoverable

---

## 🚀 Key Features

* ✅ Microservices architecture
* ✅ Saga orchestration
* ✅ Hybrid communication (Sync + Async)
* ✅ Kafka event-driven system
* ✅ Strong auditing & logging
* ✅ Robust error handling
* ✅ Non-blocking notification
* ✅ One account per user

---

## 📁 Project Structure

```text
BankingMicroservice/
│
├── user-service/
│   ├── src/
│   └── pom.xml
│
├── account-service/
│   ├── src/
│   └── pom.xml
│
├── transaction-service/
│   ├── src/
│   └── pom.xml
│
├── notification-service/
│   ├── src/
│   └── pom.xml
│
├── .gitignore
├── pom.xml
└── README.md
```

---

# 🛠️ Local Setup & Installation

This section explains how to run the complete banking microservices system locally after cloning the repository.

## 1️⃣ Prerequisites

Make sure the following are installed on your local machine:

* **Java 17+**
* **Maven 3.8+**
* **MySQL 8+**
* **Apache Kafka**
* **Apache Zookeeper** (if using the Zookeeper-based Kafka setup)
* **Git**

Verify the installations:

```bash
java -version
mvn -version
mysql --version
git --version
```

---

## 2️⃣ Clone the Repository

Clone the public repository:

```bash
git clone https://github.com/Manjeetrawat28/BankingMicroservice.git
```

Move into the project:

```bash
cd BankingMicroservice
```

---

## 3️⃣ Create the MySQL Database

The services expect a MySQL database named:

```text
bankdb
```

Create it using MySQL:

```sql
CREATE DATABASE bankdb;
```

You can verify it:

```sql
SHOW DATABASES;
```

---

## 4️⃣ Configure application.properties

The `application.properties` files are not included in the public repository.

After cloning the project, create an `application.properties` file inside the following directories:

```text
user-service/src/main/resources/application.properties

account-service/src/main/resources/application.properties

transaction-service/src/main/resources/application.properties

notification-service/src/main/resources/application.properties
```

Configure each service according to your local environment.

---

### Account Service

Create:

```text
account-service/src/main/resources/application.properties
```

Example:

```properties
spring.application.name=account

server.port=8083
server.servlet.context-path=/account

spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
```

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your local MySQL password.

---

### Notification Service

Create:

```text
notification-service/src/main/resources/application.properties
```

Configure Gmail SMTP:

```properties
############# EMAIL CONFIG #####

spring.mail.host=smtp.gmail.com
spring.mail.port=587

spring.mail.username=YOUR_GMAIL_ADDRESS
spring.mail.password=YOUR_GMAIL_APP_PASSWORD

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


################# KAFKA CONFIG ############

# Kafka Consumer Config
spring.kafka.bootstrap-servers=localhost:9092

spring.kafka.consumer.group-id=notification-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.enable-auto-commit=false

spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
```

Replace:

```text
YOUR_GMAIL_ADDRESS
YOUR_GMAIL_APP_PASSWORD
```

with your own Gmail account and **Gmail App Password**.

> **Note:** Use a Gmail App Password rather than your normal Gmail account password. Do not commit the actual email password or App Password to GitHub.

---

### Kafka Configuration

Kafka is expected to run locally on:

```text
localhost:9092
```

The Notification Service uses:

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

Make sure your Kafka broker is running before starting the services.

---

### User Service

Create:

```text
user-service/src/main/resources/application.properties
```

Configure the service with your local MySQL database and the required server port.

The database should point to:

```text
jdbc:mysql://localhost:3306/bankdb
```

Use your own MySQL username and password.

---

### Transaction Service

Create:

```text
transaction-service/src/main/resources/application.properties
```

Configure:

* Local MySQL connection
* Transaction service port
* User Service URL
* Account Service URL
* Kafka bootstrap server
* Required Kafka topics

For local execution, inter-service communication should point to your local services.

For example:

```text
User Service    → http://localhost:<USER_SERVICE_PORT>
Account Service → http://localhost:8083/account
Kafka           → localhost:9092
```

Replace the placeholders with the ports configured in your local `application.properties` files.

---

## 5️⃣ Start MySQL

Make sure MySQL is running before starting the microservices.

Default database configuration:

```text
Host: localhost
Port: 3306
Database: bankdb
Username: root
```

---

## 6️⃣ Start Zookeeper and Kafka

Start Zookeeper and Kafka according to your local Kafka installation.

The expected local Kafka broker is:

```text
localhost:9092
```

The overall dependency flow is:

```text
Zookeeper
    ↓
Kafka Broker
    ↓
Microservices
```

Make sure Kafka is running before starting the Notification and Transaction services.

---

## 7️⃣ Build the Project

From the root project directory:

```bash
mvn clean install
```

To skip tests:

```bash
mvn clean install -DskipTests
```

---

## 8️⃣ Run the Microservices

Start the services individually.

Recommended order:

### 1. User Service

```bash
cd user-service
mvn spring-boot:run
```

### 2. Account Service

```bash
cd account-service
mvn spring-boot:run
```

### 3. Transaction Service

```bash
cd transaction-service
mvn spring-boot:run
```

### 4. Notification Service

```bash
cd notification-service
mvn spring-boot:run
```

You can also run each service directly from your IDE.

> 💡 Each microservice should be started in a separate terminal or IDE run configuration.

---

## 9️⃣ Verify the Services

Once all services are running, verify that:

* MySQL is running
* Kafka is running
* All four Spring Boot services have started successfully
* The configured ports are available
* The services can communicate with each other
* Kafka topics are available
* Database connections are successful
* Gmail SMTP configuration is valid for the Notification Service

For the Account Service, the configured base URL is:

```text
http://localhost:8083/account
```

The exact endpoints depend on the controllers implemented in each service.

---

## 🔟 Running Tests

Run all tests from the root directory:

```bash
mvn test
```

Or run tests for an individual microservice:

```bash
cd account-service
mvn test
```

---

## 🐛 Common Local Setup Issues

### MySQL Connection Refused

If you see:

```text
Communications link failure
```

verify:

* MySQL is running
* MySQL is listening on port `3306`
* Database `bankdb` exists
* MySQL username/password are correct

---

### Kafka Connection Error

If Kafka connection errors occur, verify:

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

and make sure Kafka is running.

---

### Gmail Authentication Error

If the Notification Service cannot authenticate with Gmail:

* Verify the Gmail address
* Verify that SMTP authentication is enabled
* Use a Gmail **App Password**
* Do not use your normal Gmail account password
* Verify that the App Password is entered correctly

---

### Port Already in Use

If a service fails with:

```text
Web server failed to start. Port XXXX was already in use.
```

either stop the process using that port or change the service's `server.port` in its local `application.properties`.

---

## 📈 Future Enhancements

* API Gateway (Spring Cloud Gateway)
* Circuit Breaker (Resilience4j)
* Distributed Tracing (Zipkin)
* Docker & Kubernetes

---

## 👨‍💻 Author

**Manjeet Rawat**  
Java Backend Engineer | Spring Boot | Microservices | Kafka 
