package com.solvd;

import entities.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class UniversityApp {

    private static final Logger LOGGER = LogManager.getLogger(UniversityApp.class);

    public static void main(String[] args ){

        Student student = new Student(1L, "Mariano", "Jauregui", "asd@asd.com");

    }
}
