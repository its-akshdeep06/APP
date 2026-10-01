import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductManagement {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/productdb";

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
            runMenu(scanner, connection);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static String getSetting(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    private static void runMenu(Scanner scanner, Connection connection) {
        while (true) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Insert product");
            System.out.println("2. Find product by ID");
            System.out.println("3. Update product quantity");
            System.out.println("4. Display products with quantity below 10");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        insertProduct(scanner, connection);
                        break;
                    case "2":
                        findProduct(scanner, connection);
                        break;
                    case "3":
                        updateQuantity(scanner, connection);
                        break;
                    case "4":
                        displayLowStock(connection);
                        break;
                    case "5":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void insertProduct(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Product name: ");
        String productName = scanner.nextLine();

        System.out.print("Price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) "
                   + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setBigDecimal(3, price);
            statement.setInt(4, quantity);
            statement.executeUpdate();
            System.out.println("Product inserted.");
        }
    }

    private static void findProduct(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Product ID to find: ");
        int productId = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT ProductID, ProductName, Price, Quantity "
                   + "FROM Product WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    printProduct(resultSet);
                } else {
                    System.out.println("Product not found.");
                }
            }
        }
    }

    private static void updateQuantity(Scanner scanner, Connection connection)
            throws SQLException {
        System.out.print("Product ID to update: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("New quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        String sql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            int rowsUpdated = statement.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Quantity updated." : "Product not found.");
        }
    }

    private static void displayLowStock(Connection connection) throws SQLException {
        String sql = "SELECT ProductID, ProductName, Price, Quantity "
                   + "FROM Product WHERE Quantity < ? ORDER BY ProductID";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 10);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    printProduct(resultSet);
                    found = true;
                }
                if (!found) {
                    System.out.println("No products have quantity below 10.");
                }
            }
        }
    }

    private static void printProduct(ResultSet resultSet) throws SQLException {
        System.out.println("Product ID: " + resultSet.getInt("ProductID"));
        System.out.println("Product name: " + resultSet.getString("ProductName"));
        System.out.println("Price: " + resultSet.getBigDecimal("Price"));
        System.out.println("Quantity: " + resultSet.getInt("Quantity"));
        System.out.println("--------------------");
    }
}
