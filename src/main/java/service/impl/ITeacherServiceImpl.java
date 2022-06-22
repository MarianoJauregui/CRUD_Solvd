package service.impl;

import daos.mySQLimpl.TeacherDAOImpl;
import entities.Teacher;
import exceptions.DAOImplException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ITeacherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITeacherServiceImpl implements ITeacherService {

    private static final Logger LOGGER = LogManager.getLogger(ITeacherServiceImpl.class);
    private static final TeacherDAOImpl teacherDAO = new TeacherDAOImpl();


    @Override
    public List<Teacher> findAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList = teacherDAO.list();
        return teacherList;
    }

    @Override
    public Teacher getTeacherById(Integer integer) {
        Teacher teacher = new Teacher();
        try{
            teacher = teacherDAO.getEntityById(Long.valueOf(integer));
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        } return teacher;
    }

    @Override
    public void updateTeacher(Teacher student) {
        try{
            teacherDAO.updateEntityById(student);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void insertTeacher(Teacher student) {
        try{
            teacherDAO.saveEntityById(student);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(Teacher student) {
        try{
            teacherDAO.removeEntityById(student);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
