# Pharmacy Management System (Java Console Application)

This project is a simple **Pharmacy Management System** developed in **Java (Console)** using **SQL Server** as the database.

The application is designed as a university project and demonstrates basic CRUD operations, database connectivity, and role-based access.

---

## Features

- User authentication (Login)
- Role-based menu (Admin / Cashier)
- Supplier management (Add, Update, Delete, List)
- Medicine management (Add, Update, Delete, List)
- Sales processing
    - Create sale
    - Add medicines to sale
    - Automatic stock reduction
    - Save sales and sale items to database

---

## Technologies Used

- Java
- Maven
- SQL Server
- JDBC

---

## Database

The database contains the following main tables:
- Users
- Suppliers
- Medicines
- Sales
- SaleItems

Stored procedures are used for most CRUD operations.

---

## How to Run

1. Open the project in IntelliJ IDEA
2. Make sure SQL Server is running
3. Update database connection settings in `DbConnection.java`
4. Run `Main.java`

---

## Login Credentials (Example)

- **Admin**
    - Username: `admin`
    - Password: `admin123`

- **Cashier**
    - Username: `cashier`
    - Password: `cashier123`

---

## Notes

- This is a console-based application.
- Input validation is kept simple.
- The project focuses on functionality and database interaction.

---

