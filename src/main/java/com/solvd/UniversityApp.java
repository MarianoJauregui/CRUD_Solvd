package com.solvd;

import entities.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.sql.*;


public class UniversityApp {

    private static final Logger LOGGER = LogManager.getLogger(UniversityApp.class);

    public static void main(String[] args ){

        Student student = new Student(1L, "Mariano", "Jauregui", "asd@asd.com");

        String jdbc = "jdbc:mysql://localhost:3306/jauregui_mariano?useSSL=false";
        final String CREATE_TABLE = "CREATE DATABASE Employee";
        PreparedStatement statement = null;

        try{
            Connection conexion = DriverManager.getConnection(jdbc, "root", "123456" );
            System.out.println("Im in.");
            statement = conexion.prepareStatement(CREATE_TABLE);
            statement.executeUpdate();
            System.out.println("Table created");
            conexion.close();
        } catch (SQLException e){
            LOGGER.error(e);
        }


        try{
            JAXBContext contextObj = JAXBContext.newInstance(Student.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println(marshallerObj);
        } catch(JAXBException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
