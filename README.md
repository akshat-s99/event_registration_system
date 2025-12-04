# Event Registration System

A simple console-based Event Registration System written in Java.

## Features
- **Register Students**: Add students with Name, Roll No, Branch, Year, and Event details.
- **View Registrations**: List all registered students.
- **Search**: Find registrations by Roll Number.
- **Delete**: Remove a registration by Roll Number.
- **Edit**: Update details of an existing registration
- **Data Persistence**: Currently runs in-memory (data is lost when program exits).

## Enhancements
- Uses `ArrayList` for dynamic storage.
- Validates inputs (non-empty fields, valid dates, unique roll numbers).

## How to Run
1. Compile the code:
   ```bash
   javac -d . src/EventRegistrationSystem.java
   ```
2. Run the application:
   ```bash
   java EventRegistrationSystem
   ```
