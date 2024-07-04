# MDD (Monde de Dev)

## Description

MDD (Monde de Dev) is a social network dedicated to developers. The goal of this project is to facilitate networking among developers, encourage collaborations, and serve as a pool for recruitment. This repository contains both the frontend and backend code for the Minimum Viable Product (MVP) of the application.

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Installation](#installation)
  - [Database](#database)
  - [Frontend](#frontend)
  - [Backend](#backend)

## Technologies

- **Frontend:**
  - Language: TypeScript
  - Framework: Angular 14.1.3
- **Backend:**
  - Language: Java
  - Framework: Spring Boot
- **Database:**
  - MySQL
- **Version Control:**
  - Git & GitHub

## Architecture

The application follows a client-server architecture:
- **Frontend:** Angular application that communicates with the backend via RESTful API.
- **Backend:** Spring Boot application providing RESTful services.
- **Database:** MySQL database storing user data, posts, topics, and comments.

## Installation

### Prerequisites

Ensure you have the following software installed:
- Node.js (version 14.x or later)
- npm (Node Package Manager, version 6.x or later)
- Java Development Kit (JDK, version 11 or later)
- Maven (version 3.6 or later)
- MySQL (version 8.x or later)

### Database

1. **Install MySQL:**

    Follow the instructions on the [MySQL website](https://dev.mysql.com/downloads/mysql/) to install MySQL on your machine.

2. **Create the database:**

    Open a terminal and run the following commands:

    ```
    mysql -u root -p
    CREATE DATABASE mdd_db;
    ```

    Replace `root` with your MySQL username if different.


3. **Creating Database User**
  Create a new user and grant all privileges on the created database

  ```
  CREATE USER 'mddUser'@'localhost' IDENTIFIED BY 'your_password';
  GRANT ALL PRIVILEGES ON mdd_db.* TO 'mddUser'@'localhost';
  FLUSH PRIVILEGES;
  ```

### Frontend

For instructions on setting up the frontend, please refer to the [frontend README](./front/README.md).

### Backend

For instructions on setting up the backend, please refer to the [backend README](./back/readme.md).

## Usage

You can use tools like Postman or a web browser to interact with the API endpoints provided by the backend. Ensure the frontend and backend servers are running, then navigate to `http://localhost:4200` to use the application.

## Features

### User Management

- Register and log in.
- Persistent sessions.
- Profile management (view and edit profile, change username, email).
- Logout.

### Subscription Management

- Browse all topics.
- Subscribe to topics.
- Unsubscribe from topics.

### Post Management

- View feed sorted chronologically.
- Create new posts (auto-fill author and date).
- View post details (title, content, author, date, comments).
- Add comments to posts (auto-fill author and date).
