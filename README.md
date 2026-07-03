# 🍔 Food Delivery API

A backend REST API built using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL** that simulates the core functionalities of an online food delivery platform.
The project demonstrates backend development concepts including layered architecture, relational database design, DTO-based communication, exception handling, and RESTful API development.

> **Note:** This project was developed to strengthen backend development skills using Spring Boot and follows a clean Controller-Service-Repository architecture.

---

## 🚀 Features

### 👤 Customer Management
- Register a new customer
- Update customer details

### 🍽️ Restaurant Management
- Add new restaurants
- Update restaurant information
- Switch restaurant type (Veg / Non-Veg)
- View available food items

### 📋 Menu Management
- Register menus
- Delete menus

### 🍕 Food Ordering
- Place food orders
- Retrieve customer order history

### 🚚 Delivery Partner Management
- Add delivery partners
- Update delivery partner details

### ⚙️ Backend Features
- RESTful API development
- Layered Controller-Service-Repository architecture
- DTO-based Request & Response handling
- Entity relationship mapping using Hibernate
- Global custom exception handling
- Spring Data JPA integration
- MySQL relational database

---

# 🛠️ Tech Stack

| Technology | Used |
|------------|------|
| Java 17 | ✅ |
| Spring Boot | ✅ |
| Spring Data JPA | ✅ |
| Hibernate | ✅ |
| MySQL | ✅ |
| Maven | ✅ |
| Lombok | ✅ |
| Postman | ✅ |

---

# 🏗️ Project Architecture

```
Controller
     │
     ▼
Service Layer
     │
     ▼
Repository Layer
     │
     ▼
MySQL Database
```

The project follows a layered architecture where each layer has a clear responsibility, improving maintainability and scalability.

---

# 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 │     ├── request
 │     └── response
 ├── converter
 ├── exception
 ├── enums
 └── FoodDeliveryApplication.java
```

---

# 🗄️ Database Relationships

| Entity | Relationship |
|---------|--------------|
| Customer → Food Orders | One-to-Many |
| Delivery Partner → Food Orders | One-to-Many |
| Food Order → Customer | Many-to-One |
| Food Order → Delivery Partner | Many-to-One |
| Food Order ↔ Food Items | Many-to-Many |
| Menu → Food Items | One-to-Many |
| Food Item → Menu | Many-to-One |
| Restaurant ↔ Menu | One-to-One |

---

# 📌 API Modules

### Customer APIs
- Add Customer
- Update Customer

### Restaurant APIs
- Add Restaurant
- Update Restaurant
- Change Restaurant Type
- Get Food Items

### Menu APIs
- Register Menu
- Delete Menu

### Food Order APIs
- Place Order
- Get Order History

### Delivery Partner APIs
- Add Delivery Partner
- Update Delivery Partner

---

# ⚡ Exception Handling

Custom exceptions implemented for:

- Customer Not Found
- Restaurant Not Found
- Food Item Not Found
- Delivery Partner Not Found

---

# 📖 Key Concepts Implemented

- REST API Development
- Layered Architecture
- DTO Pattern
- Entity Relationships
- Hibernate ORM
- Spring Data JPA
- Exception Handling
- Lombok
- Maven Project Structure

---

# ▶️ Getting Started

### Clone Repository

```bash
git clone https://github.com/Yath12-oops/food-delivery-api.git
```

### Navigate to Project

```bash
cd food-delivery-api
```

### Configure Database

Update the database credentials inside:

```
application.properties
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

# 🔮 Future Enhancements

- JWT Authentication
- Spring Security
- Swagger/OpenAPI Documentation
- Pagination & Sorting
- Role-Based Authorization
- Docker Support
- Unit & Integration Testing
- API Validation Improvements

---

# 👨‍💻 Author

**Yatharth Sachdeva**

- GitHub: https://github.com/Yath12-oops
- LinkedIn: https://www.linkedin.com/in/yaths12/

---

⭐ If you found this project useful, consider giving it a star!
