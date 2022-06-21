package daos.mySQLimpl;

import connectionPools.ConnectionPool;
import daos.interfaces.IStudentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class StudentDAOImpl implements IStudentDAO {

    private static final Logger LOGGER = LogManager.getLogger(StudentDAOImpl.class);
    private static final String SELECT = "SELECT * FROM Student";
    private static final String INSERT = "INSERT INTO Student (first_name, last_name, email) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE Student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Student WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM Student WHERE id = ?";
    private Connection connection = ConnectionPool.getInstance().getConnection();



}
