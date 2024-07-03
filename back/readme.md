# MDD Backend

## Description

MDD (Monde de Dev) is a social network dedicated to developers. The backend of this project is built with Java and Spring Boot, providing a robust and secure API for the frontend to interact with.

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [API Endpoints](#api-endpoints)

## Technologies

- **Language:** Java
- **Framework:** Spring Boot
- **Version Control:** Git & GitHub

## Installation

To run this project locally, follow these steps:

1. **Clone the repository:**

    ```
    git clone https://github.com/tibrook/Developpez-une-application-full-stack-complete
    cd Developpez-une-application-full-stack-complete
    ```

2. **Navigate to the backend directory:**

    ```
    cd back
    ```

3. **Install dependencies and build the project:**

    ```
    ./mvnw install
    ```

4. **Configure the application:**

    Copy the sample environment configuration file :

    ```
    cp .env.example .env
    ```

    Update env variables to match with your environment: 

    ```
    DATABASE_URL=jdbc:mysql://localhost:3306/mdd_db
    DATABASE_USERNAME=mddUser
    DATABASE_PASSWORD=mddPassword
    ```

4. **Run the application:**

    ```
    ./mvnw spring-boot:run
    ```

    The application should now be running at `http://localhost:8082`.



## Usage

The backend provides a RESTful API for the frontend to interact with. You can use tools like Postman or cURL to test the endpoints.

You can also access endpoints defined in the Swagger UI at

```
http://localhost:8082/swagger-ui.html
```

This will provide you with an interactive API documentation where you can test out different endpoints.


## Features

### User Management

- Register and log in.
- Persistent sessions.
- Profile management (view and edit profile, change password).
- Logout.

### Subscription Management

- Browse all topics.
- Subscribe to topics.
- Unsubscribe from topics.

### Post Management

- View feed.
- Create new posts (auto-fill author and date).
- View post details (title, content, author, date, comments).
- Add comments to posts (auto-fill author and date).

## API Endpoints

### Auth Endpoints

- **POST** `/api/register`: Register a new user.
- **POST** `/api/login`: Log in an existing user.

### User Endpoints

- **GET** `/api/auth/profile/me`: Get the current user's profile.
- **PUT** `/api/auth/profile/update`: Update the current user's profile.

### Topics Endpoints

- **GET** `/api/auth/topics`: Get all topics.
- **POST** `/api/auth/{topicId}/subscribe`: Subscribe to a topic.
- **DELETE** `/api/auth/{topicId}/unsubscribe`: Unsubscribe from a topic.

### Post Endpoints

- **GET** `/api/auth/feed`: Get the user's feed.
- **POST** `/api/auth/posts/add`: Create a new post.
- **GET** `/api/auth/posts/{id}`: Get details of a specific post.
- **POST** `/api/auth/posts/{id}/comment`: Add a comment to a post.

