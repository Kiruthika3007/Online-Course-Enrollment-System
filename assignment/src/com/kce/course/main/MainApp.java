package com.kce.course.main;

import com.kce.course.model.*;
import com.kce.course.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApp {

    private static final CourseService courseService = new CourseService();
    private static final StudentService studentService = new StudentService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": handleAddCourse(); break;
                case "2": handleAddStudent(); break;
                case "3": handleEnroll(); break;
                case "4": handleMakePayment(); break;
                case "5": handleDisplayCourses(); break;
                case "6": running = false; break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Exiting. Goodbye!");
    }

    private static void showMenu() {
        System.out.println("\n Online Course Enrollment System");
        System.out.println("1. Add Course");
        System.out.println("2. Add Student");
        System.out.println("3. Enroll in Course");
        System.out.println("4. Make Payment");
        System.out.println("5. Display Courses");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    private static void handleAddCourse() {
        System.out.print("Course ID: ");
        String id = scanner.nextLine().trim();
        if (courseService.exists(id)) {
            System.out.println("Course with this ID already exists.");
            return;
        }
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Instructor ID: ");
        String insId = scanner.nextLine().trim();
        System.out.print("Instructor Name: ");
        String insName = scanner.nextLine().trim();
        System.out.print("Instructor Email: ");
        String insEmail = scanner.nextLine().trim();
        System.out.print("Capacity (number of seats): ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Fee (e.g., 1499.50): ");
        BigDecimal fee = new BigDecimal(scanner.nextLine().trim());

        Instructor instructor = new Instructor(insId, insName, insEmail);
        Course c = new Course(id, title, instructor, capacity, fee);
        courseService.addCourse(c);
        System.out.println("Course added: " + c);
    }

    private static void handleAddStudent() {
        System.out.print("Student ID: ");
        String sid = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Is Premium? (y/n): ");
        String isPremium = scanner.nextLine().trim().toLowerCase();
        if (isPremium.equals("y")) {
            System.out.print("Discount percent (e.g., 10 for 10%): ");
            double dp = Double.parseDouble(scanner.nextLine().trim());
            PremiumStudent ps = new PremiumStudent(sid, name, email, dp);
            studentService.addStudent(ps);
            System.out.println("Premium student added: " + ps);
        } else {
            Student s = new Student(sid, name, email);
            studentService.addStudent(s);
            System.out.println("Student added: " + s);
        }
    }

    private static void handleEnroll() {
        System.out.print("Enter Student ID: ");
        String sid = scanner.nextLine().trim();
        Optional<Student> optS = studentService.findById(sid);
        if (optS.isEmpty()) {
            System.out.println("Student not found. Add student first.");
            return;
        }
        Student s = optS.get();

        System.out.print("Enter Course ID: ");
        String cid = scanner.nextLine().trim();
        Optional<Course> optC = courseService.findById(cid);
        if (optC.isEmpty()) {
            System.out.println("Course not found.");
            return;
        }
        Course c = optC.get();

        if (!c.isAvailable()) {
            System.out.println("N5o available seats.");
            return;
        }

        Enrollment e = enrollmentService.createEnrollment(s, c);
        System.out.println("Enrollment created with ID: " + e.getEnrollmentId());
        System.out.println("Status: " + e.getStatus());
        BigDecimal fee = s.getFeeForCourse(c);
        System.out.println("Fee for this student: " + fee);
        System.out.println("You must make payment to confirm enrollment (use menu option 4).");
    }

    private static void handleMakePayment() {
        System.out.print("Enter Enrollment ID: ");
        String eid = scanner.nextLine().trim();

        Enrollment enrollment = enrollmentService.listEnrollments()
                .stream()
                .filter(en -> en.getEnrollmentId().equals(eid))
                .findFirst()
                .orElse(null);
        if (enrollment == null) {
            System.out.println("Enrollment not found.");
            return;
        }

        if (enrollment.getStatus() == Enrollment.Status.CONFIRMED) {
            System.out.println("Already confirmed.");
            return;
        }

        BigDecimal expected = enrollment.getStudent().getFeeForCourse(enrollment.getCourse());
        System.out.println("Expected amount: " + expected);
        System.out.print("Enter payment amount: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Payment method (Card/UPI/Cash): ");
        String method = scanner.nextLine().trim();

        System.out.print("Simulate successful payment? (y/n): ");
        boolean success = scanner.nextLine().trim().equalsIgnoreCase("y");

        boolean ok = enrollmentService.makePaymentAndConfirm(enrollment, amount, method, success);
        if (!ok) {
            System.out.println("Payment failed or insufficient or no seats. Enrollment remains PENDING.");
        } else {
            System.out.println("Payment successful and enrollment CONFIRMED!");
            enrollmentService.getReceiptForEnrollment(enrollment).ifPresent(r -> {
                System.out.println(" Receipt ");
                System.out.println(r);
            });
        }
    }

    private static void handleDisplayCourses() {
        List<Course> list = courseService.listCourses();
        if (list.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("Courses:");
        for (Course c : list) {
            System.out.println(c);
        }
    }

    private static void seedSampleData() {
        Instructor i1 = new Instructor("I001", "Dr. Kavitha", "kavitha@example.com");
        Instructor i2 = new Instructor("I002", "Prof. Raj", "raj@example.com");
        Course c1 = new Course("C101", "Java Programming", i1, 3, new BigDecimal("2499.00"));
        Course c2 = new Course("C102", "Data Structures", i2, 2, new BigDecimal("1999.00"));
        courseService.addCourse(c1);
        courseService.addCourse(c2);

        Student s1 = new Student("S001", "Parasu R", "parasuramanraju18@gmail.com");
        PremiumStudent ps = new PremiumStudent("S002", "Alice", "alice@example.com", 15.0);
        studentService.addStudent(s1);
        studentService.addStudent(ps);
    }
}
