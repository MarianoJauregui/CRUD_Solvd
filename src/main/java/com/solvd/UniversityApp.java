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

        try{
            Connection conexion = DriverManager.getConnection(jdbc, "root", "123456" );
            System.out.println("Im in.");
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try{
            JAXBContext contextObj = JAXBContext.newInstance(Student.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch(JAXBException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
