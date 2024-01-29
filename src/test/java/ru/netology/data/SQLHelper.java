package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final String URL = System.getProperty("db.url");
    private static final String USER = System.getProperty("db.user");
    private static final String PASSWORD = System.getProperty("db.password");
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {}

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String requestSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return getStatus(requestSQL);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        String requestSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        return getStatus(requestSQL);
    }

    @SneakyThrows
    private static String getStatus(String requestSQL) {
        try (Connection connection = getConnection()) {
            return QUERY_RUNNER.query(connection, requestSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void clearDB() {
        try (Connection connection = getConnection()) {
            QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
            QUERY_RUNNER.execute(connection, "DELETE FROM order_entity");
            QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
        }
    }
}
