package connection;

        import io.github.cdimascio.dotenv.Dotenv;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class JdbcConnection {
    private static final Connection connection;

    static {
        try {
            Dotenv dotenv = Dotenv.configure().load();
            String url = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
