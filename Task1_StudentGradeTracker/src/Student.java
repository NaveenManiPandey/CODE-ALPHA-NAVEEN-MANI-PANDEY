// ============================================================
// Task 1: Student Grade Tracker
// Author: NAVEEN MANI PANDEY
// Description: Manages student grades with summary reporting
// ============================================================

public class Student {
    private String name;
    private int rollNumber;
    private double[] marks;
    private String[] subjects;

    public Student(String name, int rollNumber, double[] marks, String[] subjects) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.subjects = subjects;
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public double[] getMarks() { return marks; }
    public String[] getSubjects() { return subjects; }

    public double getAverage() {
        double total = 0;
        for (double mark : marks) total += mark;
        return total / marks.length;
    }

    public double getHighest() {
        double max = marks[0];
        for (double mark : marks) if (mark > max) max = mark;
        return max;
    }

    public double getLowest() {
        double min = marks[0];
        for (double mark : marks) if (mark < min) min = mark;
        return min;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    public void displayReport() {
        System.out.println("--------------------------------------------");
        System.out.printf("Student Name : %s%n", name);
        System.out.printf("Roll Number  : %d%n", rollNumber);
        System.out.println("Subject-wise Marks:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("   %-20s : %.2f%n", subjects[i], marks[i]);
        }
        System.out.printf("Average Score: %.2f%n", getAverage());
        System.out.printf("Highest Mark : %.2f%n", getHighest());
        System.out.printf("Lowest Mark  : %.2f%n", getLowest());
        System.out.printf("Grade        : %s%n", getGrade());
        System.out.println("--------------------------------------------");
    }
}
