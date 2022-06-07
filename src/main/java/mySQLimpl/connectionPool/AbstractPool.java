package mySQLimpl.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractPool {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPool.class);

    public String conProperties = "db.properties";

    private static AbstractPool pool = null;
    protected int maximumConnections = 8;
    protected int normalConnections = 4;
    protected String driverName = null;
    protected Driver driver = null;

    protected AbstractPool(){
        try {
            initialize();
            loadDrivers();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void initialize() throws IOException {
        InputStream inputStream = AbstractPool.class.getClassLoader().getResourceAsStream(conProperties);
        Properties properties = new Properties();
        properties.load(inputStream);
        this.driverName = properties.getProperty("driver.class.name");
        this.maximumConnections = Integer.parseInt(properties.getProperty("maximumConnections"));
        this.normalConnections = Integer.parseInt(properties.getProperty("normalConnections"));
    }

    private void loadDrivers(){
        try {
            driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
