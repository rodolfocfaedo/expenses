```markdown

**Author**: Rodolfo Cunhasque Faedo
<a href="mailto:faedorodolfo.programming@gmail.com">
  <button style="background-color: #2DA44E; color: white; padding: 8px 16px; border: none; border-radius: 6px;">📧 Contact Me</button>
</a>

# Expenses Management API

A REST API for managing expenses with features like expense tracking, filtering by date/category, and total calculations.

## 🛠 Technologies

- **Java 21**
- **Spring Boot 3.4.5**
- **PostgreSQL**
- **MapStruct** (DTO mapping)
- **Lombok** (Boilerplate reduction)
- **Maven/Gradle** (Build tool)

## 🌟 Features

- Add new expenses
- Filter expenses by:
  - Date
  - Date range (with sorting)
  - Category (EDUCATION, HEALTH, FOOD, OTHERS)
- Calculate daily/weekly totals
- Delete expenses

## 🚀 Getting Started

### Prerequisites
- PostgreSQL installed
- Java 21 JDK
- Maven/Gradle

### Setup

1. **Database Configuration**:
   ```sql
   CREATE DATABASE expenses_db;
   ```
2. **Update `application.properties`**:
   ```properties
   spring.datasource.username=your_postgres_username
   spring.datasource.password=your_postgres_password
   ```

### Run the Application
```bash
./gradlew bootRun
# or
mvn spring-boot:run
```

## 📚 API Endpoints

| Method | Endpoint               | Description                          |
|--------|------------------------|--------------------------------------|
| POST   | `/expenses`            | Add a new expense                    |
| GET    | `/expenses?date=...`   | Get expenses by date                 |
| GET    | `/expenses/sum?date=...` | Calculate daily total               |
| GET    | `/expenses/between?...` | Get expenses between dates          |
| GET    | `/expenses/category?...`| Filter by category                  |
| DELETE | `/expenses/{id}`       | Delete an expense by ID              |

## 📋 Example Requests

**Add Expense**:
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{
  "date": "15-05-2024",
  "amount": 199.99,
  "expenseCategory": "FOOD",
  "description": "Dinner at restaurant"
}' http://localhost:8080/expenses
```

**Get Daily Total**:
```bash
curl "http://localhost:8080/expenses/sum?date=15-05-2024"
```

## 📁 Project Structure
```
src/
├── main/
│   ├── java/com/rodolfocf/expenses/
│   │   ├── controller/      # API endpoints
│   │   ├── business/        # Service layer & DTOs
│   │   ├── infrastructure/  # DB entities & repositories
│   ├── resources/           # Config files
```

## ⚙️ Dependencies
Check `build.gradle` for full dependency list.
