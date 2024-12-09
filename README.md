# TeachSpace - Classroom and Facility Booking System

TeachSpace is an intuitive web-based platform designed to simplify and optimize the reservation of classrooms and academic facilities. By centralizing and automating the booking process, it empowers professors with a seamless, efficient, and user-friendly experience, ensuring convenience and productivity.

## Features:
- **Effortless Room Booking**: Professors can quickly search for and reserve available classrooms or facilities in real time with ease.
- **Booking Management Made Simple**: Access, review, and manage both upcoming and past reservations at a glance.
- **Fully Responsive Design**: Enjoy a seamless user experience across desktop and mobile devices, ensuring maximum accessibility anytime, anywhere.

## Tech Stack:
- **Frontend**: React.js, CSS, HTML
- **Backend**: Java, Spring Boot
- **Database**: MySQL,H2Database
- **API**: RESTful API using Spring Boot for communication with the frontend
- **Authentication**: JWT (JSON Web Tokens) for secure login and session management
- **Testing**: JUnit, AssertJ, and Mockito are used for unit testing, improving assertions, and mocking dependencies to ensure backend functionality and reliability.

### Configuration:
- Make sure the backend is running on a port different from the frontend (`http://localhost:8082` is the default for Spring Boot).
- Update the API URLs in the frontend code to match your backend.

## Usage:
Once the application is running, users can:

- **Login**: Log in as a teacher using the credentials set in the system.
- **Book Rooms**: Reserve available rooms for lectures or other academic purposes.
- **Manage Bookings**: View and delete upcoming or past bookings.

### Authentication:

The application uses JWT (JSON Web Tokens) for authentication. After logging in, a token is issued, which must be included in the headers of subsequent API requests.

### DataBase:
The application uses MySQL as its database, integrated seamlessly with Spring Boot through JPA (Java Persistence API) for Object-Relational Mapping (ORM). This allows the system to efficiently map Java objects to database tables, ensuring smooth data operations such as retrieving, updating, and deleting records.

The architecture follows a layered pattern, structured into Controller, Service, Repository, and Model layers:
- **Controller**: Handles HTTP requests and returns appropriate responses.
- **Service**: Contains business logic, orchestrating the flow of data between the controller and repository layers.
- **Repository**: Interfaces with the database using JPA, managing data persistence and queries.
- **Model**: Defines the data structure and entity classes mapped to database tables.

In the future, the system is planned to transition to a cloud-based database for improved scalability, reliability, and performance, enabling more efficient data management and access in larger and distributed environments.

### Testing:

For testing, the application uses an H2 database, which is an in-memory database that provides an efficient and lightweight solution for small-scale testing environments. H2 is ideal for unit and integration tests, as it ensures that tests are executed in a clean, isolated environment without needing a full-scale database setup.

The testing strategy consists of unit testing, integration testing, and layered testing:

- **Unit Testing**: Focuses on testing individual components (e.g., services, repositories, controllers) in isolation. JUnit is used for writing these tests.
- **Integration Testing**: Ensures that different parts of the system work together as expected. This includes testing the interaction between the controller, service, and repository layers using H2 as the database.
- **Layered Testing**: Each layer of the application (Controller, Service, Repository) is tested separately and in combination, ensuring that data flows correctly through the system and that the business logic behaves as intended.
  
Tools like JUnit, Mockito, and AssertJ are used to test the functionality:

- **JUnit**: Provides the framework for writing and running the tests.
- **Mockito**: Used for mocking dependencies in unit tests, allowing testing of components in isolation.
- **AssertJ**: Improves test readability with fluent assertions, making the tests more descriptive and easier to maintain.
  
This layered approach ensures that all aspects of the application are thoroughly tested and that changes to one layer don’t introduce errors in other layers.

## Frontend:

### Features:

- **Room Search**: View available time and date for particular rooms.
- **Room Booking**: Teachers can book rooms and update bookings.
- **Booking Management**: View and cancel existing bookings.
- **Authentication**: Secure user login and signup functionality.

### Tech Stack:

- **React**: Frontend framework for building the user interface.
- **Axios**: For making HTTP requests to the backend API.
- **React Router**: For managing navigation within the app.

### Prerequisites:
Before running the project, make sure you have Node.js installed.

## Screenshots:
![Screenshot_5-12-2024_194041_localhost](https://github.com/user-attachments/assets/b95bb32b-92cd-44a4-9de3-948172259776)
![a64f5ada-f362-4ae8-aa6c-8d542455c18f](https://github.com/user-attachments/assets/bb6d7b46-8a98-4e3e-9c68-9eab5ca42d0f)
![Screenshot_5-12-2024_19407_localhost](https://github.com/user-attachments/assets/a7563aeb-1de1-407e-81b7-ec197c33f4d8)
![da4da09a-4972-4485-be78-50fcaf424709](https://github.com/user-attachments/assets/28b4ec32-6552-4f16-8010-cb79a2efc971)
![c8597ca5-e4e7-4dcc-abbb-751c345e6386](https://github.com/user-attachments/assets/6702d217-7a5e-4728-bf52-80a3eabcd50c)





Feel free to reach out if you encounter any issues or have suggestions for improvements. Happy coding! 🚀

