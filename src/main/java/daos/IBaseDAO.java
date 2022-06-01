package daos;

public interface IBaseDAO<T> {

    T getEntityById(Long id);
    void saveEntityById(T entity);
    void removeEntityById(T entity);
    void updateEntityById(T entity);

}
