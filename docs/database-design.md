# VY Saloon - Database Design

## 1. Users Table

Purpose:
Stores customer and admin login information.

| Column | Type | Constraints | Description |
|---------|------|------------|-------------|
| id | BIGINT | PK, Auto Increment | Unique user id |
| full_name | VARCHAR(100) | NOT NULL | User full name |
| email | VARCHAR(150) | UNIQUE | Login email |
| phone | VARCHAR(15) | UNIQUE | Phone number |
| password | VARCHAR(255) | NOT NULL | Encrypted password |
| role | ENUM | CUSTOMER, ADMIN | User role |
| created_at | TIMESTAMP | NOT NULL | Account creation date |

---

## 2. Employees Table

Purpose:
Stores all salon employees.

| Column | Type | Constraints | Description |
|---------|------|------------|-------------|
| id | BIGINT | PK | Employee id |
| full_name | VARCHAR(100) | NOT NULL | Employee name |
| phone | VARCHAR(15) | UNIQUE | Contact number |
| specialization | VARCHAR(100) | NULL | Hair Stylist / Barber etc. |
| start_time | TIME | NOT NULL | Work start |
| end_time | TIME | NOT NULL | Work end |
| lunch_start | TIME | NULL | Lunch start |
| lunch_end | TIME | NULL | Lunch end |
| active | BOOLEAN | DEFAULT TRUE | Employee available |

---

## 3. Services Table

Purpose:
Stores salon services.

| Column | Type | Constraints | Description |
|---------|------|------------|-------------|
| id | BIGINT | PK | Service id |
| service_name | VARCHAR(100) | NOT NULL | Service name |
| duration | INTEGER | NOT NULL | Duration in minutes |
| price | DECIMAL(10,2) | NOT NULL | Service cost |
| description | TEXT | NULL | Details |
| active | BOOLEAN | DEFAULT TRUE | Available |

---

## 4. Appointments Table

Purpose:
Stores customer bookings.

| Column | Type | Constraints | Description |
|---------|------|------------|-------------|
| id | BIGINT | PK | Appointment id |
| booking_number | VARCHAR(30) | UNIQUE | Booking reference |
| user_id | BIGINT | FK | Customer |
| employee_id | BIGINT | FK | Employee |
| service_id | BIGINT | FK | Service |
| appointment_date | DATE | NOT NULL | Booking date |
| start_time | TIME | NOT NULL | Appointment start |
| end_time | TIME | NOT NULL | Appointment end |
| status | ENUM | PENDING, CONFIRMED, COMPLETED, CANCELLED | Booking status |
| created_at | TIMESTAMP | NOT NULL | Booking time |

---

## Relationships

User (1) --------< Appointment >-------- (1) Employee
                     |
                     |
                  Service