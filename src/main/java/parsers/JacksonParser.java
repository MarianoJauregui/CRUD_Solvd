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
        Student student = createStudent();


        try {
            objectMapper.writeValue(new File("C:/Users/PC/IdeaProjects/CRUD_Solvd/src/main/java/parsers/mapper.json"), student);
            String jsonString = objectMapper.writeValueAsString(student);
            LOGGER.info(jsonString);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        try{
            Student student1 = objectMapper.readValue(new File("C:/Users/PC/IdeaProjects/CRUD_Solvd/src/main/java/parsers/mapper.json"),
                    Student.class);

            String jsonInString = "{\"id\":1,\"firstName\":\"Mariano\",\"lastName\":\"Jauregui\",\"email\":\"asd@asd.com\",\"university\":null,\"degrees\":null,\"subjects\":null}";
            Student student2 = objectMapper.readValue(jsonInString, Student.class);

            System.out.println((objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student2)));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static Student createStudent() {
        Student student = new Student(1L, "Mariano", "Jauregui", "asd@asd.com");
        return student;
    }
}


