import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EventRegistrationSystem {

    // --- Inner Classes for OOP Concepts ---

    // Student Class: Encapsulates student details
    static class Student {
        private String name;
        private String rollNumber;
        private String branch;
        private int year;

        public Student(String name, String rollNumber, String branch, int year) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.branch = branch;
            this.year = year;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }
        // Roll number usually doesn't change, but if needed we can add setter

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll No: " + rollNumber + ", Branch: " + branch + ", Year: " + year;
        }
    }

    // Event Class: Encapsulates event details
    static class Event {
        private String eventName;
        private String eventDate;

        public Event(String eventName, String eventDate) {
            this.eventName = eventName;
            this.eventDate = eventDate;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        @Override
        public String toString() {
            return "Event: " + eventName + ", Date: " + eventDate;
        }
    }

    // Registration Class: Links Student and Event
    static class Registration {
        private Student student;
        private Event event;

        public Registration(Student student, Event event) {
            this.student = student;
            this.event = event;
        }

        public Student getStudent() {
            return student;
        }

        public Event getEvent() {
            return event;
        }

        public void displayInfo() {
            System.out.println("--------------------------------------------------");
            System.out.println(student.toString());
            System.out.println(event.toString());
            System.out.println("--------------------------------------------------");
        }
    }

    // --- Main System Logic ---

    private static ArrayList<Registration> registrations = new ArrayList<>(); // ArrayList to store registrations
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== College Event Registration System ===");
            System.out.println("1. Register Student");
            System.out.println("2. View All Registrations");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Delete Registration");
            System.out.println("5. Edit Registration");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                scanner.nextLine(); // Clear invalid input
            }

            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    viewRegistrations();
                    break;
                case 3:
                    searchByRollNumber();
                    break;
                case 4:
                    deleteRegistration();
                    break;
                case 5:
                    editRegistration();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerStudent() {
        System.out.println("\n--- Enter Student Details ---");

        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty())
                break;
            System.out.println("Name cannot be empty.");
        }

        String rollNumber;
        while (true) {
            System.out.print("Enter Roll Number: ");
            rollNumber = scanner.nextLine().trim();
            if (rollNumber.isEmpty()) {
                System.out.println("Roll Number cannot be empty.");
                continue;
            }
            if (isDuplicateRollNumber(rollNumber)) {
                System.out.println("Roll Number already registered! Please use a unique Roll Number.");
            } else {
                break;
            }
        }

        String branch;
        while (true) {
            System.out.print("Enter Branch: ");
            branch = scanner.nextLine().trim();
            if (!branch.isEmpty())
                break;
            System.out.println("Branch cannot be empty.");
        }

        int year = 0;
        while (true) {
            System.out.print("Enter Year (1-4): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (year >= 1 && year <= 4)
                    break;
                System.out.println("Invalid year. Please enter a value between 1 and 4.");
            } else {
                scanner.nextLine(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.println("\n--- Enter Event Details ---");
        String eventName;
        while (true) {
            System.out.print("Enter Event Name: ");
            eventName = scanner.nextLine().trim();
            if (!eventName.isEmpty())
                break;
            System.out.println("Event Name cannot be empty.");
        }

        String eventDate;
        while (true) {
            System.out.print("Enter Event Date (DD/MM/YYYY): ");
            eventDate = scanner.nextLine().trim();
            if (isValidDate(eventDate))
                break;
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
        }

        // Create Objects
        Student student = new Student(name, rollNumber, branch, year);
        Event event = new Event(eventName, eventDate);
        Registration registration = new Registration(student, event);

        // Store in ArrayList
        registrations.add(registration);

        System.out.println("Registration Successful!");
    }

    private static void viewRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("\nNo registrations found.");
        } else {
            System.out.println("\n=== All Registrations ===");
            for (Registration reg : registrations) {
                reg.displayInfo();
            }
        }
    }

    private static void searchByRollNumber() {
        if (registrations.isEmpty()) {
            System.out.println("\nNo registrations to search.");
            return;
        }

        System.out.print("Enter Roll Number to Search: ");
        String searchRoll = scanner.nextLine().trim();
        boolean found = false;

        for (Registration reg : registrations) {
            if (reg.getStudent().getRollNumber().equalsIgnoreCase(searchRoll)) {
                System.out.println("\n--- Registration Found ---");
                reg.displayInfo();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No registration found with Roll Number: " + searchRoll);
        }
    }

    private static void deleteRegistration() {
        if (registrations.isEmpty()) {
            System.out.println("\nNo registrations to delete.");
            return;
        }

        System.out.print("Enter Roll Number to Delete: ");
        String deleteRoll = scanner.nextLine().trim();
        boolean removed = false;

        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getStudent().getRollNumber().equalsIgnoreCase(deleteRoll)) {
                registrations.remove(i);
                removed = true;
                System.out.println("Registration deleted successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("No registration found with Roll Number: " + deleteRoll);
        }
    }

    private static void editRegistration() {
        if (registrations.isEmpty()) {
            System.out.println("\nNo registrations to edit.");
            return;
        }

        System.out.print("Enter Roll Number to Edit: ");
        String editRoll = scanner.nextLine().trim();
        Registration targetReg = null;

        for (Registration reg : registrations) {
            if (reg.getStudent().getRollNumber().equalsIgnoreCase(editRoll)) {
                targetReg = reg;
                break;
            }
        }

        if (targetReg == null) {
            System.out.println("No registration found with Roll Number: " + editRoll);
            return;
        }

        System.out.println("\n--- Current Details ---");
        targetReg.displayInfo();

        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Branch");
        System.out.println("3. Year");
        System.out.println("4. Event Name");
        System.out.println("5. Event Date");
        System.out.println("6. Cancel");
        System.out.print("Enter choice: ");

        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } else {
            scanner.nextLine();
        }

        switch (choice) {
            case 1:
                System.out.print("Enter New Name: ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) {
                    targetReg.getStudent().setName(newName);
                    System.out.println("Name updated.");
                } else {
                    System.out.println("Invalid name. Update cancelled.");
                }
                break;
            case 2:
                System.out.print("Enter New Branch: ");
                String newBranch = scanner.nextLine().trim();
                if (!newBranch.isEmpty()) {
                    targetReg.getStudent().setBranch(newBranch);
                    System.out.println("Branch updated.");
                } else {
                    System.out.println("Invalid branch. Update cancelled.");
                }
                break;
            case 3:
                System.out.print("Enter New Year (1-4): ");
                if (scanner.hasNextInt()) {
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    if (newYear >= 1 && newYear <= 4) {
                        targetReg.getStudent().setYear(newYear);
                        System.out.println("Year updated.");
                    } else {
                        System.out.println("Invalid year. Update cancelled.");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Invalid input. Update cancelled.");
                }
                break;
            case 4:
                System.out.print("Enter New Event Name: ");
                String newEventName = scanner.nextLine().trim();
                if (!newEventName.isEmpty()) {
                    targetReg.getEvent().setEventName(newEventName);
                    System.out.println("Event Name updated.");
                } else {
                    System.out.println("Invalid event name. Update cancelled.");
                }
                break;
            case 5:
                System.out.print("Enter New Event Date (DD/MM/YYYY): ");
                String newEventDate = scanner.nextLine().trim();
                if (isValidDate(newEventDate)) {
                    targetReg.getEvent().setEventDate(newEventDate);
                    System.out.println("Event Date updated.");
                } else {
                    System.out.println("Invalid date format. Update cancelled.");
                }
                break;
            case 6:
                System.out.println("Edit cancelled.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Helper method to check for duplicate roll numbers
    private static boolean isDuplicateRollNumber(String rollNumber) {
        for (Registration reg : registrations) {
            if (reg.getStudent().getRollNumber().equalsIgnoreCase(rollNumber)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to validate date format (DD/MM/YYYY)
    private static boolean isValidDate(String date) {
        // Simple regex for DD/MM/YYYY
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
        return Pattern.matches(regex, date);
    }
}