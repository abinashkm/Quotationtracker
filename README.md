# Quotation Tracker (Backend)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](https://www.h2database.com/)

**Quotation Tracker** is a professional **Request for Quote (RFQ)** management system designed to streamline the procurement process. It connects customers with multiple vendors through a transparent, transparent, and role-based bidding environment.

This repository contains the **backend API** services, engineered for modularity, stateless security, and scalable data relationships.

---

## 🏗 System Architecture

The application follows a strict **N-Tier Architecture**, ensuring separation of concerns between security, business logic, and persistence layers.

### Request Flow
The diagram below illustrates the lifecycle of a request: moving from the Client, passing through the Spring Security Filter Chain (JWT Validation), into the Service Layer for business logic, and finally interacting with the H2 Database via JPA.

<img width="1240" height="2224" alt="Untitled diagram-2026-02-04-151232" src="https://github.com/user-attachments/assets/7ab78f58-8243-4917-86af-d03f53c11e0c" />


---

## 🔐 Security & Authentication

Security is handled via **Spring Security** using a stateless **JWT (JSON Web Token)** architecture. This ensures the backend remains scalable as it does not require server-side session storage.

### Authentication Lifecycle
1.  **Login:** User submits credentials via `/auth/login`.
2.  **Validation:** `AuthenticationManager` verifies credentials against the database.
3.  **Token Issuance:** A signed JWT containing the `userId` and `ROLE` (Customer/Vendor) is returned.
4.  **Authorization:** Subsequent requests must include `Authorization: Bearer <token>`.
<img width="8192" height="3822" alt="Spring Boot JWT Flow-2026-02-04-164802" src="https://github.com/user-attachments/assets/6dea34d7-9e07-4a3f-abd3-f17c2d69b1a3" />


---

## 📊 Database Design

The system utilizes **H2 (In-Memory Database)** for high-speed development and testing. The schema is normalized to support complex relationships between Users, RFQs, Quotes, and Contracts.

* **Users & Roles:** RBAC foundation.
* **RFQs:** Created by customers, viewable by vendors.
* **Quotes:** Submitted by vendors, linked to specific RFQs.
* **Contracts:** Generated upon quote acceptance.



---

## 🛠 Technology Stack

### Core Frameworks
| Technology | Usage |
| :--- | :--- |
| **Java 21** | Core language (LTS features). |
| **Spring Boot** | Application configuration and dependency management. |
| **Spring Security** | Authentication and RBAC authorization. |
| **JWT** | Stateless token-based security. |

### Data & Persistence
| Technology | Usage |
| :--- | :--- |
| **H2 Database** | In-memory relational database (Zero-config setup). |
| **Spring Data JPA** | Repository abstraction for database operations. |
| **Hibernate** | ORM implementation for mapping Entities to Tables. |

### Validation & Tools
| Technology | Usage |
| :--- | :--- |
| **Jakarta Validation** | Request integrity (`@NotNull`, `@Email`, etc.). |
| **Postman** | API endpoint testing and debugging. |
| **H2 Console** | Runtime database inspection. |

---

## 🚀 Getting Started

### Prerequisites
* JDK 21 or higher
* Maven 3.x

### Installation
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/quotation-tracker-backend.git](https://github.com/yourusername/quotation-tracker-backend.git)
    cd quotation-tracker-backend
    ```

2.  **Build the project**
    ```bash
    mvn clean install
    ```

3.  **Run the application**
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the Database**
    The application will start on port `8080`. You can access the H2 console at:
    * **URL:** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:testdb` (Check `application.properties` if different)
    * **Username:** `sa`
    * **Password:** (Empty or check properties)

---

## 🛡 API Security Endpoints

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/register` | Register a new user | Public |
| `POST` | `/auth/login` | Authenticate & get Token | Public |
| `POST` | `/api/rfq` | Create a new RFQ | `CUSTOMER` |
| `GET` | `/api/rfq` | View available RFQs | `VENDOR` |
| `POST` | `/api/quotes` | Submit a bid | `VENDOR` |

---
