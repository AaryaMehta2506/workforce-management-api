# Workforce Management API 🚀

This project is a Workforce Management API for managing tasks and staff members — built as part of a Backend Engineer coding challenge.

---

## 🧩 Features

- ✅ Create, assign, and track tasks  
- ✅ Reassign tasks (automatically cancels old one)  
- ✅ Filter tasks by date and status  
- ✅ Set and update task priority (HIGH, MEDIUM, LOW)  
- ✅ Smart daily task view  
- ✅ Add comments and view full activity history  

---

## 🛠 Tech Stack

- Java 17  
- Spring Boot 3.0.4  
- Gradle  
- Lombok  
- MapStruct  
- In-memory storage (no DB required)  

---
src/main/java/com/yourcompany/workforcemgmt/
├── controller/ # REST Controllers
├── service/ # Business logic layer
├── model/ # Data models (Task, Staff, etc.)
├── dto/ # Data Transfer Objects
└── WorkforcemgmtApplication.java


---

## 🚀 Running the App

```bash
./gradlew bootRun
```
Or run directly from your IDE (IntelliJ, VSCode, etc.).

---

## 🔗 API Endpoints
| Method | Endpoint                                       | Description                            |
| ------ | ---------------------------------------------- | -------------------------------------- |
| POST   | `/tasks`                                       | Create a new task                      |
| GET    | `/tasks/range?start=YYYY-MM-DD&end=YYYY-MM-DD` | Get tasks for date range               |
| POST   | `/tasks/{id}/priority?priority=HIGH`           | Update task priority                   |
| GET    | `/tasks/priority/HIGH`                         | Get tasks by priority                  |
| POST   | `/tasks/{id}/reassign?staffId=2`               | Reassign a task                        |
| POST   | `/tasks/{id}/comment`                          | Add comment to a task                  |
| GET    | `/tasks/{id}`                                  | Get task details with comments/history |

---

## 🤝 Contributing
Contributions are welcome!
Feel free to fork the repository, improve the game, and open a pull request. Let's grow this classic game together!

## 📄 License
This project is licensed under the [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](./LICENSE)

## 👩‍💻 Author
**Aarya Mehta**  
🔗 [GitHub Profile](https://github.com/AaryaMehta2506)


## 📁 Project Structure

