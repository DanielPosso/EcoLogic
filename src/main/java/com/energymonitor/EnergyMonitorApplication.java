package com.energymonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EnergyMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyMonitorApplication.class, args);
	}

}

/*
General Project Structure

- Application Flow
Client Request → Controller → Service → Repository → Database
     ↑                 ↓          ↓          ↑
     └─────── Response ←──── Business Logic ─┘

-Key Components and its relationships

[Controllers]
    │
    ├── AuthController      → Handles user authentication
    ├── EnergyController    → Manages energy consumption data
    ├── DashboardController → Provides analytics and summaries
    └── UserController      → Manages user operations
           │
           ▼
[Services]  (Business Logic Layer)
    │
    ├── AuthService        → Authentication logic
    ├── UserService        → User management
    ├── EnergyService      → Energy data processing
    └── DashboardService   → Analytics calculations
           │
           ▼
[Repositories] (Data Access Layer)
    │
    ├── UserRepository     → User data operations
    ├── EnergyRepository   → Energy data operations
    └── DeviceRepository   → Device data operations
           │
           ▼
[Database]
    │
    ├── users             → User information
    ├── energy_consumption → Consumption records
    └── devices           → Device information

- Security Flow

Request → JwtAuthenticationFilter → SecurityConfig → UserDetailsService
   ↑            │                        │                │
   └────────────┴────── JwtTokenProvider ────────────────┘

- Data Transfer

Client ←→ DTOs ←→ Controllers ←→ Entities ←→ Database

- General Project Structure

src/main/java/com/energymonitor/
├── EnergyMonitorApplication.java
├── config/                          # Configuration classes
│   ├── SecurityConfig.java
│   ├── WebConfig.java
│   └── SwaggerConfig.java          # API documentation
├── controller/                      # HTTP endpoints
│   ├── AuthController.java         # Authentication endpoints
│   ├── UserController.java         # User management
│   ├── EnergyController.java       # Energy consumption endpoints
│   └── DashboardController.java    # Analytics and dashboard data
├── dto/                            # Data Transfer Objects
│   ├── auth/
│   │   ├── LoginDto.java
│   │   ├── RegisterDto.java
│   │   └── TokenDto.java
│   └── energy/
│       ├── ConsumptionDto.java
│       └── DashboardDto.java
├── model/                          # Database entities
│   ├── User.java
│   ├── EnergyConsumption.java
│   └── Device.java
├── repository/                     # Database access
│   ├── UserRepository.java
│   ├── EnergyRepository.java
│   └── DeviceRepository.java
├── service/                        # Business logic
│   ├── AuthService.java
│   ├── UserService.java
│   ├── EnergyService.java
│   └── DashboardService.java
├── security/                       # Security components
│   ├── JwtTokenProvider.java
│   ├── UserDetailsServiceImpl.java
│   └── JwtAuthenticationFilter.java
└── exception/                      # Custom exceptions
    ├── GlobalExceptionHandler.java
    └── CustomExceptions.java

-Example Request Flow: Recording Energy Consumption

1. HTTP Request
   POST /api/energy/record
   └─→ EnergyController
       │
       ├─→ JWT Authentication Filter
       │   └─→ Validates user token
       │
       ├─→ EnergyService
       │   ├─→ Validates data
       │   ├─→ Processes consumption
       │   └─→ Calculates costs
       │
       ├─→ EnergyRepository
       │   └─→ Saves to database
       │
       └─→ Returns Response to Client

2. Authentication Flow

	1. Login Request
	   POST /api/auth/login
	   └─→ AuthController
	       │
	       ├─→ AuthService
	       │   ├─→ Validates credentials
	       │   └─→ UserDetailsService checks user exists
	       │
	       ├─→ JwtTokenProvider
	       │   └─→ Generates JWT token
	       │
	       └─→ Returns token to client

	2. Protected Endpoint Access
	   GET /api/dashboard
	   └─→ JwtAuthenticationFilter
	       │
	       ├─→ Extracts token from header
	       ├─→ JwtTokenProvider validates token
	       ├─→ Sets SecurityContext
	       │
	       └─→ Request proceeds to DashboardController

3. Security Implementation Details:

// 1. Token in requests
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

// 2. JWT Token contains
{
  "sub": "user@email.com",    // Subject (user identifier)
  "roles": ["USER"],          // User roles
  "iat": 1516239022,         // Issued at
  "exp": 1516242622          // Expiration time
}

// 3. Security layers
├── JWT Filter (every request)
├── Role-based access control
├── Method-level security
└── Password encryption

-Key Security Features

Authentication: Verify user identity
Authorization: Control access to resources
Token Management: Handle JWT lifecycle
Password Security: BCrypt encryption
Session Management: Stateless JWT-based

*/

