package service;

import entities.Teacher;

import java.util.List;

public interface ITeacherService {

    List<Teacher> findAllTeachers();

    Teacher getTeacherById(Integer integer);
    void updateTeacher(Teacher teacher);
    void insertTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
}
