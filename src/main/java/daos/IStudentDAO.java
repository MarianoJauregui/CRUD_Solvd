package daos;

import entities.Degree;
import entities.Student;
import entities.Subject;

import java.util.List;

public interface IStudentDAO extends IBaseDAO<Student> {

    List<Subject> getSubjectsById(Long id);
    List<Degree> getDegreesById(Long id);
}
