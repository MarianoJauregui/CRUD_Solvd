package jdbcMySQLimpl.daoImpl;

import daos.IStudentDAO;
import entities.Degree;
import entities.Student;
import entities.Subject;

import java.util.List;

public class StudentDAO implements IStudentDAO {

    @Override
    public Student getEntityById(Long id) {
        return null;
    }

    @Override
    public void saveEntityById(Student entity) {

    }

    @Override
    public void removeEntityById(Student entity) {

    }

    @Override
    public void updateEntityById(Student entity) {

    }

    @Override
    public List<Subject> getSubjectsById(Long id) {
        return null;
    }

    @Override
    public List<Degree> getDegreesById(Long id) {
        return null;
    }
}
