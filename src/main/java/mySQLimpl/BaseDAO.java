package mySQLimpl;

import daos.IBaseDAO;
import mySQLimpl.connectionPool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class BaseDAO<T> implements IBaseDAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(BaseDAO.class);
    private final String TABLE_NAME;
    private final String CLASS_NAME;
    private Connection connection;
    private Class<T> instance;
    private Long id;
    private ConcurrentHashMap<String, String> classFields;
    private ConcurrentHashMap<String, String> objectFields;
    private ConcurrentHashMap<String, String> getObjectFields;

    public BaseDAO(String TABLE_NAME, String CLASS_NAME){
        this.CLASS_NAME = CLASS_NAME;
        this.TABLE_NAME = TABLE_NAME;
        classFields = new ConcurrentHashMap<>();
        objectFields = new ConcurrentHashMap<>();
        getObjectFields = new ConcurrentHashMap<>();
    }

    @Override
    public T getEntityById(Long id) {
        return null;
    }

    @Override
    public void saveEntityById(T entity) {

    }

    @Override
    public void removeEntityById(T entity) {

    }

    @Override
    public void updateEntityById(T entity) {

    }
/*
    @Override
    public T getEntityById(Long id){
        startConnection();
/*
        String selectStatement =
                "SELECT * FROM " + this.TABLE_NAME + " WHERE " + ID.getAttribute() + " = " + id + ";";

        T resultEntity = null;
        try(PreparedStatement getEntity = connection.prepareStatement(selectStatement));
        ResultSet resultSet = getEntity.executeQuery()){

    resultEntity = this.parseResultSet(resultSet);
        }
    }


    private void startConnection(){
        try{
            this.connection = ConnectionPool.getInstance().getConnection();
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage());
            this.startConnection();
        }
    }*/
}
