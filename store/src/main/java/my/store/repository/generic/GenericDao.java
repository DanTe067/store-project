package my.store.repository.generic;

public interface GenericDao<ID, T> {

    T create(T object);

    T get(ID id);

    T update(T object);

    void delete(ID id);
}
