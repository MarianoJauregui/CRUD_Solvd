package com.solvd.JDBC;

import java.sql.*;

public class DbConnection {
    private static final String jdbc = "jdbc:mysql://jauregui_mariano:3306/jauregui_mariano";
    private static final String jdbcUser = "root";
    private static final String jdbcPassword = "devintern";

    public static Connection conection() throws SQLException{
        return DriverManager.getConnection(jdbc, jdbcUser, jdbcPassword);
    }

}
