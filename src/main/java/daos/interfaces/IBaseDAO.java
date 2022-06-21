package daos.interfaces;

import exceptions.NoSuchThingWasFound;

public interface IBaseDAO<T> {

    T getEntityById(Long id) throws NoSuchThingWasFound;
    void saveEntityById(T entity) throws NoSuchThingWasFound;
    void removeEntityById(T entity) throws NoSuchThingWasFound;
    void updateEntityById(T entity) throws NoSuchThingWasFound;

}
