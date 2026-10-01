import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Map<String, String> config = loadConfig();
            String url = getSetting(config, "DB_URL", "jdbc:mysql://localhost:3306/library");
            String user = getRequiredSetting(config, "DB_USER");
            String password = getRequiredSetting(config, "DB_PASSWORD");

            try (Connection connection =
                         DriverManager.getConnection(url, user, password);
                 Scanner scanner = new Scanner(System.in)) {

                while (true) {
                    System.out.println("\n--- Library Menu ---");
                    System.out.println("1. Insert Book");
                    System.out.println("2. Search Book");
                    System.out.println("3. Display Available Books");
                    System.out.println("4. Issue Book");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            insertBook(scanner, connection);
                            break;
                        case 2:
                            searchBook(scanner, connection);
                            break;
                        case 3:
                            displayAvailableBooks(connection);
                            break;
                        case 4:
                            issueBook(scanner, connection);
                            break;
                        case 5:
                            System.out.println("Goodbye.");
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver was not found.");
            System.out.println("Check that the Connector/J JAR is in the project classpath.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Map<String, String> loadConfig() {
        Path envFile = Paths.get(".env");
        if (!Files.exists(envFile)) {
            envFile = Paths.get("..", ".env");
        }
        if (!Files.exists(envFile)) {
            envFile = Paths.get("jdbc-library", ".env");
        }

        Map<String, String> config = new HashMap<>();
        if (!Files.exists(envFile)) {
            return config;
        }

        try {
            for (String line : Files.readAllLines(envFile)) {
                String entry = line.trim();
                if (entry.isEmpty() || entry.startsWith("#")) {
                    continue;
                }

                int separator = entry.indexOf('=');
                if (separator > 0) {
                    String key = entry.substring(0, separator).trim();
                    String value = entry.substring(separator + 1).trim();
                    if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }
                    config.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not read .env configuration file.", e);
        }
        return config;
    }

    private static String getSetting(Map<String, String> config, String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isEmpty()) {
            value = config.get(key);
        }
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    private static String getRequiredSetting(Map<String, String> config, String key) {
        String value = getSetting(config, key, null);
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Missing " + key + ". Set it in .env or the process environment.");
        }
        return value;
    }

    private static void insertBook(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        String sql = "INSERT INTO Book (BookID, Title, Author, Price, Availability) "
                   + "VALUES (?, ?, ?, ?, TRUE)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setDouble(4, price);

            statement.executeUpdate();
            System.out.println("Book inserted and marked available.");
        }
    }

    private static void searchBook(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Enter Book ID to search: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT BookID, Title, Author, Price, Availability "
                   + "FROM Book WHERE BookID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    printBook(resultSet);
                } else {
                    System.out.println("Book not found.");
                }
            }
        }
    }

    private static void displayAvailableBooks(Connection connection)
            throws SQLException {
        String sql = "SELECT BookID, Title, Author, Price, Availability "
                   + "FROM Book WHERE Availability = TRUE ORDER BY BookID";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            boolean found = false;

            while (resultSet.next()) {
                printBook(resultSet);
                found = true;
            }

            if (!found) {
                System.out.println("No available books.");
            }
        }
    }

    private static void issueBook(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Enter Book ID to issue: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        String sql = "UPDATE Book SET Availability = FALSE "
                   + "WHERE BookID = ? AND Availability = TRUE";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book not found or it is already issued.");
            }
        }
    }

    private static void printBook(ResultSet resultSet) throws SQLException {
        System.out.println("Book ID: " + resultSet.getInt("BookID"));
        System.out.println("Title: " + resultSet.getString("Title"));
        System.out.println("Author: " + resultSet.getString("Author"));
        System.out.println("Price: " + resultSet.getBigDecimal("Price"));
        System.out.println("Availability: "
                + (resultSet.getBoolean("Availability") ? "Available" : "Issued"));
        System.out.println("--------------------");
    }
}
