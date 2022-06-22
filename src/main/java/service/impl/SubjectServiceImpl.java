package service.impl;

import daos.mySQLimpl.SubjectDAOImpl;
import entities.Subject;
import exceptions.DAOImplException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ISubjectService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImpl implements ISubjectService {

    private static final Logger LOGGER = LogManager.getLogger(SubjectServiceImpl.class);
    private static final SubjectDAOImpl subjectDAO = new SubjectDAOImpl();


    @Override
    public List<Subject> findAllSubjects() {
        List<Subject> subjectList = new ArrayList<>();
        subjectList = subjectDAO.list();
        return subjectList;
    }

    @Override
    public Subject getSubjectById(Integer integer) {
        Subject subject = new Subject();
        try{
            subject = subjectDAO.getEntityById(Long.valueOf(integer));
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        } return subject;
    }

    @Override
    public void updateSubject(Subject subject) {
        try{
            subjectDAO.updateEntityById(subject);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void insertSubject(Subject subject) {
        try{
            subjectDAO.saveEntityById(subject);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteSubject(Subject subject) {
        try{
            subjectDAO.removeEntityById(subject);
        } catch (DAOImplException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
