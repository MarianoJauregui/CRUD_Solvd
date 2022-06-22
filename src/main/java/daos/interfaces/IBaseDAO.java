package daos.interfaces;

import exceptions.DAOImplException;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T, K> {

    List<T> list();

    T getEntityById(Long id) throws DAOImplException;

    void saveEntityById(T entity) throws DAOImplException, SQLException;

    void removeEntityById(T entity) throws DAOImplException;

    void updateEntityById(T entity) throws DAOImplException;

}
