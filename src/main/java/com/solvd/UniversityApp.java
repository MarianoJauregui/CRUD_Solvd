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


        final String CREATE_TABLE = "CREATE DATABASE Employee";
        PreparedStatement statement = null;

        // TODO: 12/5/2022 Implement SQL Methods (Select, Insert, Update, Delete, Find)


/*
        try{
            JAXBContext contextObj = JAXBContext.newInstance(Student.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println(marshallerObj);
        } catch(JAXBException e) {
            LOGGER.error(e.getMessage());
        }

 */

    }
}
