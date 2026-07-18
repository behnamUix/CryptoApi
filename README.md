<div align="center">

# 🚀 Crypto API Gateway

<p align="center">
  <img src="shots/banner.png" alt="Crypto API Gateway Banner" width="100%">
</p>

A modern **Ktor** backend service that acts as a lightweight Crypto API Gateway. The project provides REST APIs for retrieving a list of cryptocurrencies and viewing detailed information for each cryptocurrency. Built with **Clean Architecture** and containerized using **Docker**.

یک سرویس بک‌اند مدرن مبتنی بر **Ktor** که به عنوان یک **Crypto API Gateway** عمل می‌کند. این پروژه امکان دریافت لیست ارزهای دیجیتال و مشاهده اطلاعات هر ارز را از طریق REST API فراهم می‌کند. ساختار پروژه بر اساس **Clean Architecture** طراحی شده و با استفاده از **Docker** قابل استقرار است.

</div>

---

# 📸 Screenshots

<p align="center">
  <img src="shots/list_shot.png" width="80%" alt="Dashboard">
</p>

<p align="center">
  <img src="shots/detail_shot.png" width="30%" alt="Coin Detail">
</p>

---

# ✨ Features | قابلیت‌ها

### 🇺🇸 English

- Display a list of cryptocurrencies
- View detailed information for each cryptocurrency
- RESTful API
- Clean Architecture
- Docker support

### 🇮🇷 فارسی

- نمایش لیست ارزهای دیجیتال
- نمایش اطلاعات هر ارز دیجیتال
- پیاده‌سازی REST API
- معماری Clean Architecture
- پشتیبانی از Docker

---

# 🛠️ Tech Stack

- Kotlin
- Ktor Server
- Ktor HTTP Client
- Koin
- Kotlin Coroutines
- Docker
- Clean Architecture
- HTML5
- CSS3
- JavaScript

---

# 🏗️ Project Architecture

```text
       ┌──────────────────────────────────────────┐
       │              Presentation                │
       │     Routes • HTTP • HTML Dashboard       │
       └───────────────────┬──────────────────────┘
                           │
                           ▼
       ┌──────────────────────────────────────────┐
       │                 Domain                   │
       │     Models • UseCases • Interfaces       │
       └───────────────────┬──────────────────────┘
                           │
                           ▼
       ┌──────────────────────────────────────────┐
       │                  Data                    │
       │ Repositories • Remote API • Ktor Client  │
       └──────────────────────────────────────────┘
```

---

# 📂 Project Structure

```text
src
├── data
│   ├── remote
│   ├── repository
│   └── model
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── presentation
│   ├── routes
│   └── dashboard
│
└── di
```

---

# 🐳 Docker

```bash
docker build -t crypto-api-gateway .
docker run -p 8080:8080 crypto-api-gateway
```

---

# ❤️ Built With

- Kotlin
- Ktor
- Koin
- Docker
- Clean Architecture
