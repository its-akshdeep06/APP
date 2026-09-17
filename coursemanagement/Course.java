package coursemanagement;

public class Course {
    private final String courseCode;
    private final String courseName;
    private final String instructor;
    private final int credits;

    public Course(String courseCode, String courseName, String instructor, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
    }

    public void displayInformation() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Instructor: " + instructor);
        System.out.println("Credits: " + credits);
    }
}
