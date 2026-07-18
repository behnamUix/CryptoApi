<div align="center">

# 🚀 Crypto API Gateway

<p align="center">
  <img src="shots/banner.png" alt="Crypto API Gateway Banner" width="100%">
</p>

### A Modern Cryptocurrency REST API Gateway Built with Ktor

A lightweight and scalable backend service built with **Ktor** that works as a **Crypto API Gateway**.  
The application provides RESTful APIs for retrieving cryptocurrency lists and detailed coin information.

The project follows **Clean Architecture principles** and is containerized using **Docker** for easy deployment.

---

### 🇮🇷 معرفی پروژه

یک سرویس بک‌اند مدرن مبتنی بر **Ktor** که به عنوان یک **Crypto API Gateway** عمل می‌کند.

این پروژه امکان:
- دریافت لیست ارزهای دیجیتال
- مشاهده جزئیات هر ارز دیجیتال
- ارائه داده‌ها از طریق REST API

را فراهم می‌کند.

ساختار پروژه بر اساس **Clean Architecture** طراحی شده و با استفاده از **Docker** قابل اجرا و استقرار است.

</div>

---

# 📸 Screenshots

<p align="center">
  <img src="shots/list_shot.png" width="80%" alt="Cryptocurrency List">
</p>

<p align="center">
  <img src="shots/detail_shot.png" width="35%" alt="Cryptocurrency Detail">
</p>

---

# ✨ Features | قابلیت‌ها

## 🇺🇸 Features

- Cryptocurrency list endpoint
- Cryptocurrency detail endpoint
- RESTful API implementation
- Clean Architecture structure
- Dependency Injection with Koin
- Asynchronous processing using Kotlin Coroutines
- External API communication using Ktor Client
- Docker container support


## 🇮🇷 قابلیت‌ها

- دریافت لیست ارزهای دیجیتال
- نمایش اطلاعات دقیق هر ارز دیجیتال
- پیاده‌سازی REST API
- معماری Clean Architecture
- مدیریت وابستگی‌ها با Koin
- پردازش غیرهمزمان با Kotlin Coroutines
- ارتباط با سرویس‌های خارجی با Ktor Client
- پشتیبانی از Docker

---

# 🛠 Tech Stack

| Technology | Usage |
|---|---|
| Kotlin | Programming Language |
| Ktor Server | Backend Framework |
| Ktor HTTP Client | External API Communication |
| Koin | Dependency Injection |
| Kotlin Coroutines | Asynchronous Operations |
| Docker | Containerization |
| Clean Architecture | Software Architecture |
| HTML5 | Dashboard UI |
| CSS3 | Dashboard Styling |
| JavaScript | Frontend Interaction |

---

# 🏗 Architecture

The project is structured based on **Clean Architecture**:

```text
                    Presentation Layer

        Routes • API Endpoints • Dashboard

                         │

                         ▼

                    Domain Layer

        Models • UseCases • Repository Contracts

                         │

                         ▼

                     Data Layer

        Repository Implementation
        Remote Data Source
        Ktor HTTP Client
