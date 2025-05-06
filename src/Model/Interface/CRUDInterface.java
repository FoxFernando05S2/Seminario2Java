package Model.Interface;

import java.util.List;

public interface CRUDInterface<T> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(String id);
    List<T> getAll();
    T getById(String id);
}
