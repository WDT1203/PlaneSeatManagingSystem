# Plane Seat Managing System

## Overview

The Plane Seat Managing System is a Java-based command-line application designed to manage the booking, cancellation, and tracking of seats on a plane. It allows users to reserve seats, cancel bookings, validate customer information, view the seating plan, and print ticket details. Each ticket is saved as a text file containing passenger and booking information.

## Features

- **Seat Booking:**  
  Book available seats by selecting a row (A-D) and seat number. Seats are priced based on location.
- **Customer Information:**  
  Collects and validates passenger first name, surname, and email address.
- **Seating Plan Display:**  
  Visualizes the seating arrangement, showing reserved (X) and available (O) seats.
- **Cancel Reservations:**  
  Allows users to cancel previously reserved seats.
- **Ticket Information:**  
  Displays all reserved tickets and the total price.
- **Ticket Storage:**  
  Each ticket is saved as a `.txt` file including seat, price, and passenger details.
- **Search Functionality:**  
  Search and display ticket information for any reserved seat.
- **Input Validation:**  
  Validates names (letters and spaces only) and emails using regular expressions.

## Getting Started

### Prerequisites

- Java 8 or higher
- A terminal or command prompt

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/WDT1203/PlaneSeatManagingSystem.git
   cd PlaneSeatManagingSystem
   ```

2. **Compile the Java source files:**
   ```bash
   javac src/*.java
   ```

3. **Run the application:**
   ```bash
   java -cp src PlaneManagement
   ```

4. **Follow the on-screen menu to:**
   - Book a seat
   - Cancel a reservation
   - View the seating plan
   - Print all reserved ticket information
   - Search for a ticket

## File Structure

- `src/PlaneManagement.java` - Main logic and menu-driven interface.
- `src/Ticket.java` - Represents a ticket; includes methods for saving ticket details to file.
- `src/Person.java` - Stores and validates passenger details.
- `A4.txt`, `A5.txt`, ... - Example of ticket files generated for each reserved seat.

## Example Ticket File

```
Seat: A5
Price: 200.0
Full Name: John Doe
Email: johndoe@example.com
```

## Customization

- **Seat Pricing:**  
  Seat prices are determined by seat location and can be adjusted in the booking logic.
- **Plane Layout:**  
  The size and arrangement of the seating can be modified in the `ticketArray` and `seatArray` structures.

## License

This project is for educational purposes.

---

*For questions, suggestions, or contributions, please open an issue or contact the repository owner.*
