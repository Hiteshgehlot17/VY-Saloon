# VY Saloon - Software Requirements Specification (SRS)

## Project Overview

VY Saloon is a web-based appointment booking system that allows customers to book salon appointments online while enabling the salon owner to manage employees, services, and appointments efficiently.

The system aims to eliminate manual appointment booking and provide a seamless booking experience for both customers and salon staff.

---

## User Roles

### 1. Customer

A customer can:

- Register an account
- Login securely
- View available services
- View available employees
- Select a specific employee
- Select an available time slot
- Book an appointment
- View booking history
- Cancel appointment (before scheduled time)
- Download appointment receipt

---

### 2. Admin (Salon Owner)

The admin can:

- Login securely
- View dashboard
- Add employees
- Update employee details
- Delete employees
- Add salon services
- Update services
- Delete services
- View all appointments
- Update appointment status
- View booking statistics

---

## Functional Requirements

### Authentication

- Customer Registration
- Customer Login
- Admin Login
- JWT Authentication
- Password Encryption

---

### Employee Management

- Add Employee
- Update Employee
- Delete Employee
- Activate/Deactivate Employee
- Define Working Hours

---

### Service Management

- Add Service
- Update Service
- Delete Service
- Define Service Duration
- Define Service Price

---

### Appointment Booking

Customer should be able to:

- Select Service
- Select Employee
- Select Date
- View Available Slots
- Confirm Appointment

The system should prevent double booking.

---

### Appointment Management

Customer:

- View My Appointments
- Cancel Appointment

Admin:

- View All Appointments
- Confirm Appointment
- Complete Appointment
- Cancel Appointment

---

### Receipt Generation

Generate appointment receipt containing:

- Booking ID
- Customer Name
- Employee Name
- Service Name
- Date
- Time
- Price

Receipt should be downloadable as PDF.

---

## Non Functional Requirements

- Responsive UI
- Secure Authentication
- Fast API Response
- Scalable Architecture
- Clean Code
- RESTful APIs

---

## Technologies

Frontend

- React
- Tailwind CSS

Backend

- Spring Boot
- Spring Security
- Spring Data JPA

Database

- MySQL

Authentication

- JWT

API Style

- REST API