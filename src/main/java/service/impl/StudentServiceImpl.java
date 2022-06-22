package service.impl;

import daos.mySQLimpl.StudentDAOImpl;
import entities.Student;
import exceptions.DAOImplException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IStudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private static final Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class);
    private static final StudentDAOImpl studentDAO = new StudentDAOImpl();


    @Override
    public List<Student> findAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList = studentDAO.list();
        return studentList;
    }

    @Override
    public Student getStudentById(Integer integer) {
        Student student = new Student();
        try{
            student = studentDAO.getEntityById(Long.valueOf(integer));
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        } return student;
    }

    @Override
    public void updateStudent(Student student) {
        try{
            studentDAO.updateEntityById(student);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void insertStudent(Student student) {
        try{
            studentDAO.saveEntityById(student);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try{
            studentDAO.removeEntityById(student);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
