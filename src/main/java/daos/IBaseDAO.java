package daos;

public interface IBaseDAO<T> {

    T getEntity(Long id);
    void saveEntity(T entity);
    void removeEntity(T entity);
    void updateEntity(T entity);
}
