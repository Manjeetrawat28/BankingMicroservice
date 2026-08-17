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
root/
 ├── user-service/
 ├── account-service/
 ├── transaction-service/
 ├── notification-service/
 
```

---

## 🛠️ Setup Instructions

### 2️⃣ Start Dependencies
* MySQL
* Kafka + Zookeeper

### 3️⃣ Configure application.properties
* Database configs
* Kafka configs
* Ports

### 4️⃣ Run Services
1. User Service
2. Account Service
3. Transaction Service
4. Notification Service

---

## 📈 Future Enhancements
* API Gateway (Spring Cloud Gateway)
* Circuit Breaker (Resilience4j)
* Distributed Tracing (Zipkin)
* Docker & Kubernetes

---

## 👨‍💻 Author

**Manjeet Rawat**  
Backend Engineer | Java | Microservices | AWS Certified


