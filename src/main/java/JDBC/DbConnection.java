package JDBC;

import java.sql.*;

public class DbConnection {

    //This is my personal db connection.
    private static final String JDBC = "jdbc:mysql://localhost:3306/jauregui_mariano?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "123456";

    public static Connection conection() throws SQLException{
        return DriverManager.getConnection(JDBC, JDBC_USER, JDBC_PASSWORD);
    }

}
