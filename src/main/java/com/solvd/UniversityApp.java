package com.solvd;

import java.sql.*;


public class UniversityApp {
    public static void main( String[] args ){

        // String jdbc = "jdbc:mysql://52.59.193.212:3306/jauregui_mariano";
        String jdbc = "jdbc:/mysql://localhost:3306/my";

        try{
            Connection conexion = DriverManager.getConnection(jdbc, "root", "123456" );
            System.out.println("Im in.");
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}