package connectionPools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool extends Pool{
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private int closeConnectionInUse;

    private String username = null;
    private String password = null;
    private String url = null;

    private Vector<Connection> availableConnections = new Vector<>();
    private static int activeConnections = 0;
    private static int nonActiveConnections = 0;
    private static ConnectionPool pool = null;

    public static synchronized ConnectionPool getInstance() {
        if(pool == null){
            pool = new ConnectionPool();
        }
        return pool;
    }


    private ConnectionPool() {
        try{
            start();
            for (int i = 0; i < normalConnections; i++){
                Connection connection = createNewConnection();
                if(connection != null){
                    availableConnections.addElement(connection);
                    activeConnections++;
                }
            }
        } catch (IOException e){
            LOGGER.error(e.getMessage());
        }
    }

    private void start() throws IOException {
        InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(dbProperties);
        Properties properties = new Properties();
        properties.load(inputStream);

        this.username = properties.getProperty("datasource.username");
        this.password = properties.getProperty("datasource.password");
        this.url = properties.getProperty("datasource.url");
        this.driverName = properties.getProperty("driver.class.name");
        this.maximumConnections = Integer.parseInt(properties.getProperty("maximumConnections"));
        this.normalConnections = Integer.parseInt(properties.getProperty("normalConnections"));
    }

    private Connection createNewConnection(){
        Connection connection = null;
        try{
            if(username == null) {
                connection = DriverManager.getConnection(url);
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    @Override
    public synchronized Connection getConnection() {
        Connection conn = null;
        if(availableConnections.size() > 0){
            activeConnections--;
            conn = availableConnections.firstElement();
            availableConnections.removeElement(0);
            try{
                if(conn.isClosed()){
                    conn = getConnection();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        } else if(maximumConnections == 0 || closeConnectionInUse < maximumConnections) {
            conn = createNewConnection();
        }
        if (conn != null) {
            closeConnectionInUse++;
        } activeConnections ++;
        return conn;
    }

    @Override
    public void release() {
        try{
            Enumeration<Connection> allConnections = availableConnections.elements();
            //This is a loop to close all the open connections.
            while(allConnections.hasMoreElements()) {
                Connection connection = allConnections.nextElement();
                try{
                    connection.close();
                    nonActiveConnections--;
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            }
            availableConnections.removeAllElements();
            activeConnections = 0;
        } finally {
            super.release();
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        availableConnections.addElement(connection);
        nonActiveConnections++;
        closeConnectionInUse--;
        activeConnections--;
    }




}
