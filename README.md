# Budget Breakdown

A Java expense-management application for tracking, analyzing, and reporting personal expenses.

The project began as a command-line application and was expanded into a Spring Boot REST API backed by PostgreSQL. It demonstrates object-oriented programming, layered backend architecture, RESTful API development, database persistence, validation, testing, file I/O, and financial data processing.

## Features

### REST API

- Create expenses
- Retrieve all expenses
- Retrieve an expense by ID
- Update expenses
- Delete expenses
- Calculate total spending
- Filter expenses by category
- Search expenses by name
- Validate incoming expense data
- Return HTTP `400 Bad Request` for invalid input
- Return HTTP `404 Not Found` for missing expenses
- Persist expense data with PostgreSQL

### Command-Line Application

- Add, list, and delete expenses
- View total spending and category-based totals
- View category percentage breakdowns
- View monthly and weekly spending summaries
- Search expenses by name, category, or date range
- Sort expenses by date, amount, name, or category
- Save and load expenses using CSV files
- Generate TXT financial reports
- Format expense output for readability

## Backend Architecture

The REST API uses a layered architecture:

```text
HTTP Request
     |
     v
ExpenseController
     |
     v
ExpenseService
     |
     v
ExpenseRepository
     |
     v
Spring Data JPA / Hibernate
     |
     v
PostgreSQL
```

### Layers

- **Controller** — handles HTTP requests and REST endpoints
- **Service** — contains application and business logic
- **Repository** — provides database access through Spring Data JPA
- **Entity** — maps expense objects to PostgreSQL records

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get an expense by ID |
| POST | `/api/expenses` | Create a new expense |
| PUT | `/api/expenses/{id}` | Update an existing expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/expenses/total` | Get total spending |
| GET | `/api/expenses/category/{category}` | Filter expenses by category |
| GET | `/api/expenses/search?name={name}` | Search expenses by name |

## Example Request

Create an expense:

```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Coffee",
    "amount":5.50,
    "category":"Food",
    "date":"2026-08-16"
  }'
```

Example response:

```json
{
  "name": "Coffee",
  "amount": 5.5,
  "category": "Food",
  "date": "2026-08-16",
  "id": 1
}
```

## Technologies Used

- Java 21
- Spring Boot
- Spring Web / Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jakarta Validation
- Maven
- JUnit
- Mockito
- Apache Commons CSV
- Java Collections Framework
- Java Streams
- LocalDate
- Git & GitHub

## Project Structure

```text
src/
├── main/
│   ├── java/com/jonathan/budget/
│   │   ├── BudgetApplication.java
│   │   ├── Main.java
│   │   ├── Expense.java
│   │   ├── BudgetManager.java
│   │   ├── FileManager.java
│   │   ├── ReportGenerator.java
│   │   ├── controller/
│   │   │   └── ExpenseController.java
│   │   ├── service/
│   │   │   └── ExpenseService.java
│   │   ├── repository/
│   │   │   └── ExpenseRepository.java
│   │   └── exception/
│   │       ├── ExpenseNotFoundException.java
│   │       └── GlobalExceptionHandler.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/com/jonathan/budget/service/
        └── ExpenseServiceTest.java
```

## Database Setup

The API uses PostgreSQL.

Create a local database:

```bash
createdb budget_breakdown
```

Configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/budget_breakdown
spring.datasource.username=${DB_USERNAME}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set your PostgreSQL username before starting the application:

```bash
export DB_USERNAME=your_postgres_username
```

If your PostgreSQL installation requires a password, add:

```properties
spring.datasource.password=${DB_PASSWORD}
```

and set it with:

```bash
export DB_PASSWORD=your_postgres_password
```

Do not commit database passwords to the repository.

## Running the Application

Clone the repository:

```bash
git clone https://github.com/Jon504/budget-breakdown-cli.git
cd budget-breakdown-cli
```

Compile the project:

```bash
mvn compile
```

Start the Spring Boot API:

```bash
mvn spring-boot:run
```

The API will run at:

```text
http://localhost:8080
```

Example request:

```bash
curl http://localhost:8080/api/expenses
```

## Running Tests

Run the automated test suite with:

```bash
mvn test
```

The service layer includes JUnit and Mockito unit tests for:

- Retrieving expenses
- Calculating total spending
- Handling missing expense IDs

## Validation and Error Handling

Expense requests are validated before being processed.

Invalid data includes:

- Blank expense names
- Zero or negative amounts
- Blank categories
- Missing dates

Invalid requests return:

```text
HTTP 400 Bad Request
```

Requests for expenses that do not exist return:

```text
HTTP 404 Not Found
```

Example:

```bash
curl -i http://localhost:8080/api/expenses/9999
```

Response:

```text
HTTP/1.1 404

Expense not found with id: 9999
```

## Original CLI

The original command-line interface remains part of the project.

It supports:

- Expense creation and deletion
- Spending summaries
- Category analysis
- Searching and sorting
- CSV persistence
- TXT report generation

The project demonstrates its progression from a standalone Java CLI application into a database-backed REST API.

## Author

Jonathan Anderson

GitHub: https://github.com/Jon504

LinkedIn: https://linkedin.com/in/jonathan26