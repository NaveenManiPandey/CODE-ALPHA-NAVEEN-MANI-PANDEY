// ============================================================
// Task 1: Student Grade Tracker
// Author: NAVEEN MANI PANDEY
// Description: Console-based student grade management system
// ============================================================

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String[] subjects = {"Mathematics", "Physics", "Chemistry", "English", "Computer Science"};

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         STUDENT GRADE TRACKER - NAVEEN MANI PANDEY        ");
        System.out.println("============================================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Display Summary Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: displaySummaryReport(); break;
                case 5:
                    System.out.println("\nThank you for using Student Grade Tracker!");
                    System.out.println("Developed by: NAVEEN MANI PANDEY");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        double[] marks = new double[subjects.length];
        System.out.println("Enter marks for each subject (out of 100):");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("  %s: ", subjects[i]);
            marks[i] = getDoubleInput();
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Setting to 0.");
                marks[i] = 0;
            }
        }

        students.add(new Student(name, roll, marks, subjects));
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }
        System.out.println("\n--- ALL STUDENTS ---");
        for (Student s : students) s.displayReport();
    }

    private static void searchStudent() {
        System.out.print("\nEnter Roll Number to search: ");
        int roll = getIntInput();
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                s.displayReport();
                return;
            }
        }
        System.out.println("Student with Roll Number " + roll + " not found.");
    }

    private static void displaySummaryReport() {
        if (students.isEmpty()) {
            System.out.println("\nNo students to report.");
            return;
        }

        System.out.println("\n============================================================");
        System.out.println("                    SUMMARY REPORT                         ");
        System.out.println("              Prepared by: NAVEEN MANI PANDEY               ");
        System.out.println("============================================================");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s %-6s%n",
                "Roll", "Name", "Average", "Highest", "Lowest", "Grade");
        System.out.println("------------------------------------------------------------");

        double classTotal = 0;
        Student topStudent = students.get(0);
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-10.2f %-10.2f %-10.2f %-6s%n",
                    s.getRollNumber(), s.getName(), s.getAverage(),
                    s.getHighest(), s.getLowest(), s.getGrade());
            classTotal += s.getAverage();
            if (s.getAverage() > topStudent.getAverage()) topStudent = s;
        }
        System.out.println("------------------------------------------------------------");
        System.out.printf("Total Students   : %d%n", students.size());
        System.out.printf("Class Average    : %.2f%n", classTotal / students.size());
        System.out.printf("Top Performer    : %s (%.2f)%n", topStudent.getName(), topStudent.getAverage());
        System.out.println("============================================================");
    }

    private static int getIntInput() {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
