package ua.training;

public interface SimpleLinkedList<T> extends Iterable<T> {

    boolean add(T element);

    T set(Integer position, T element);

    T get(Integer position);

    boolean remove(T element);

    boolean contains(T element);

    Integer size();

    void clear();

    boolean isEmpty();
}
