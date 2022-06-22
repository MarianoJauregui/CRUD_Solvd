package daos.mySQLimpl;

import connectionPools.ConnectionPool;
import daos.interfaces.ISubjectDAO;
import entities.Subject;
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

public class SubjectDAOImpl implements ISubjectDAO {

    private static final Logger LOGGER = LogManager.getLogger(StudentDAOImpl.class);
    private static final String SELECT = "SELECT * FROM Subject";
    private static final String INSERT = "INSERT INTO Subject (theme) VALUES (?)";
    private static final String UPDATE = "UPDATE Subject SET theme = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Subject WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM Subject WHERE id = ?";
    private Connection connection = ConnectionPool.getInstance().getConnection();


    private Subject info(ResultSet resultSet) throws DAOImplException {
        Subject subject = null;
        try {
            String theme = resultSet.getString("theme");
            subject = new Subject(theme);
            subject.setId((long) resultSet.getInt("id"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return subject;
    }


    @Override
    public List<Subject> list() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Subject> subjects = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subjects.add(info(resultSet));
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
        return subjects;
    }

    @Override
    public Subject getEntityById(Long id) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Subject subject = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, Math.toIntExact(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = info(resultSet);
            } else {
                throw new DAOImplException("This entity was not found.");
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
        return subject;
    }

    @Override
    public void updateEntityById(Subject subject) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, Math.toIntExact(subject.getId()));
            preparedStatement.setString(2, subject.getTheme());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DAOImplException("This subject was not updated.");
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void saveEntityById(Subject subject) throws DAOImplException, SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        try {
            preparedStatement.setInt(1, Math.toIntExact(subject.getId()));
            preparedStatement.setString(2, subject.getTheme());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DAOImplException("The subject was not saved.");
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
    public void removeEntityById(Subject subject) throws DAOImplException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, Math.toIntExact(subject.getId()));
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
