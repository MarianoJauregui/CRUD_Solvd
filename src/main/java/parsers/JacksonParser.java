package parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser {
    private static final Logger LOGGER = LogManager.getLogger(JacksonParser.class);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        marshall(objectMapper);
    }

    public static void marshall(ObjectMapper objectMapper) {
        Student student = new Student(1L, "Omar", "Obaca", "asd@asd.com");
        File jsonStudentFile = new File(System.getenv("JsonStudentFile"));
        try{
            objectMapper.writeValue(jsonStudentFile, student);
        } catch (IOException e){
            LOGGER.error(e.getMessage());
        }
    }


}
