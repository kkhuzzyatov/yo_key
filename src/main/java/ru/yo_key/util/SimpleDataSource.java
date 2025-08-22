package ru.yo_key.util;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Component
public class SimpleDataSource implements DataSource {

    private final String url = "jdbc:postgresql://localhost:5432/yo_key";
    private final String user = "postgres";
    private final String password = "1234";

    private Connection connection = null;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new RuntimeException("Not implemented");
    }
}
