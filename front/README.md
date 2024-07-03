# MDD Frontend

## Description

MDD (Monde de Dev) is a social network dedicated to developers. The goal of this project is to facilitate networking among developers, encourage collaborations, and serve as a pool for recruitment. This repository contains the frontend code for the Minimum Viable Product (MVP) of the application, developed with Angular.

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)

## Technologies

- **Language:** TypeScript
- **Framework:** Angular
- **Version Control:** Git & GitHub

## Installation

To run this project locally, follow these steps:

1. **Clone the repository:**

    ```
    git clone https://github.com/tibrook/Developpez-une-application-full-stack-complete
    cd Developpez-une-application-full-stack-complete
    ```

2. **Navigate to the frontend directory:**

    ```
    cd front
    ```

3. **Install dependencies:**

    ```
    npm install
    ```

4. **Start the development server:**

    ```
    ng serve
    ```

    The application should now be running at `http://localhost:4200`.

## Usage

### Home (Authentication) Page

- Access the login and registration pages quickly.

### Registration Page

- Sign up with an email, password, and username.

### Login Page

- Log in with your email and password.

### Main Features (Post-Login)

- **Feed:** View your feed with posts from subscribed topics.
- **Topics Page:** Browse and subscribe to various topics.
- **Post View:** Read posts and add comments.
- **Create Post Form:** Create new posts with a title, content, and associated topic.
- **Profile Page:** View and edit your profile information and manage subscriptions.

## Features

### User Management

- Register and log in.
- Persistent sessions between sessions.
- Profile management (view and edit profile, change password).
- Logout.

### Subscription Management

- Browse all topics.
- Subscribe to topics.
- Unsubscribe from topics via the profile page.

### Post Management

- View your feed sorted chronologically.
- Create new posts (auto-fill author and date).
- View post details (title, content, author, date, comments).
- Add comments to posts (auto-fill author and date).
