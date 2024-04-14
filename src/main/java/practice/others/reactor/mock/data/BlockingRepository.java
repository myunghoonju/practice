package practice.others.reactor.mock.data;

public interface BlockingRepository<T> {

    void save(T value);

    T findFirst();

    Iterable<T> findAll();

    T findById(String id);

    int callCount();
}
