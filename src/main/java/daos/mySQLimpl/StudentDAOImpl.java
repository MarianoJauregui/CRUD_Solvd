package daos.mySQLimpl;

import connectionPools.ConnectionPool;
import daos.interfaces.IStudentDAO;
import entities.Student;
import exceptions.DAOImplException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    private static final Logger LOGGER = LogManager.getLogger(StudentDAOImpl.class);
    private static final String SELECT = "SELECT * FROM Student";
    private static final String INSERT = "INSERT INTO Student (first_name, last_name, email) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE Student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Student WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM Student WHERE id = ?";
    private Connection connection = ConnectionPool.getInstance().getConnection();

    private Student info(ResultSet resultSet) throws DAOImplException {
        String name = null;
        Student student = null;

        try{
            name = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            student = new Student(name, lastName, email);
            student.setId((long) resultSet.getInt("id"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> list() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(info(resultSet));
            }
        } catch (SQLException | DAOImplException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return students;
    }

    @Override
    public void saveEntityById(Student student) throws DAOImplException, SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(INSERT);
        try{
            statement.setInt(1, Math.toIntExact(student.getId()));
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());

            if(statement.executeUpdate() == 0) {
                throw new DAOImplException("Something went wrong, please try again.");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.error(e.getMessage());
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public Student getEntityById(Long id) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, Math.toIntExact(id));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                student = info(resultSet);
            } else throw new DAOImplException("Not found.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DAOImplException("Something went wrong when trying to close the result set");
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
                ConnectionPool.getInstance().releaseConnection(connection);
            }
    }
        return student;
    }

    @Override
    public void updateEntityById(Student student) throws DAOImplException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setInt(1, Math.toIntExact(student.getId()));
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            if(statement.executeUpdate() == 0) {
                throw new DAOImplException(("The wanted student was not able to update"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeEntityById(Student student) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, Math.toIntExact(student.getId()));
            if(preparedStatement.executeUpdate() == 0){
                throw new DAOImplException("Something went wrong, please try again.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

}
