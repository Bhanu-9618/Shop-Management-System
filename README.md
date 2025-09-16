# ThogaKade Shop Management System

A **desktop-based shop management system** built using **JavaFX** and **MySQL**, following the **MVC design pattern** and using **Singleton pattern** for database connection. This system manages items, customers, and orders efficiently, with a clean separation between the UI and business logic.  

---

## Features

- **Customer Management**
  - Add, update, delete, and view customers
    
- **Item Management**
  - Add, update, delete, and view items
  - Automatically updates stock quantities when orders are placed  

- **Order Management**
  - Place orders for multiple items
  - Automatically reduces stock quantity after order

- **Order Detail Management**
  - Add, update, or delete order details

- **Architecture & Design**
  - **MVC Pattern**: Clear separation between UI (FXML), controllers, and database logic
  - **Singleton Pattern**: Used for database connection to ensure a single shared instance
  - **Interfaces**: Ensure loose coupling between controllers and service classes

---

## Technologies Used

- **Java** 
- **JavaFX** for desktop GUI  
- **MySQL** for database management  
- **JDBC** for database connection  
- **MVC & Singleton design patterns** for clean architecture  



