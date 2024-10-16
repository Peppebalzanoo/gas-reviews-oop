# Gas Reviews 🔥🇮🇹 
This project, developed as part of an Object-Oriented Programming course, is a simple desktop application in Java utilizing the Swing library. Inspired by the famous TripAdvisor platform, this software is tailored specifically for Italy. 🇮🇹 🍝🏛️

## Features

- **User Registration** 👤: Users can sign up to create their profiles.
- **Review Submission** 📝: Users can submit reviews for various establishments, including restaurants, accommodations, and attractions. Each review can have a rating from ⭐ 1 to ⭐⭐⭐⭐⭐ and a descriptive comment.
- **Admin Approval** ✅: Reviews are subject to approval by an administrator before they go live.

### Admin Dashboard

An exclusive area for administrators allows them to:

- Review and approve or reject user-submitted reviews. 🔍

### User Dashboard

Users can check the status of their reviews in three categories:

- **Approved Reviews** ✔️: Published reviews visible to all.
- **Rejected Reviews** ❌: Reviews that didn't meet the criteria.
- **Pending Reviews** ⏳: Reviews awaiting approval.

## Technical Details

- **Database** 💾: The project uses PostgreSQL for data storage, ensuring efficient management of user and review data.
- **Design Pattern** 🔧: The Data Access Object (DAO) pattern is employed to separate data access logic from the business logic. This enhances modularity and maintainability, making it easier to manage database interactions.

### Swing Library

- **User Interface** 🎨: The application leverages Java Swing, a powerful toolkit for building graphical user interfaces (GUIs). Swing provides a rich set of components, such as buttons, text fields, and tables, allowing for the creation of a responsive and intuitive user interface.
- **Event Handling** ⚙️: The project incorporates event-driven programming, where user actions trigger specific responses in the application. This is facilitated through listeners and action events, making the application interactive and user-friendly.

### Object-Oriented Programming Concepts

- **Encapsulation** 🔒: The project employs encapsulation by grouping related properties and methods into classes, which enhances code organization and security.
- **Inheritance** 🧬: Common functionalities are shared across different classes, promoting code reusability and a cleaner architecture.
- **Polymorphism** 🔄: The ability to redefine methods in derived classes allows for flexible and dynamic behavior in the application.
