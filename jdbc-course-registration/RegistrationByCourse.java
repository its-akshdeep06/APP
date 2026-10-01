import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RegistrationByCourse {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/course_registration_db";

    public static void main(String[] args) {
        String url = getSetting("DB_URL", DEFAULT_URL);
        String user = getSetting("DB_USER", null);
        String password = getSetting("DB_PASSWORD", null);

        if (user == null || password == null) {
            System.out.println("Set DB_USER and DB_PASSWORD in the process environment.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine().trim();
            displayRegistrations(connection, courseCode);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static String getSetting(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    private static void displayRegistrations(Connection connection, String courseCode)
            throws SQLException {
        String sql = "SELECT StudentID, StudentName, CourseCode, CourseName, Semester "
                   + "FROM CourseRegistration WHERE CourseCode = ? ORDER BY StudentID";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    System.out.println("Student ID: " + resultSet.getString("StudentID"));
                    System.out.println("Student name: " + resultSet.getString("StudentName"));
                    System.out.println("Course code: " + resultSet.getString("CourseCode"));
                    System.out.println("Course name: " + resultSet.getString("CourseName"));
                    System.out.println("Semester: " + resultSet.getString("Semester"));
                    System.out.println("--------------------");
                    found = true;
                }

                if (!found) {
                    System.out.println("No students are registered for course code " + courseCode + ".");
                }
            }
        }
    }
}
