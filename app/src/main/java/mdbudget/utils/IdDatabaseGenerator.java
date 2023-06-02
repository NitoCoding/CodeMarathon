package mdbudget.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IdDatabaseGenerator {
    public static int generateId(String idField, String tableName) {
        int id = 0;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            conn = Connector.getConnection();
            statement = conn.createStatement();
            String query = String.format("SELECT MAX(%s) AS id FROM %s", idField,tableName);

            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                id = resultSet.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id;
    }
}
