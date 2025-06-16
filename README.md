# 📝 TODO App

A simple and responsive TODO web application built with Spring Boot (backend) and HTML/CSS/JS (frontend). The app supports user registration, login, and personal task management. It also includes an **Admin Panel** for managing users.

🚀 **Live Demo:**  
- User App: [https://todo-app-4lw2.onrender.com/](https://todo-app-4lw2.onrender.com/)  
- Admin Panel: [https://todo-app-4lw2.onrender.com/admin.html](https://todo-app-4lw2.onrender.com/admin.html)

---

## About Render Sleep Behavior

This app is deployed on [Render](https://render.com/) free tier, which **puts the backend to sleep after a period of inactivity**.

- When the app is asleep, the **first user request after waking up will take longer** (usually around **30-50 seconds**) because the backend needs to start again.
- Users should expect a delay on the very first request after some idle time.
- Subsequent requests will be faster as the backend stays awake for a while.

---

## ✨ Features

### ✅ User Features
- Register and login (via **Basic Authentication**)
- Add, view, update, and delete TODO items
- Each user has their own isolated task list

### 🔐 Admin Features
- Admin login via a separate panel
- View and delete all registered users
- Signup button is disabled in admin panel to indicate restricted access

---

## 🔐 Authentication

This app uses **HTTP Basic Authentication**:
- Username and password are sent with every request via the `Authorization` header.
- No server-side session is created (stateless).
- Make sure to serve the app over **HTTPS** in production to secure credentials.

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, JDBC, PostgreSQL
- **Frontend:** HTML, CSS, JavaScript (vanilla)
- **Authentication:** Basic Auth (Spring Security)
- **Deployment:** Docker, Render.com

---

## 🗄️ Database Setup

Before running the backend, you **must** execute the database schema file located in the backend folder:

```bash
psql -U your_db_user -d your_db_name -f backend/schema.sql
```

This will create all required tables and constraints in PostgreSQL.

---

## ⚙️ Backend Database Configuration

The backend connects to a PostgreSQL database using the properties defined in `src/main/resources/application.properties` (or environment variables):

```properties
spring.datasource.url=jdbc:postgresql://<host>:5432/<database>
spring.datasource.username=<db_user>
spring.datasource.password=<db_password>
spring.datasource.driver-class-name=org.postgresql.Driver
```

---

## ⚙️ Frontend API Base URL

In both frontend static files (`index.html` and `admin.html`), update the `API` variable to point to the backend when running locally:

```js
const API = 'http://localhost:8080';
```

This ensures all API calls go to the correct backend URL when the frontend is served locally on port `3000` (see below).

Example usage:

```js
fetch(`${API}/api/todo`, {
  method: 'GET',
  headers: {
    'Authorization': 'Basic ...'
  }
});
```

---

## ▶️ Run Frontend Locally

You can serve the frontend using Python’s built-in HTTP server:

```bash
cd frontend
python3 -m http.server 3000
```

Then open: [http://localhost:3000](http://localhost:3000)

---

## ▶️ Run Backend

Make sure PostgreSQL is running and configured, then run the backend:

### Using Maven:

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```

### Or using Docker:

```bash
docker build -t todoapp .
docker run -p 8080:8080 todoapp
```

Then backend is available at: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Sample Admin Credentials

- **Username:** ``
- **Password:** ``

---

## 🙌 Author

Made with ❤️ by [Maruf Hasan](https://github.com/maruffhasan)
