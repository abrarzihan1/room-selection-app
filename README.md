# TeachSpace - Classroom and Facility Booking System

TeachSpace is a web-based application designed to streamline the process of reserving classrooms and facilities for academic purposes. By centralizing and automating the booking process, it enhances efficiency and convenience for professors.

## Features

- **Easy Room Booking**: Professors can easily find and book available classrooms or facilities in real-time.
- **Upcoming & Past Bookings**: View and manage both upcoming and past bookings.
- **Responsive Design**: Works seamlessly on desktop and mobile devices for maximum accessibility.

## Tech Stack

- **Frontend**: React.js, CSS, HTML
- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **API**: RESTful API using Spring Boot for communication with the frontend
- **Authentication**: JWT (JSON Web Tokens) for secure login and session management

### Configuration

- Make sure the backend is running on a port different from the frontend (`http://localhost:8082` is the default for Spring Boot).
- Update the API URLs in the frontend code to match your backend.

## Usage

Once the application is running, users can:

- **Login**: Log in as a teacher using the credentials set in the system.
- **Book Rooms**: Reserve available rooms for lectures or other academic purposes.
- **Manage Bookings**: View and delete upcoming or past bookings.

### Authentication

The application uses JWT (JSON Web Tokens) for authentication. After logging in, a token is issued, which must be included in the headers of subsequent API requests.

## Screenshots
