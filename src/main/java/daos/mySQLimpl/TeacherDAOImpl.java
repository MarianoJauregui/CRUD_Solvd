package daos.mySQLimpl;

import connectionPools.ConnectionPool;
import daos.interfaces.ITeacherDAO;
import entities.Teacher;
import exceptions.DAOImplException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    private static final Logger LOGGER = LogManager.getLogger(StudentDAOImpl.class);
    private static final String SELECT = "SELECT * FROM Teacher";
    private static final String INSERT = "INSERT INTO Teacher (first_name, last_name) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Teacher SET first_name = ?, last_name = ?, WHERE id = ?";
    private static final String DELETE = "DELETE FROM Teacher WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM Teacher WHERE id = ?";
    private Connection connection = ConnectionPool.getInstance().getConnection();

    private Teacher info(ResultSet resultSet) throws DAOImplException {
        Teacher teacher = null;
        try {
            String name = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            teacher = new Teacher(name, lastName);
            teacher.setId((long) resultSet.getInt("id"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return teacher;
    }


    @Override
    public List<Teacher> list() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Teacher> teacher = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher.add(info(resultSet));
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
        return teacher;
    }

    @Override
    public Teacher getEntityById(Long id) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, Math.toIntExact(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = info(resultSet);
            } else {
                throw new DAOImplException("This register is not found ");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return teacher;
    }

    @Override
    public void updateEntityById(Teacher teacher) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, Math.toIntExact(teacher.getId()));
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());

            if (preparedStatement.executeUpdate() == 0) {
                throw new DAOImplException("The teacher was not updated.");
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void saveEntityById(Teacher teacher) throws DAOImplException, SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        try {
            preparedStatement.setInt(1, Math.toIntExact(teacher.getId()));
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());

            if (preparedStatement.executeUpdate() == 0) {
                throw new DAOImplException("The teacher was not saved.");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.debug(e.getMessage());
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void removeEntityById(Teacher teacher) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, Math.toIntExact(teacher.getId()));
            if (preparedStatement.executeUpdate() == 0) {
                throw new DAOImplException("The delete query could not be executed properly.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
