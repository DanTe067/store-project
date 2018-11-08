package my.flipside.repository.generic;

import java.util.List;

public interface GenericDao<ID, T> {

    int create(T object);

    T get(ID id);

    T update(T object);

    void delete(ID id);

    List<T> getAll();
}
