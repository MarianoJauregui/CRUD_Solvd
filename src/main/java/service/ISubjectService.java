package service;

import entities.Subject;

import java.util.List;

public interface ISubjectService {

    List<Subject> findAllSubjects();

    Subject getSubjectById(Integer integer);
    void updateSubject(Subject subject);
    void insertSubject(Subject subject);
    void deleteSubject(Subject subject);
}
