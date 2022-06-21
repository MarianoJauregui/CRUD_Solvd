package connectionPools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class Pool {
    private final static Logger LOGGER = LogManager.getLogger(Pool.class);
    public String dbProperties = "db.properties";

    private static Pool instance = null;
    protected Driver driver = null;
    protected String driverName = null;
    protected int maximumConnections = 6;
    protected int normalConnections = 3;

    protected Pool(){
      try {
          start();
          load();
      } catch (IOException e) {
          LOGGER.error(e.getMessage());
      }
    }

    private void start() throws IOException {
        InputStream inputStream = Pool.class.getClassLoader().getResourceAsStream(dbProperties);
        Properties properties = new Properties();
        properties.load(inputStream);

        this.driverName = properties.getProperty("driver.class.name");
        this.maximumConnections = Integer.valueOf(properties.getProperty("maximumConnections"));
        this.normalConnections = Integer.valueOf(properties.getProperty("normalConnections"));
    }


    private void load() {
        try{
            driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (IllegalAccessException | ClassNotFoundException | SQLException | InstantiationException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static Pool getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if(instance == null) {
            synchronized (Pool.class) {
                if(instance == null) {
                    instance = (Pool) Class.forName("Pool").newInstance();
                    instance.start();
                }
            }
        }
        return instance;
    }

    public abstract Connection getConnection();

    public abstract void releaseConnection (Connection connection);

    protected synchronized void release() {
        try{
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
