# Hackathon Backend

This is the backend service for the Hackathon project, built using **Java Spring Boot** and **Maven**. It provides APIs and services for managing the application, including database interactions, email notifications, and OAuth2 authentication.

## Features

- **Spring Boot**: Simplified application setup and configuration.
- **Database Integration**: Configured with a relational database using JPA and Hibernate.
- **Liquibase**: Database versioning and migrations.
- **Email Service**: SMTP-based email notifications.
- **OAuth2 Authentication**: Google OAuth2 integration for secure login.
- **Custom Features**: Configurable time zones, admin email, and Gemini API integration.

## Project Structure

src/ ├── main/ │ ├── java/ │ │ └── com/ # Application source code │ └── resources/ │ ├── application.properties # Application configuration │ ├── db/ # Liquibase changelogs │ └── META-INF/ # Additional resources ├── test/ │ └── java/ # Unit and integration tests target/ ├── classes/ # Compiled application files └── test-classes/ # Compiled test files


## Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- A relational database (e.g., PostgreSQL, MySQL)
- Environment variables for sensitive configurations

## Configuration

The application uses environment variables for sensitive data. Below are the required variables:

| Variable Name              | Description                          |
|----------------------------|--------------------------------------|
| `DATABASE_URL`             | Database connection URL             |
| `DATABASE_USERNAME`        | Database username                   |
| `DATABASE_PASSWORD`        | Database password                   |
| `SPRING_MAIL_USERNAME`     | Email service username              |
| `SPRING_MAIL_PASSWORD`     | Email service password              |
| `GOOGLE_CLIENT_ID`         | Google OAuth2 client ID             |
| `GOOGLE_CLIENT_SECRET`     | Google OAuth2 client secret         |
| `ADMIN_EMAIL`              | Admin email address                 |
| `GEMINI_API_KEY`           | API key for Gemini integration      |

These variables should be set in your environment or in a `.env` file.

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/Sharath198118/Hackathon-Backend.git
   cd Hackathon-Backend
2. Build the project using Maven:
  ```bash
  mvn clean install

3. Run the application:
  ```bash
  mvn spring-boot:run

4. The application will be available at http://localhost:8080.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contributing
Contributions are welcome! Please fork the repository and submit a pull request.
