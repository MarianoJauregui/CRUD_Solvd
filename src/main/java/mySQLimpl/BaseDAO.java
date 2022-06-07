package mySQLimpl;

import daos.IBaseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
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




}
