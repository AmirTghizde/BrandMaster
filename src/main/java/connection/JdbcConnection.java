package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "amir9650");

    public Connection getConnection() {
        return connection;
    }

    public JdbcConnection() throws SQLException {
    }
}
