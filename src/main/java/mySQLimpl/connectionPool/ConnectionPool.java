package mySQLimpl.connectionPool;

import java.util.concurrent.BlockingQueue;

public class ConnectionPool extends AbstractPool {


    // This would be a general realization.
    // private final static ConnectionPool instance = new ConnectionPool();

    //Lazy initialization
    private static ConnectionPool instance;

    public static ConnectionPool getInstance(){
        if (instance == null){
            instance = new ConnectionPool();
        } return instance;
    }

    private BlockingQueue<ConnectionPool> connectionPools;

    //SINGLETON.
    private ConnectionPool(){}

}
