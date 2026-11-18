# Online Banking System

## Project Overview

The Online Banking System is a Java-based web application that allows users to perform basic banking operations online. It provides a user-friendly interface for managing bank accounts and performing financial transactions. The application uses Java Servlets and JSP for server-side processing and MySQL for database management.

## Features

### User Features
- User Registration
- User Login and Logout
- Create Bank Account
- Check Account Balance
- Deposit Money
- Withdraw Money
- Transfer Money
- View User Dashboard

### Admin Features
- Admin Login
- Admin Dashboard
- View All Registered Users
- View All Bank Accounts
- View Individual User Details
- Block and Unblock Bank Accounts

## Technologies Used

| Category | Technologies |
|---|---|
| Programming Language | Java |
| Backend | Java Servlets, JDBC |
| Frontend | HTML, CSS, JavaScript, JSP |
| Database | MySQL |
| Server | Apache Tomcat |
| IDE | Eclipse |
| Version Control | Git, GitHub |

## Project Architecture

The project follows a layered structure:

- **Controller:** Handles HTTP requests and application logic using Servlets.
- **DAO:** Handles database operations.
- **Model:** Contains Java classes representing application entities.
- **Util:** Provides database connectivity.
- **JSP:** Handles the user interface.
- **CSS:** Provides styling for web pages.

## Project Structure

```text
OnlineBankingSystem/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/
│       │   ├── dao/
│       │   ├── model/
│       │   └── util/
│       │
│       └── webapp/
│           ├── css/
│           ├── jsp/
│           ├── WEB-INF/
│           └── META-INF/
│
└── README.md
```

## Database Configuration

Database: MySQL

Database Name: `online_banking`

The application uses JDBC to establish a connection with the MySQL database.

Configure your database connection using your own local MySQL credentials. Do not commit passwords or other sensitive information to GitHub.

**Note:** Add the SQL database creation script to the repository so that others can create the required tables before running the application.

## How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/CHINNASWAMYSUDARSHAN/Online-Banking-System.git
   ```

2. Open Eclipse IDE.

3. Import the project as an Existing Dynamic Web Project.

4. Install and configure MySQL.

5. Create the required database and tables.

6. Configure the MySQL JDBC connection.

7. Configure Apache Tomcat in Eclipse.

8. Run the project on the Tomcat server.

9. Open the application in your browser.

## Learning Outcomes

- Understanding Java Servlets and JSP.
- Implementing JDBC database connectivity.
- Performing CRUD operations using MySQL.
- Understanding DAO and MVC-style application organization.
- Handling HTTP requests and responses.
- Implementing session management.
- Developing a dynamic web application using Java.

## Author

** C Sudarshan**

GitHub: [CHINNASWAMYSUDARSHAN](https://github.com/CHINNASWAMYSUDARSHAN)
